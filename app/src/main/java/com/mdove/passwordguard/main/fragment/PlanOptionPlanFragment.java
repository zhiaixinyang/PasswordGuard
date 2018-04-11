package com.mdove.passwordguard.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentPlanOptionBinding;
import com.mdove.passwordguard.main.adapter.DailyPlanOptionAdapter;
import com.mdove.passwordguard.main.model.DailyPlanOptionInfo;
import com.mdove.passwordguard.main.presenter.DailyPlanOptionPresenter;
import com.mdove.passwordguard.main.presenter.contract.DailyPlanOptionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/9.
 */
public class PlanOptionPlanFragment extends Fragment implements DailyPlanOptionContract.MvpView {
    private FragmentPlanOptionBinding mBinding;
    private DailyPlanOptionPresenter mPresenter;
    private DailyPlanOptionAdapter mAdapter;
    private List<DailyPlanOptionInfo> mData;

    public static PlanOptionPlanFragment newInstance() {
        PlanOptionPlanFragment fragment = new PlanOptionPlanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_plan_option, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new DailyPlanOptionPresenter();
        mPresenter.subscribe(this);

        mData = new ArrayList<>();
        mAdapter = new DailyPlanOptionAdapter(mData, mPresenter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        mBinding.rlvOptions.setLayoutManager(gridLayoutManager);
        mBinding.rlvOptions.setAdapter(mAdapter);

        mPresenter.initDailyPlan();
    }

    @Override
    public void initDailyPlan(List<DailyPlanOptionInfo> data) {
        mData = data;
        mAdapter.setData(data);
    }
}
