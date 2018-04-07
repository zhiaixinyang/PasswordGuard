package com.mdove.passwordguard.alldata.model.handler;

import com.mdove.passwordguard.alldata.model.vm.ItemAllDailySelfVM;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.AllDailySelfPresenter;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfHandler {
    private AllDailySelfPresenter mPresenter;

    public AllDailySelfHandler(AllDailySelfPresenter presenter) {
        mPresenter = presenter;
    }

    public void favoriteDailySelf(ItemAllDailySelfVM vm) {
        mPresenter.favoriteDailySelf(vm);
    }

    public void btnHidePworDs(ItemAllDailySelfVM vm) {
        mPresenter.btnHidePworDs(vm);
    }

    public void copyDailySelf(ItemAllDailySelfVM vm) {
        mPresenter.copyDailySelf(vm);
    }

}