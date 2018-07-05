package com.mdove.passwordguard.home.todayreview.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.presenter.contract.CustomReViewContract;

import java.util.ArrayList;
import java.util.List;

public class CustomReViewPresenter implements CustomReViewContract.Presenter {
    private CustomReViewContract.MvpView mView;
    private CustomReViewDao mCustomReViewDao;
    private List<CustomReViewModel> mData;

    public CustomReViewPresenter() {
        mCustomReViewDao = App.getDaoSession().getCustomReViewDao();
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        List<CustomReView> data = mCustomReViewDao.queryBuilder().list();

        for (CustomReView customReView : data) {
            mData.add(new CustomReViewModel(customReView));
        }

        mView.initData(mData);
    }

    @Override
    public void subscribe(CustomReViewContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
