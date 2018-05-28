package com.mdove.passwordguard.task.model.handle;

import android.view.View;
import android.widget.EditText;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.AllSelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;
import com.mdove.passwordguard.utils.ToastHelper;

/**
 * Created by MDove on 2018/3/27.
 */

public class AllSelfTaskHandler {
    private AllSelfTaskPresenter mPresenter;

    public AllSelfTaskHandler(AllSelfTaskPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(SelfTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

    public void onClickSee(SelfTaskModelVM vm) {
        mPresenter.onClickSee(vm);
    }

    public void onClickBtnEdit(View editText, SelfTaskModelVM vm) {
        boolean isChange = true;
        if (vm.mTask.get().equals(vm.mSelfTaskModel.mSelfTask.mTask)) {
            isChange = false;
        }
        editText.clearFocus();
        mPresenter.onClickBtnEdit(vm, isChange);
    }

    public void onClickDelete(SelfTaskModelVM vm) {
        mPresenter.onClickDelete(vm);
    }

    public void onClickPriority(SelfTaskModelVM vm) {
        mPresenter.onClickPriority(vm);
    }

    public void onClickCopy(SelfTaskModelVM vm) {
        mPresenter.onClickCopy(vm);
    }

    public void onClickLabelSetting(){
        mPresenter.onClickLabelSetting();
    }
}
