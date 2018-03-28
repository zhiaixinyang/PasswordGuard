package com.mdove.passwordguard.task.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.FragmentSucSelfTaskBinding;
import com.mdove.passwordguard.task.adapter.SucSelfTaskAdapter;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.presenter.SucSelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.contract.SucSelfTaskContract;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskFragment extends Fragment implements SucSelfTaskContract.MvpView {
    private FragmentSucSelfTaskBinding mBinding;
    private RecyclerView mRlv;
    private SucSelfTaskPresenter mPresenter;
    private SucSelfTaskAdapter mAdapter;
    private TextView mLayoutEmpty;

    public static SucSelfTaskFragment newInstance() {
        SucSelfTaskFragment fragment = new SucSelfTaskFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_suc_self_task, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RxBus.get().register(this);

        mRlv = mBinding.rlvSucSelfTask;
        mLayoutEmpty = mBinding.layoutEmpty;
        mPresenter = new SucSelfTaskPresenter();
        mPresenter.subscribe(this);

        mAdapter = new SucSelfTaskAdapter(getContext(), mPresenter);
        mAdapter.setOnChangeDataSizeListener(new OnChangeDataSizeListener() {
            @Override
            public void dataIsEmpty(boolean isEmpty) {
                if (isEmpty) {
                    mLayoutEmpty.setVisibility(View.GONE);
                } else {
                    mLayoutEmpty.setVisibility(View.VISIBLE);
                }
            }
        });

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!dataExisted()) {
            mPresenter.initData();
        }
    }

    @Override
    public void initData(List<SucSelfTaskModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickSuc(int position, boolean isSuc) {
        mAdapter.onClickTaskSuc(position, isSuc);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.onClickTaskDelete(position);
    }

    @Override
    public void onClickPriority(int position) {
        mAdapter.onClickTaskPriority(position);
    }

    private boolean dataExisted() {
        if (mAdapter != null) {
            return mAdapter.getItemCount() > 0;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Subscribe
    public void selfTaskClickSuc(SelfTaskClickSucEvent event) {
        mPresenter.onClickSuc(event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickDelete(SelfTaskClickDeleteEvent event) {
        mPresenter.onClickDelete(event.mId);
    }

    @Subscribe
    public void selfTaskClickPriority(SelfTaskClickPriorityEvent event) {
        mPresenter.onClickPriority(event.mSelfTaskModel);
    }
}
