package com.mdove.passwordguard.alldata.model.handler;

import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllPasswordHandler {
    private AllPasswordPresenter mPresenter;

    public AllPasswordHandler(AllPasswordPresenter presenter) {
        mPresenter = presenter;
    }

    public void favoritePassword(ItemAllPasswordVM vm) {
        mPresenter.favoritePassword(vm);
    }

    public void btnHidePworDs(ItemAllPasswordVM vm) {
        mPresenter.btnHidePworDs(vm);
    }

    public void copyPasswordInUserName(ItemAllPasswordVM vm) {
        mPresenter.copyPasswordInUserName(vm);
    }

    public void copyPasswordInPassword(ItemAllPasswordVM vm) {
        mPresenter.copyPasswordInPassword(vm);
    }
}
