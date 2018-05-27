package com.mdove.passwordguard.main.newmain.dailytask.model;

import android.view.View;

import com.mdove.passwordguard.main.newmain.dailytask.presenter.DailyTaskPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.AllSelfTaskPresenter;

/**
 * Created by MDove on 2018/5/26.
 */

public class DailyTaskHandler {
    private DailyTaskPresenter mPresenter;

    public DailyTaskHandler(DailyTaskPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(DailyTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

    public void onClickSee(DailyTaskModelVM vm) {
        mPresenter.onClickSee(vm);
    }

    public void onClickBtnEdit(View editText, DailyTaskModelVM vm) {
        boolean isChange = true;
        if (vm.mTask.get().equals(vm.mSelfTaskModel.mSelfTask.mTask)) {
            isChange = false;
        }
        editText.clearFocus();
        mPresenter.onClickBtnEdit(vm, isChange);
    }

    public void onClickDelete(DailyTaskModelVM vm) {
        mPresenter.onClickDelete(vm);
    }

    public void onClickPriority(DailyTaskModelVM vm) {
        mPresenter.onClickPriority(vm);
    }

    public void onClickCopy(DailyTaskModelVM vm) {
        mPresenter.onClickCopy(vm);
    }
}
