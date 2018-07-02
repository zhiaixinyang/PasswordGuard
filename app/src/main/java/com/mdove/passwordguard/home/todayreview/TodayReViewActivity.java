package com.mdove.passwordguard.home.todayreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityTodayReviewBinding;
import com.mdove.passwordguard.home.todayreview.adapter.ReViewPagerAdapter;
import com.mdove.passwordguard.home.todayreview.fragment.CustomReViewFragment;
import com.mdove.passwordguard.home.todayreview.fragment.ScheduleReViewFragment;
import com.mdove.passwordguard.home.todayreview.model.handler.ActivityTodayReViewHandler;
import com.mdove.passwordguard.home.todayreview.presenter.ActivityTodayReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.contract.ActivityTodayReViewContract;
import com.mdove.passwordguard.home.todayreview.presenter.contract.TodayReViewContract;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/29.
 */

public class TodayReViewActivity extends BaseActivity implements ActivityTodayReViewContract.MvpView{
    private static final String EXTRA_TODAY_PLAN_ID = "extra_today_plan_id";
    private ActivityTodayReviewBinding mBinding;
    private long mTodayPlanId;
    private ActivityTodayReViewPresenter mPresenter;
    private ScheduleReViewFragment mScheduleReViewFragment;
    private CustomReViewFragment mCustomReViewFragment;
    private String[] mTitle = {"复盘计划", "独立复盘"};

    public static void start(Context context) {
        start(context, -1L);
    }

    public static void start(Context context, long id) {
        Intent start = new Intent(context, TodayReViewActivity.class);
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
        mPresenter=new ActivityTodayReViewPresenter();
        mPresenter.subscribe(this);
        mBinding.setHandler(new ActivityTodayReViewHandler(mPresenter));

        mTodayPlanId = getIntent().getLongExtra(EXTRA_TODAY_PLAN_ID, -1L);

        mScheduleReViewFragment = ScheduleReViewFragment.newInstance(mTodayPlanId);
        mCustomReViewFragment = CustomReViewFragment.newInstance();
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(mScheduleReViewFragment);
        mFragments.add(mCustomReViewFragment);

        mBinding.vp.setAdapter(new ReViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitle));
        mBinding.tb.setupWithViewPager(mBinding.vp);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
