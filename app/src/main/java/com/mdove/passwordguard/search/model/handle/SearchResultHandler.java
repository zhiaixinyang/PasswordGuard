package com.mdove.passwordguard.search.model.handle;

import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.search.presenter.SearchResultPresenter;

/**
 * Created by MDove on 2018/3/24.
 */

public class SearchResultHandler {
    private SearchResultPresenter mPresenter;

    public SearchResultHandler(SearchResultPresenter presenter) {
        mPresenter = presenter;
    }

    public void copyDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.copyDailySelf(vm);
    }

    public void copyPasswordInPassword(ItemMainPasswordVM vm){
        mPresenter.copyPasswordInPassword(vm);
    }

    public void copyPasswordInUserName(ItemMainPasswordVM vm){
        mPresenter.copyPasswordInUserName(vm);
    }

    public void favoriteDailySelf(ItemMainDailySelfVM vm) {
        mPresenter.favoriteDailySelf(vm);
    }

}
