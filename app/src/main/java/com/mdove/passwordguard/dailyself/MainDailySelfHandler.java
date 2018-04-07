package com.mdove.passwordguard.dailyself;

import com.mdove.passwordguard.base.IHideVM;
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

    public void favoriteDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.favoriteDailySelf(vm);
    }

    public void btnHide(IHideVM vm) {
        mPresenter.btnHidePworDs(vm);
    }

    public void onClickBtnDeleteDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.deleteDailySelf(vm);
    }
    public void onClickItemDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.onClickItemDailySelf(vm);
    }
}
