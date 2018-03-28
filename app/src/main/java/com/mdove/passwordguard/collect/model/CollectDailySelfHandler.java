package com.mdove.passwordguard.collect.model;

import com.mdove.passwordguard.collect.presenter.CollectPresenter;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectDailySelfHandler {
    private CollectPresenter mPresenter;

    public CollectDailySelfHandler(CollectPresenter presenter) {
        mPresenter = presenter;
    }

    public void copyDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.copyDailySelf(vm);
    }

    public void favoriteDailySelf(CollectDailySelfModelVM vm) {
//        mPresenter.favoriteDailySelf(vm);
    }

    public void onClickBtnDeleteDailySelf(CollectDailySelfModelVM vm) {
//        mPresenter.deleteDailySelf(vm);
    }
    public void onClickItemDailySelf(CollectDailySelfModelVM vm) {
//        mPresenter.onClickItemDailySelf(vm);
    }
}
