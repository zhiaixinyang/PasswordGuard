package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskHandler {
    private MainPresenter mPresenter;

    public MainSelfTaskHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(SelfTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

    public void onClickSee(SelfTaskModelVM vm) {
        mPresenter.onClickSee(vm);
    }

    public void onClickPriority(SelfTaskModelVM vm) {
        mPresenter.onClickPriority(vm);
    }

    public void onClickCopy(SelfTaskModelVM vm) {
        mPresenter.onClickCopyTaskSelf(vm);
    }
}
