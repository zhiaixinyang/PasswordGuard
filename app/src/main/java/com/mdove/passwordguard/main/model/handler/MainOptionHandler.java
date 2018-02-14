package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/2/10.
 */

public class MainOptionHandler {
    private MainPresenter mPresenter;

    public MainOptionHandler(MainPresenter presenter) {
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
