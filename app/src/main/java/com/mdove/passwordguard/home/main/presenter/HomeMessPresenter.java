package com.mdove.passwordguard.home.main.presenter;

import com.mdove.passwordguard.home.main.model.BaseHomeMessModel;
import com.mdove.passwordguard.home.main.presenter.contract.HomeMessContract;

import java.util.List;

public class HomeMessPresenter implements HomeMessContract.Presenter {
    private HomeMessContract.MvpView mView;
    private List<BaseHomeMessModel> mData;

    public HomeMessPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void subscribe(HomeMessContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
