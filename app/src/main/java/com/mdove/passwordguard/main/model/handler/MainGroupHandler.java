package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/2/16.
 */

public class MainGroupHandler {
    private MainPresenter mPresenter;

    public MainGroupHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnPassword() {
        mPresenter.onClickBtnPassword();
    }

    public void onClickBtnLock() {
        mPresenter.onClickBtnLock();
    }

    public void onClickBtnDelete() {
        mPresenter.onClickBtnDelete();
    }
}
