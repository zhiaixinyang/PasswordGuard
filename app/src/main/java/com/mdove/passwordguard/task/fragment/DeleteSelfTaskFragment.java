package com.mdove.passwordguard.task.fragment;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.mdove.passwordguard.databinding.FragmentDeleteSelfTaskBinding;
import com.mdove.passwordguard.task.adapter.DeleteSelfTaskAdapter;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModelVM;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
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
    private TextView mLayoutEmpty;

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
        RxBus.get().register(this);

        mRlv = mBinding.rlvDeleteSelfTask;
        mLayoutEmpty = mBinding.layoutEmpty;

        mPresenter = new DeleteSelfTaskPresenter();
        mPresenter.subscribe(this);
        mAdapter = new DeleteSelfTaskAdapter(getContext(), mPresenter);
        mAdapter.setOnChangeDataSizeListener(new OnChangeDataSizeListener() {
            @Override
            public void dataIsEmpty(boolean isEmpty) {
                if (isEmpty) {
                    mLayoutEmpty.setVisibility(View.VISIBLE);
                } else {
                    mLayoutEmpty.setVisibility(View.GONE);
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
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!dataExisted()) {
                mPresenter.initData();
            }
        }
    }

    private boolean dataExisted() {
        if (mAdapter != null) {
            return mAdapter.getItemCount() > 0;
        }
        return false;
    }

    @Override
    public void initData(List<DeleteSelfTaskModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.onClickTaskDelete(position);
    }

    @Override
    public void realDelete(int position) {
        mAdapter.notifyDeleteReturn(position);
    }

    @Override
    public void warningDeleteDialog(final DeleteSelfTaskModelVM vm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("确定要永久删除？");
        builder.setMessage("真的要把这条记录删掉么？");
        builder.setNegativeButton("手滑了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("删！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.realDelete(vm);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Subscribe
    public void selfTaskClickDelete(SelfTaskClickDeleteEvent event) {
        mPresenter.onClickDelete(event.mId);
    }
}
