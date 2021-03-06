package com.mdove.passwordguard.addoralter.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.deletelist.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordPresenter implements AddPasswordContract.Presenter {
    private AddPasswordContract.MvpView mView;
    private GroupInfoDao mGroupDap;
    private PasswordDao mPasswordDao;
    private DeletedPasswordDao mDeletedPasswordDao;

    public AddPasswordPresenter() {
        mGroupDap = App.getDaoSession().getGroupInfoDao();
        mPasswordDao = App.getDaoSession().getPasswordDao();
        mDeletedPasswordDao = App.getDaoSession().getDeletedPasswordDao();
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
        List<AddPasswordGroupRlvModel> modelData = new ArrayList<>();
        modelData.add(new AddPasswordGroupRlvModel(AppConstant.DEFAULT_CHECK_GROUP_TITLE,
                AppConstant.DEFAULT_CHECK_GROUP_COLOR, new Date().getTime()));
        List<GroupInfo> data = mGroupDap.queryBuilder().list();
        for (GroupInfo info : data) {
            modelData.add(new AddPasswordGroupRlvModel(info));
        }
        mView.showGroup(modelData);
    }

    @Override
    public void addPassword(Password password) {
        mPasswordDao.insert(password);
    }

    @Override
    public void alterPassword(AlterPasswordModel model, int itemPosition) {
        mPasswordDao.update(model.mNeedEditPassword);
        mDeletedPasswordDao.insert(DeletedPasswordHelper.getDeletedPassword(model.mTempPassword));
    }
}
