package com.mdove.passwordguard.main.newmain.dailytask.model;

import android.view.View;

import com.mdove.passwordguard.main.newmain.dailytask.presenter.MainSelfTaskPresenter;

/**
 * Created by MDove on 2018/5/26.
 */

public class MainSelfTaskHandler {
    private MainSelfTaskPresenter mPresenter;

    public MainSelfTaskHandler(MainSelfTaskPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(MainSelfTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

    public void onClickSee(MainSelfTaskModelVM vm) {
        mPresenter.onClickSee(vm);
    }

    public void onClickBtnEdit(View editText, MainSelfTaskModelVM vm) {
        boolean isChange = true;
        if (vm.mTask.get().equals(vm.mSelfTaskModel.mSelfTask.mTask)) {
            isChange = false;
        }
        editText.clearFocus();
        mPresenter.onClickBtnEdit(vm, isChange);
    }

    public void onClickDelete(MainSelfTaskModelVM vm) {
        mPresenter.onClickDelete(vm);
    }

    public void onClickPriority(MainSelfTaskModelVM vm) {
        mPresenter.onClickPriority(vm);
    }

    public void onClickCopy(MainSelfTaskModelVM vm) {
        mPresenter.onClickCopy(vm);
    }
}
