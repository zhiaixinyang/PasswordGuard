package com.mdove.passwordguard.main.newmain.dailytask;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentDailyTaskBinding;
import com.mdove.passwordguard.main.newmain.dailytask.adapter.MainSelfTaskAdapter;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.MainSelfTaskEtDialog;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.MainSelfTaskPresenter;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.MainSelfTaskContract;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSeeEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskFinishEvent;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class MainSelfTaskFragment extends Fragment implements MainSelfTaskContract.MvpView {
    private FragmentDailyTaskBinding mBinding;
    private Dialog mDialog;
    private MainSelfTaskEtDialog mDialogN;
    private int previousKeyboardHeight = -1;
    private MainSelfTaskPresenter mPresenter;
    private MainSelfTaskAdapter mAdapter;
    private List<SelfTaskModel> mData;

    private FrameLayout mBtnSend;
    private EditText mEt;

    public static MainSelfTaskFragment newInstance() {
        Bundle args = new Bundle();

        MainSelfTaskFragment fragment = new MainSelfTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_task, container, false);
        mBinding.layoutEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogN != null) {
                    mDialogN.show();
                }
            }
        });

        RxBus.get().register(this);
        return mBinding.getRoot();
    }

    private void updateUI() {
        if (mPresenter!=null){
            mPresenter.initData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                long displayHeight = rect.bottom - rect.top;
                long height = getActivity().getWindow().getDecorView().getHeight();
                long keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (float) displayHeight / (float) height > 0.8;
                    if (hide && mDialogN != null) {
                        mDialogN.dismiss();
                    }
                }
            }
        });

        initDialog();

        mData = new ArrayList<>();
        mPresenter = new MainSelfTaskPresenter();
        mPresenter.subscribe(this);
        mAdapter = new MainSelfTaskAdapter(getContext(), mPresenter);
        mBinding.rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlv.setAdapter(mAdapter);
        mPresenter.initData();
    }

    private void initDialog() {
        mDialogN = new MainSelfTaskEtDialog(getActivity());
        mDialogN.setOnClickSendListener(new MainSelfTaskEtDialog.OnClickSendListener() {
            @Override
            public void onClickSend(String content, LabelTempModel tempModel) {
                mPresenter.insertSelfTask(content, tempModel);
            }
        });
        mDialogN.setOnClickLabelSelectListener(new MainSelfTaskEtDialog.OnClickLabelSelectListener() {
            @Override
            public void onClickLabel(String content, long id) {
                ToastHelper.shortToast(content);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void insertSelfTask(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void initData(List<SelfTaskModel> data) {
        mAdapter.setData(data);
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
        mAdapter.notifyDelete(position);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.notifyDeleteSelfTask(position);
    }

    @Override
    public void onClickBtnEdit(int position) {
        mAdapter.notifyPosition(position);
    }

    @Subscribe
    public void selfTaskClickSuc(SelfTaskClickSucEvent event) {
        mAdapter.notifyEventSelfTaskClickSuc(event.mId, event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickSee(SelfTaskClickSeeEvent event) {
        mAdapter.notifyEventSelfTaskClickSee(event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickDelete(SelfTaskClickDeleteEvent event) {
        mAdapter.notifyEventSelfTaskClickDelete(event.mId);
    }

    @Subscribe
    public void selfTaskFinishEvnet(SelfTaskFinishEvent event) {
        updateUI();
    }

    @Subscribe
    public void selfTaskClickPriority(SelfTaskClickPriorityEvent event) {
        mAdapter.notifyEventSelfTaskClickPriority(event.mId, event.mSelfTaskModel.mPriority);
    }
}
