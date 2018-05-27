package com.mdove.passwordguard.main.newmain.dailytask;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.mdove.passwordguard.greendao.entity.DailyTaskLabel;
import com.mdove.passwordguard.main.newmain.dailytask.adapter.DailyTaskAdapter;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.DailyTaskEtDialog;
import com.mdove.passwordguard.main.newmain.dailytask.model.DailyTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.DailyTaskPresenter;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.DailyTaskContract;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSeeEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class DailyTaskFragment extends Fragment implements DailyTaskContract.MvpView {
    private FragmentDailyTaskBinding mBinding;
    private Dialog mDialog;
    private DailyTaskEtDialog mDialogN;
    private int previousKeyboardHeight = -1;
    private DailyTaskPresenter mPresenter;
    private DailyTaskAdapter mAdapter;
    private List<SelfTaskModel> mData;

    private FrameLayout mBtnSend;
    private EditText mEt;

    public static DailyTaskFragment newInstance() {
        Bundle args = new Bundle();

        DailyTaskFragment fragment = new DailyTaskFragment();
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
//                    mDialog.show();
                    mDialogN.show();
                }
            }
        });

        RxBus.get().register(this);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
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
//                        mDialog.dismiss();
                    }
                }
            }
        });

        initDialog();

        mData = new ArrayList<>();
        mPresenter = new DailyTaskPresenter();
        mPresenter.subscribe(this);
        mAdapter = new DailyTaskAdapter(getContext(), mPresenter);
        mBinding.rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rlv.setAdapter(mAdapter);
        mPresenter.initData();
    }

    private void initDialog() {
//        mDialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
//        mDialog.setContentView(R.layout.dialog_daily_task_et);
//        mBtnSend = mDialog.findViewById(R.id.btn_send);
//        mEt = mDialog.findViewById(R.id.et_daily_task);
        mDialogN = new DailyTaskEtDialog(getActivity());
        mDialogN.setOnClickSendListener(new DailyTaskEtDialog.OnClickSendListener() {
            @Override
            public void onClickSend(String content) {
                mPresenter.insertSelfTask(content);
            }
        });
        mDialogN.setOnClickLabelSelectListener(new DailyTaskEtDialog.OnClickLabelSelectListener() {
            @Override
            public void onClickLabel(String content) {
                ToastHelper.shortToast(content);
            }
        });
//        mBtnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String content = mEt.getText().toString();
//                if (!TextUtils.isEmpty(content)) {
//                    mEt.setText("");
//                    mPresenter.insertSelfTask(content);
//                } else {
//                    ToastHelper.shortToast("计划怎么能为空呢?");
//                }
//            }
//        });
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
        mAdapter.notifyPosition(position);
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
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickSuc(event.mId, event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickSee(SelfTaskClickSeeEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickSee(event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickDelete(SelfTaskClickDeleteEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickDelete(event.mId);
    }

    @Subscribe
    public void selfTaskClickPriority(SelfTaskClickPriorityEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickPriority(event.mId, event.mSelfTaskModel.mPriority);
    }
}
