package com.mdove.passwordguard.main.newmain.everydayreplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.databinding.ActivityEtEverydayReplayBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.newmain.everydayreplay.adapter.EverydayReplayRlvAdapter;
import com.mdove.passwordguard.main.newmain.everydayreplay.presenter.EtEverydayReplayPresenter;
import com.mdove.passwordguard.main.newmain.everydayreplay.presenter.contract.EtEverydayReplayContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class EtEverydayReplayActivity extends BaseActivity implements EtEverydayReplayContract.MvpView {
    private ActivityEtEverydayReplayBinding mBinding;
    private EverydayReplayRlvAdapter mAdapter;
    private EtEverydayReplayPresenter mPresenter;
    private int mDefaultNormal = DailyPlanModel.STATUS_NORMAL;

    public static void start(Context context) {
        Intent intent = new Intent(context, EtEverydayReplayActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_everyday_replay);
        initToolbar();

        mPresenter = new EtEverydayReplayPresenter();
        mAdapter = new EverydayReplayRlvAdapter(this, mPresenter);
        mPresenter.subscribe(this);

        mBinding.rlv.setAdapter(mAdapter);
        mBinding.rlv.setLayoutManager(new LinearLayoutManager(this));

        mPresenter.initData();

        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etEverydayReplay.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.addDailyPlan(content,mDefaultNormal);
                    mBinding.etEverydayReplay.setText("");
                    ToastHelper.shortToast("添加成功");
                    return;
                }
                ToastHelper.shortToast("复盘内容怎么能为空呢？");
            }
        });

        initGetOrLost();
    }

    private void initGetOrLost() {
        changeStatus(mDefaultNormal);
        mBinding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDefaultNormal != DailyPlanModel.STATUS_GET) {
                    mDefaultNormal = DailyPlanModel.STATUS_GET;
                    changeStatus(mDefaultNormal);
                }
            }
        });
        mBinding.btnLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDefaultNormal != DailyPlanModel.STATUS_LOST) {
                    mDefaultNormal = DailyPlanModel.STATUS_LOST;
                    changeStatus(mDefaultNormal);
                }
            }
        });
        mBinding.btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDefaultNormal!= DailyPlanModel.STATUS_NORMAL) {
                    mDefaultNormal = DailyPlanModel.STATUS_NORMAL;
                    changeStatus(mDefaultNormal);
                }
            }
        });
    }

    private void changeStatus(int status) {
        switch (status) {
            case DailyPlanModel.STATUS_GET: {
                mBinding.ivGet.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvGet.setTextColor(ContextCompat.getColor(this, R.color.black));

                mBinding.ivLost.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvLost.setTextColor(ContextCompat.getColor(this, R.color.gray));
                mBinding.ivNormal.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvNormal.setTextColor(ContextCompat.getColor(this, R.color.gray));
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
                mBinding.ivLost.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvLost.setTextColor(ContextCompat.getColor(this, R.color.black));

                mBinding.ivGet.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvGet.setTextColor(ContextCompat.getColor(this, R.color.gray));
                mBinding.ivNormal.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvNormal.setTextColor(ContextCompat.getColor(this, R.color.gray));
                break;
            }
            case DailyPlanModel.STATUS_NORMAL: {
                mBinding.ivNormal.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvNormal.setTextColor(ContextCompat.getColor(this, R.color.black));

                mBinding.ivLost.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvLost.setTextColor(ContextCompat.getColor(this, R.color.gray));
                mBinding.ivGet.setColorFilter(ContextCompat.getColor(this, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                mBinding.tvGet.setTextColor(ContextCompat.getColor(this, R.color.gray));
                break;
            }
            default: {
                break;
            }
        }
    }

    private void initToolbar() {
        mBinding.toolbar.setTitle("今日复盘");
        setSupportActionBar(mBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<BaseCalendarModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void updateLostOrGet(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void onClickDailyPlanDelete(int position) {
        mAdapter.notifyDelete(position);
    }

    @Override
    public void addDailyPlan(int position) {
        mAdapter.notifyPosition(position);
    }
}
