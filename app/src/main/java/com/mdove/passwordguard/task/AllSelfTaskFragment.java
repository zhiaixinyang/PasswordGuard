package com.mdove.passwordguard.task;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentAllSelfTaskBinding;
import com.mdove.passwordguard.task.adapter.AllSelfTaskAdapter;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.presenter.AllSelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.contract.AllSelfTaskContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class AllSelfTaskFragment extends Fragment implements AllSelfTaskContract.MvpView {
    private FragmentAllSelfTaskBinding mBinding;
    private EditText mEt;
    private TextView mBtn;
    private RecyclerView mRlv;
    private AllSelfTaskPresenter mPresenter;
    private AllSelfTaskAdapter mAdapter;

    public static AllSelfTaskFragment newInstance() {
        AllSelfTaskFragment fragment = new AllSelfTaskFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_self_task, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEt = mBinding.etContent;
        mBtn = mBinding.btnSend;
        mRlv = mBinding.rlvSelfTask;

        mPresenter = new AllSelfTaskPresenter();
        mPresenter.subscribe(this);
        mAdapter = new AllSelfTaskAdapter(getContext(), mPresenter);

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.setAdapter(mAdapter);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEt.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.insertSelfTask(content);
                    mEt.setText("");
                    return;
                }
                ToastHelper.shortToast("怎么能添加一个空的工作内容");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!dataExisted()) {
            mPresenter.initData();
        }
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public void insertSelfTask(int position) {
        mAdapter.notifyInsertPosition(position);
    }

    @Override
    public void notifySelfTaskIsSuc(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifySelfTaskPriority(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifySelfSee(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void initData(List<SelfTaskModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.notifyDeleteSelfTask(position);
    }

    private boolean dataExisted() {
        if (mAdapter != null) {
            return mAdapter.getItemCount() > 0;
        }
        return false;
    }
}
