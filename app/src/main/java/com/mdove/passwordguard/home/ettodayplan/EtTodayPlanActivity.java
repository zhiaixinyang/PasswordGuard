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
import com.mdove.passwordguard.home.constant.TimeConstant;
import com.mdove.passwordguard.home.ettodayplan.adapter.EtTodayPlanAdapter;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.handler.EtTodayPlanHandler;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.event.SetTimeEvent;
import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.home.ettodayplan.presenter.contract.EtTodayPlanContract;
import com.mdove.passwordguard.home.utils.TodayPlanUtils;
import com.mdove.passwordguard.ui.renkstar.BubbleSeekBar;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/26.
 */

public class EtTodayPlanActivity extends BaseActivity implements EtTodayPlanContract.MvpView {
    public static final String EXTRA_INTENT_TYPE = "extra_intent_type";
    public static final String EXTRA_INTENT_TYPE_TODAY_ID = "extra_intent_type_today_id";
    public static final int INTENT_TYPE_ADD_PLAN = 0;
    public static final int INTENT_TYPE_EDIT_PLAN = 1;

    private EtTodayPlanPresenter mPresenter;
    private ActivityEtTodayPlanBinding mBinding;
    private List<BaseTodayPlanModel> mData;
    private EtTodayPlanAdapter mAdapter;
    private int mImportant = TimeConstant.DEFAUT_IMPORTANT, mUrgent = TimeConstant.DEFAUT_URGENT;
    public int mSelectStartHour = TimeConstant.DEFAUT_START_HOUR, mSelectStartMin = TimeConstant.DEFAUT_START_MIN;
    public int mSelectEndHour = TimeConstant.DEFAUT_END_HOUR, mSelectEndMin = TimeConstant.DEFAUT_END_MIN;

    public static void start(Context context) {
        start(context, INTENT_TYPE_ADD_PLAN, -1);
    }

    public static void start(Context context, int intentType, long todayId) {
        Intent start = new Intent(context, EtTodayPlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_INTENT_TYPE, intentType);
        start.putExtra(EXTRA_INTENT_TYPE_TODAY_ID, todayId);
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
        initIntent(getIntent());
    }

    private void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        int intentType = intent.getIntExtra(EXTRA_INTENT_TYPE, -1);
        switch (intentType) {
            case -1: {
                break;
            }
            case INTENT_TYPE_ADD_PLAN: {
                break;
            }
            case INTENT_TYPE_EDIT_PLAN: {
                long id = intent.getLongExtra(EXTRA_INTENT_TYPE_TODAY_ID, -1);
                mPresenter.initEditData(id);
                break;
            }
        }
    }

    private void initListener() {
        mBinding.btnAddMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                String tips = mBinding.etTips.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mBinding.etPlan.setText("");
                    mPresenter.addMainTodayPlan(TodayPlanUtils.getMainTodayPlan(content, tips,
                            mImportant, mUrgent, mSelectStartHour, mSelectStartMin, mSelectEndHour, mSelectEndMin));
                } else {
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
                    mPresenter.addSecondTodayPlan(TodayPlanUtils.getSecondTodayPlan(content, tips,
                            mImportant, mUrgent, mSelectStartHour, mSelectStartMin, mSelectEndHour, mSelectEndMin));
                } else {
                    ToastHelper.shortToast("啥计划也没写呀？");
                }
            }
        });
        mBinding.bbUrgent.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mUrgent = progress;
            }
        });

        mBinding.bbImportant.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mImportant = progress;
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
        mSelectEndHour = event.mModel.mSelectEndHour;
        mSelectEndMin = event.mModel.mSelectEndMin;
        mSelectStartHour = event.mModel.mSelectStartHour;
        mSelectStartMin = event.mModel.mSelectStartMin;

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

    @Override
    public void initEditData(List<BaseTodayPlanModel> data) {
        mAdapter.setData(data);
    }
}
