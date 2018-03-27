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

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentAllSelfTaskBinding;
import com.mdove.passwordguard.databinding.FragmentSucSelfTaskBinding;
import com.mdove.passwordguard.task.adapter.SucSelfTaskAdapter;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;
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
        mRlv = mBinding.rlvSucSelfTask;
        mPresenter = new SucSelfTaskPresenter();
        mPresenter.subscribe(this);

        mAdapter = new SucSelfTaskAdapter(getContext(), mPresenter);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (!dataExisted()) {
                mPresenter.initData();
            }
        }
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

    private boolean dataExisted() {
        if (mAdapter != null) {
            return mAdapter.getItemCount() > 0;
        }
        return false;
    }
}
