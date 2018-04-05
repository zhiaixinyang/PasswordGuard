package com.mdove.passwordguard.setting.model;

import com.mdove.passwordguard.setting.presenter.SettingPresenter;

/**
 * Created by MDove on 2018/4/5.
 */

public class SettingHandler {
    private SettingPresenter mPresenter;

    public SettingHandler(SettingPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnHideGroup() {
        mPresenter.onClickBtnHideGroup();
    }

    public void onClickBtnHideSearch() {
        mPresenter.onClickBtnHideSearch();
    }

    public void onClickBtnHideTimeTop() {
        mPresenter.onClickBtnHideTimeTop();
    }

    public void onClickBtnHideOption() {
        mPresenter.onClickBtnHideOption();
    }
}
