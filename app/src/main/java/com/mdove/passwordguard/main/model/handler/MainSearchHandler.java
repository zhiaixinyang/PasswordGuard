package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/2/14.
 */

public class MainSearchHandler {
    private MainPresenter mPresenter;

    public MainSearchHandler() {
    }

    public MainSearchHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnSearch() {
        if (mPresenter == null) {
            return;
        }
        mPresenter.onClickBtnSearch();
    }

    public void onClickBtnHideSearch() {
        if (mPresenter == null) {
            return;
        }
        mPresenter.onClickBtnHideSearch();
    }
}
