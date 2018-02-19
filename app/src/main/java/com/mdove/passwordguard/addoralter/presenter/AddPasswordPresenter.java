package com.mdove.passwordguard.addoralter.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.entity.GroupInfo;

import java.util.List;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordPresenter implements AddPasswordContract.Presenter {
    private AddPasswordContract.MvpView mView;
    private GroupInfoDao mGroupDap;

    public AddPasswordPresenter() {
        mGroupDap = App.getDaoSession().getGroupInfoDao();
    }

    @Override
    public void subscribe(AddPasswordContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initGroup() {
        List<GroupInfo> data = mGroupDap.queryBuilder().list();
        mView.showGroup(data);
    }
}
