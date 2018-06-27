package com.mdove.passwordguard.home.ettodayplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityEtTodayPlanBinding;
import com.mdove.passwordguard.home.ettodayplan.adapter.EtTodayPlanAdapter;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.handler.EtTodayPlanHandler;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.event.SetTimeEvent;
import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.home.ettodayplan.presenter.contract.EtTodayPlanContract;
import com.mdove.passwordguard.home.utils.TodayPlanUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/26.
 */

public class EtTodayPlanActivity extends BaseActivity implements EtTodayPlanContract.MvpView {
    private EtTodayPlanPresenter mPresenter;
    private ActivityEtTodayPlanBinding mBinding;
    private List<BaseTodayPlanModel> mData;
    private EtTodayPlanAdapter mAdapter;

    public static void start(Context context) {
        Intent start = new Intent(context, EtTodayPlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_today_plan);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));
        RxBus.get().register(this);

        mPresenter = new EtTodayPlanPresenter();
        mPresenter.subscribe(this);

        mBinding.setHandler(new EtTodayPlanHandler(mPresenter));
        mData = new ArrayList<>();
        mAdapter = new EtTodayPlanAdapter(this, mData);

        mBinding.rlvPlan.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rlvPlan.setAdapter(mAdapter);

        initListener();
    }

    private void initListener() {
        mBinding.btnAddMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                String tips = mBinding.etTips.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mBinding.etPlan.setText("");
                    mPresenter.addMainTodayPlan(TodayPlanUtils.getMainTodatPlan(content, tips));
                }else{
                    ToastHelper.shortToast("啥计划也没写呀？");
                }
            }
        });
        mBinding.btnAddSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                String tips = mBinding.etTips.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mBinding.etPlan.setText("");
                    mPresenter.addSecondTodayPlan(TodayPlanUtils.getSecondTodayPlan(content, tips));
                }else{
                    ToastHelper.shortToast("啥计划也没写呀？");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Subscribe
    public void setTimeCallBack(SetTimeEvent event) {
        mBinding.tvTime.setText(event.mModel.mTime);
    }

    @Override
    public void addMainTodayPlanReturn(MainTodayPlanModel model) {
        mBinding.btnAddMain.setClickable(false);
        mBinding.btnAddMain.setTextColor(ContextCompat.getColor(this, R.color.gray));

        mBinding.btnAddSecond.setClickable(true);
        mBinding.btnAddSecond.setTextColor(ContextCompat.getColor(this, R.color.black));

        mAdapter.insertData(model);
    }

    @Override
    public void addMainTodayPlanReturn(SecondTodayPlanModel model) {
        mAdapter.insertData(model);
    }
}
