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
import com.mdove.passwordguard.databinding.FragmentDeleteSelfTaskBinding;
import com.mdove.passwordguard.deletelist.presenter.DeleteListPasswordContract;
import com.mdove.passwordguard.task.adapter.DeleteSelfTaskAdapter;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.presenter.DeleteSelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.contract.DeleteSelfTaskContract;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskFragment extends Fragment implements DeleteSelfTaskContract.MvpView {
    private FragmentDeleteSelfTaskBinding mBinding;
    private RecyclerView mRlv;
    private DeleteSelfTaskPresenter mPresenter;
    private DeleteSelfTaskAdapter mAdapter;

    public static DeleteSelfTaskFragment newInstance() {
        DeleteSelfTaskFragment fragment = new DeleteSelfTaskFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_self_task, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRlv = mBinding.rlvDeleteSelfTask;

        mPresenter = new DeleteSelfTaskPresenter();
        mPresenter.subscribe(this);
        mAdapter = new DeleteSelfTaskAdapter(getContext(), mPresenter);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.initData();
    }

    @Override
    public void initData(List<DeleteSelfTaskModel> data) {
        mAdapter.setData(data);
    }
}
