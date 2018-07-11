package com.mdove.passwordguard.home.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
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
import com.mdove.passwordguard.ui.refresh.refreshLoad.DefaultLoadCreator;
import com.mdove.passwordguard.ui.refresh.refreshLoad.DefaultRefreshCreator;
import com.mdove.passwordguard.ui.refresh.widget.LoadRefreshRecyclerView;
import com.mdove.passwordguard.ui.refresh.widget.RefreshRecyclerView;
import com.mdove.passwordguard.ui.superrecyclerview.OnMoreListener;

import java.util.List;

public class HomeMessFragment extends Fragment implements HomeMessContract.MvpView,
        RefreshRecyclerView.OnRefreshListener, LoadRefreshRecyclerView.OnLoadMoreListener {
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

        mBinding.rlv.addRefreshViewCreator(new DefaultRefreshCreator());
        mBinding.rlv.addLoadViewCreator(new DefaultLoadCreator());
        mBinding.rlv.setOnRefreshListener(this);
        mBinding.rlv.setOnLoadMoreListener(this);

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
    public void onRefreshSuc(List<BaseHomeMessModel> data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.rlv.onStopRefresh();
            }
        }, 500);

        if (data != null) {
            mAdapter.setData(data);
        }
    }

    @Override
    public void onLoadMore(List<BaseHomeMessModel> data) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.rlv.onStopLoad();
            }
        }, 500);
        mAdapter.notifyLoadMore(data);
    }

    @Override
    public void onRefresh() {
        mPresenter.initData();
    }

    @Override
    public void onLoad() {
        mPresenter.loadMore();
    }
}
