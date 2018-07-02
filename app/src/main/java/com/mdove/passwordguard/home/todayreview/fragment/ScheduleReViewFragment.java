package com.mdove.passwordguard.home.todayreview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentTodayReviewBinding;
import com.mdove.passwordguard.home.todayreview.adapter.ScheduleReViewAdapter;
import com.mdove.passwordguard.home.todayreview.adapter.TodayReViewAdapter;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.contract.TodayReViewContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class ScheduleReViewFragment extends Fragment implements TodayReViewContract.MvpView {
    private static final String EXTRA_TODAY_PLAN_ID = "extra_today_plan_id";

    private FragmentTodayReviewBinding mBinding;
    private ScheduleReViewAdapter mAdapter;
    private TodayReViewPresenter mPresenter;
    private List<BaseTodayReViewModel> mData;
    private long mTodayPlanId = -1;

    public static ScheduleReViewFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(EXTRA_TODAY_PLAN_ID, id);

        ScheduleReViewFragment fragment = new ScheduleReViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_review, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTodayPlanId = getArguments().getLong(EXTRA_TODAY_PLAN_ID);

        mPresenter = new TodayReViewPresenter();
        mPresenter.subscribe(this);

        mData = new ArrayList<>();
        mAdapter = new ScheduleReViewAdapter(getContext(), mData, mPresenter);

        mBinding.rlvReview.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlvReview.setAdapter(mAdapter);

        mPresenter.initData(mTodayPlanId);
    }

    @Override
    public void initData(List<BaseTodayReViewModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void finish() {

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
