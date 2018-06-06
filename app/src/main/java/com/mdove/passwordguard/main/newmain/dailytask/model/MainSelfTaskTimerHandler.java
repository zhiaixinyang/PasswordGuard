package com.mdove.passwordguard.main.newmain.dailytask.model;


import com.mdove.passwordguard.main.newmain.dailytask.presenter.MainSelfTaskPresenter;

/**
 * Created by MDove on 2018/6/6.
 */

public class MainSelfTaskTimerHandler {
    private MainSelfTaskPresenter mPresenter;

    public MainSelfTaskTimerHandler(MainSelfTaskPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(MainSelfTaskTimerModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

    public void onClickDelete(MainSelfTaskTimerModelVM vm) {
        mPresenter.onClickDelete(vm);
    }
}
