package com.mdove.passwordguard.addoralter.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.addoralter.presenter.contract.AddDailySelfContract;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.entity.GroupInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfPresenter implements AddDailySelfContract.Presenter {
    private AddDailySelfContract.MvpView mView;
    private GroupInfoDao mGroupDap;

    public AddDailySelfPresenter() {
        mGroupDap = App.getDaoSession().getGroupInfoDao();
    }

    @Override
    public void subscribe(AddDailySelfContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initGroup() {
        List<AddDailySelfGroupRlvModel> modelData = new ArrayList<>();
        modelData.add(new AddDailySelfGroupRlvModel(AppConstant.DEFAULT_CHECK_GROUP_TITLE,
                AppConstant.DEFAULT_CHECK_GROUP_COLOR, new Date().getTime()));
        modelData.add(new AddDailySelfGroupRlvModel(AppConstant.DEFAULT_DAILY_SELF_TV_GROUP,
                AppConstant.DEFAULT_DAILY_SELF_TV_GROUP_COLOR, new Date().getTime()));
        List<GroupInfo> data = mGroupDap.queryBuilder().list();
        for (GroupInfo info : data) {
            modelData.add(new AddDailySelfGroupRlvModel(info));
        }
        mView.showGroup(modelData);
    }
}
