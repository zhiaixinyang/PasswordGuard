package com.mdove.passwordguard.collect.model;

import com.mdove.passwordguard.collect.presenter.CollectPresenter;

/**
 * Created by MDove on 2018/3/31.
 */

public class CollectPasswordHandler {
    private CollectPresenter mPresenter;

    public CollectPasswordHandler(CollectPresenter presenter) {
        mPresenter = presenter;
    }

    public void copyPassword(CollectPasswordModelVM vm) {
        mPresenter.copyPasswordInPassword(vm);
    }
    public void copyUserName(CollectPasswordModelVM vm) {
        mPresenter.copyPasswordInUserName(vm);
    }

    public void favoritePassword(CollectPasswordModelVM vm) {
        mPresenter.favoritePassword(vm);
    }

    public void onClickBtnDeleteDailySelf(CollectDailySelfModelVM vm) {
//        mPresenter.deleteDailySelf(vm);
    }
    public void onClickItemDailySelf(CollectDailySelfModelVM vm) {
//        mPresenter.onClickItemDailySelf(vm);
    }
}
