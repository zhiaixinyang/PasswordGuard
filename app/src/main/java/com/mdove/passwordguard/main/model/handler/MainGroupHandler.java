package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/2/16.
 */

public class MainGroupHandler {
    private MainPresenter mPresenter;

    public MainGroupHandler(){}

    public MainGroupHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnAddGroup() {
        if (mPresenter==null){
            return;
        }
        mPresenter.onClickBtnAddGroup();
    }

    public void onClickBtnHideGroup() {
        if (mPresenter==null){
            return;
        }
        mPresenter.onClickBtnHideGroup();
    }

    public void onClickBtnSetting() {
        if (mPresenter==null){
            return;
        }
        mPresenter.onClickBtnSetting();
    }

}
