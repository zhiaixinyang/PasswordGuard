package com.mdove.passwordguard.home.todayreview;

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
import com.mdove.passwordguard.databinding.ActivityTodayReviewBinding;
import com.mdove.passwordguard.home.todayreview.adapter.TodayReViewAdapter;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.handler.TodayReViewHandler;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.contract.TodayReViewContract;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/29.
 */

public class TodayReViewActivityOld extends BaseActivity implements TodayReViewContract.MvpView {
    private static final String EXTRA_TODAY_PLAN_ID = "extra_today_plan_id";
    private ActivityTodayReviewBinding mBinding;
    private TodayReViewAdapter mAdapter;
    private TodayReViewPresenter mPresenter;
    private List<BaseTodayReViewModel> mData;
    private long mTodayPlanId;

    public static void start(Context context, long id) {
        Intent start = new Intent(context, TodayReViewActivityOld.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_TODAY_PLAN_ID, id);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_today_review);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));

        mTodayPlanId = getIntent().getLongExtra(EXTRA_TODAY_PLAN_ID, -1L);

        mPresenter = new TodayReViewPresenter();
        mPresenter.subscribe(this);

        mData = new ArrayList<>();
        mAdapter = new TodayReViewAdapter(this, mData, mPresenter);

        mBinding.rlvReview.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rlvReview.setAdapter(mAdapter);
        mBinding.setHandler(new TodayReViewHandler(mPresenter));

        mPresenter.initData(mTodayPlanId);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<BaseTodayReViewModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void addMainTodayReViewReturn(MainTodayReViewModel model) {

    }

    @Override
    public void addMainTodayReViewReturn(SecondTodayReViewModel model) {

    }

    @Override
    public void onClickTodayReViewSuc(int position) {
        mAdapter.notifyItemChanged(position);
    }
}
