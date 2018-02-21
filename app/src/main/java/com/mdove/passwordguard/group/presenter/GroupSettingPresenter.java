package com.mdove.passwordguard.group.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.group.model.GroupSettingModel;
import com.mdove.passwordguard.group.presenter.contract.GroupSettingContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/2/21.
 */

public class GroupSettingPresenter implements GroupSettingContract.Presenter {
    private GroupSettingContract.MvpView mView;
    private GroupInfoDao mGroupDao;

    public GroupSettingPresenter() {
        mGroupDao = App.getDaoSession().getGroupInfoDao();
    }

    @Override
    public void subscribe(GroupSettingContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        List<GroupInfo> data = mGroupDao.queryBuilder().build().list();

        mView.showData(data);
    }

    @Override
    public void deleteGroup(GroupInfo info, int position) {
        mGroupDao.delete(info);
        mView.deleteSuc(position);
    }
}
