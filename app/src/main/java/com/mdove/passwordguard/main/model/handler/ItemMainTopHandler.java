package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/4/16.
 */

public class ItemMainTopHandler {
    public MainPresenter mPresenter;

    public ItemMainTopHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnStatistics() {
        mPresenter.onClickBtnStatistics();
    }

    public void onClickBtnHideTimeTop() {
        mPresenter.onClickBtnHideTimeTop();
    }
}
