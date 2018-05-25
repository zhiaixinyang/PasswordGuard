package com.mdove.passwordguard.main.newmain.dailytask;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentDailyTaskBinding;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.DailyTaskContract;
import com.mdove.passwordguard.task.model.SelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class DailyTaskFragment extends Fragment implements DailyTaskContract.MvpView{
    private FragmentDailyTaskBinding mBinding;
    private Dialog mDialog;
    private int previousKeyboardHeight = -1;

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
                mDialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                mDialog.setContentView(R.layout.dialog_daily_task_et);
                mDialog.show();
            }
        });
        return mBinding.getRoot();
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
                    if (hide && mDialog != null) {
                        mDialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void insertSelfTask(int position) {

    }

    @Override
    public void initData(List<SelfTaskModel> data) {

    }
}
