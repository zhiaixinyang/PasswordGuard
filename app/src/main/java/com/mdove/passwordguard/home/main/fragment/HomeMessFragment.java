package com.mdove.passwordguard.home.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentHomeMessBinding;
import com.mdove.passwordguard.home.main.adapter.HomeMessAdapter;
import com.mdove.passwordguard.home.main.model.BaseHomeMessModel;
import com.mdove.passwordguard.home.main.presenter.HomeMessPresenter;
import com.mdove.passwordguard.home.main.presenter.contract.HomeMessContract;
import com.mdove.passwordguard.ui.superrecyclerview.OnMoreListener;

import java.util.List;

public class HomeMessFragment extends Fragment implements HomeMessContract.MvpView, SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeMessBinding mBinding;
    private HomeMessAdapter mAdapter;
    private HomeMessPresenter mPresenter;

    public static HomeMessFragment newInstance() {

        Bundle args = new Bundle();

        HomeMessFragment fragment = new HomeMessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_mess, container, false);
        mBinding.srf.setOnRefreshListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomeMessPresenter();
        mPresenter.subscribe(this);

        mAdapter = new HomeMessAdapter(getContext());
        mBinding.rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlv.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null && mAdapter != null) {
            mPresenter.initData();
        }
    }

    @Override
    public void initData(List<BaseHomeMessModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onRefreshSuc(List<BaseHomeMessModel> data) {
        mBinding.srf.setRefreshing(false);
        if (data != null) {
            mAdapter.setData(data);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.initData();
    }
}
