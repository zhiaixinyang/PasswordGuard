package com.mdove.passwordguard.dailyself;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/2/22.
 */

public class MainDailySelfHandler {
    private MainPresenter mPresenter;

    public MainDailySelfHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void copyDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.copyDailySelf(vm);
    }

    public void onClickBtnDeleteDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.onClickBtnDeleteDailySelf(vm);
    }
}
