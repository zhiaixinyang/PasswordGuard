package com.mdove.passwordguard.home.alltodayplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityAllTodayPlanBinding;
import com.mdove.passwordguard.home.alltodayplan.adapter.AllTodayPlanAdapter;
import com.mdove.passwordguard.home.alltodayplan.model.AllTodayPlanHandler;
import com.mdove.passwordguard.home.alltodayplan.presenter.AllTodayPlanPresenter;
import com.mdove.passwordguard.home.alltodayplan.presenter.contract.AllTodayPlanContract;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class AllTodayPlanActivity extends BaseActivity implements AllTodayPlanContract.MvpView {
    public static final String EXTRA_PLAN_ID = "extra_plan_id";
    private AllTodayPlanPresenter mPlanPresenter;
    private ActivityAllTodayPlanBinding mBinding;
    private AllTodayPlanAdapter mAdapter;
    private List<BaseTodayPlanModel> mData;

    public static void start(Context context, long todayId) {
        Intent start = new Intent(context, AllTodayPlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_PLAN_ID, todayId);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_all_today_plan);
        StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.gray_new_home));
        long id = getIntent().getLongExtra(EXTRA_PLAN_ID, -1);

        mPlanPresenter = new AllTodayPlanPresenter();
        mPlanPresenter.subscribe(this);
        mBinding.setHandler(new AllTodayPlanHandler(mPlanPresenter));

        mData = new ArrayList<>();
        mAdapter = new AllTodayPlanAdapter(this, mData);
        mBinding.rlvAllPlan.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rlvAllPlan.setAdapter(mAdapter);
        if (id != -1) {
            mPlanPresenter.initData(id);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<BaseTodayPlanModel> data) {
        mAdapter.setData(data);
    }
}
