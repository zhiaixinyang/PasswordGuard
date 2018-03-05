package com.mdove.passwordguard.main.presenter;

import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.addoralter.EditPasswordActivity;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.deletelist.DeleteListDailySelfActivity;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.greendao.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.group.GroupSettingActivity;
import com.mdove.passwordguard.main.AddGroupDialog;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.manager.UpdateStatusManager;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.update.UpdateDialog;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

import static android.media.CamcorderProfile.get;

/**
 * Created by MDove on 2018/2/10.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.MvpView mView;
    private List<BaseMainModel> mData;
    private List<BaseMainModel> mSysEmptyData;
    private PasswordDao mDao;
    private DeletedPasswordDao mDeleteDao;
    private GroupInfoDao mGroupInfoDao;
    private DeletedDailySelfDao mDeleteDailyDao;
    private DailySelfDao mDailySelfDao;
    private List<MainGroupRlvModel> mGroupData;
    private List<BaseMainModel> mDailyData;
    private MainGroupModel mMainGroupModel;
    private List<GroupInfo> mCheckedList;
    private static final String DEFAULT_DAILY_SELF_TV_GROUP = AppConstant.DEFAULT_DAILY_SELF_TV_GROUP;
    private static final String DEFAULT_CHECK_GROUP_TITLE = AppConstant.DEFAULT_CHECK_GROUP_TITLE;

    @Override
    public void subscribe(MainContract.MvpView view) {
        mView = view;

        mCheckedList = new ArrayList<>();

        mDao = App.getDaoSession().getPasswordDao();
        mDeleteDao = App.getDaoSession().getDeletedPasswordDao();
        mGroupInfoDao = App.getDaoSession().getGroupInfoDao();
        mDailySelfDao = App.getDaoSession().getDailySelfDao();
        mDeleteDailyDao = App.getDaoSession().getDeletedDailySelfDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mSysEmptyData = new ArrayList<>();
        mGroupData = new ArrayList<>();
        mDailyData = new ArrayList<>();

        initSys();

        List<Password> data = mDao.queryBuilder().build().list();
        for (Password password : data) {
            mData.add(new PasswordModel(password));
        }

        List<DailySelf> dailyData = mDailySelfDao.queryBuilder().list();
        for (DailySelf dailySelf : dailyData) {
            MainDailySelfModel model = new MainDailySelfModel(dailySelf);
            mDailyData.add(model);
            mData.add(model);
        }

        mView.showData(mData);
    }

    private void initSys() {
        MainTopModel mainTopModel = new MainTopModel();
        mainTopModel.mTime = new Date();
        mainTopModel.mType = 1;
        mData.add(mainTopModel);
        mSysEmptyData.add(mainTopModel);

        MainSearchModel mainSearchModel = new MainSearchModel();
        mainSearchModel.mType = 1;
        mData.add(mainSearchModel);
        mSysEmptyData.add(mainSearchModel);

        mMainGroupModel = new MainGroupModel();
        mMainGroupModel.mType = 1;
        initGroup();

        BaseMainModel optionModel = new BaseMainModel();
        optionModel.mType = 0;
        mData.add(optionModel);
        mSysEmptyData.add(optionModel);

        MainAdapter.mPasswordPosition = mData.size();
    }

    private void initGroup() {
        MainGroupRlvModel mainGroupRlvModel = new MainGroupRlvModel(DEFAULT_CHECK_GROUP_TITLE, "#ffffff", new Date().getTime());
        mGroupData.add(mainGroupRlvModel);
        MainGroupRlvModel mainGroupRlvModel2 = new MainGroupRlvModel(DEFAULT_DAILY_SELF_TV_GROUP, "#ffffff", new Date().getTime());
        mGroupData.add(mainGroupRlvModel2);

        List<GroupInfo> data = mGroupInfoDao.queryBuilder().list();
        if (data != null && data.size() >= 0) {
            for (GroupInfo groupInfo : data) {
                mGroupData.add(new MainGroupRlvModel(groupInfo));
            }
        }

        mMainGroupModel.mData = mGroupData;
        mData.add(mMainGroupModel);
        mSysEmptyData.add(mMainGroupModel);
    }

    @Override
    public void addPassword(Password password) {
        long id = mDao.insert(password);
        if (id != -1) {
            PasswordModel model = new PasswordModel(password);
            mData.add(model);

            mView.notifyPasswordData(mData.size());
            mView.addPasswordSuc(mView.getContext().getString(R.string.string_add_password_suc));
        }
    }

    @Override
    public void insertDailySelf(String content) {
        DailySelf dailySelf = new DailySelf();
        dailySelf.mContent = content;
        dailySelf.mTimeStamp = new Date().getTime();
        dailySelf.mTvGroup = DEFAULT_DAILY_SELF_TV_GROUP;
        dailySelf.mIsFavorite = 0;
        mDailySelfDao.insert(dailySelf);
        MainDailySelfModel model = new MainDailySelfModel(dailySelf);
        mData.add(model);
        mDailyData.add(model);

        mView.notifyDailySelfData(mData.size());
    }

    @Override
    public void favoriteDailySelf(ItemMainDailySelfVM vm) {
        if (vm.mDailySelf.mIsFavorite == 0) {
            vm.mDailySelf.mIsFavorite = 1;
            mDailySelfDao.update(vm.mDailySelf);

            MainDailySelfModel model = (MainDailySelfModel) mData.get(vm.mItemPosition);
            model.mIsFavorite = true;
            mView.notifyDailySelfData(vm.mItemPosition);
        } else {
            vm.mDailySelf.mIsFavorite = 0;
            mDailySelfDao.update(vm.mDailySelf);

            MainDailySelfModel model = (MainDailySelfModel) mData.get(vm.mItemPosition);
            model.mIsFavorite = false;
            mView.notifyDailySelfData(vm.mItemPosition);
        }
    }

    @Override
    public void addGroup(String tvGroup) {
        GroupInfo info = new GroupInfo();
        info.mTvGroup = tvGroup;
        info.mBgColor = "#ffffff";
        info.mTimeStamp = new Date().getTime();
        long id = mGroupInfoDao.insert(info);

        MainGroupRlvModel model = new MainGroupRlvModel(info);
        mGroupData.add(model);

        if (id != -1) {
            mView.addGroupSuc();
        }
    }

    @Override
    public void onClickBtnPassword() {
        AddPasswordActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnLock() {
        mView.onClickBtnLock();
    }

    @Override
    public void onClickItemPassword(ItemMainPasswordVM model) {
        EditPasswordActivity.start(mView.getContext(), model, model.mItemPosition);
    }

    @Override
    public void onClickBtnDeletePassword() {
        DeleteListPasswordActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnDeleteDailySelf() {
        DeleteListDailySelfActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnAddGroup() {
        new AddGroupDialog(mView.getContext()).show();
    }

    @Override
    public void onClickBtnSetting() {
        GroupSettingActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnSearch() {
        mView.onClickBtnSearch();
    }

    @Override
    public void querySearch(String queryKey) {
        List<Password> list = mDao.queryBuilder().whereOr(PasswordDao.Properties.MTitle.like("%" + queryKey + "%"),
                PasswordDao.Properties.MUserName.like("%" + queryKey + "%"),
                PasswordDao.Properties.MPassword.like("%" + queryKey + "%")).list();
        if (list.size() > 0) {
            mView.searchReturn(list, null);
            return;
        }
        mView.searchReturn(null, "未找到和此关键字匹配的账号信息");
    }

    @Override
    public void deletePassword(int position, Password password) {
        DeletedPassword deletedPassword = DeletedPasswordHelper.getDeletedPassword(password);
        mDeleteDao.insert(deletedPassword);
        mDao.delete(password);

        mView.deletePassword(position);
    }

    @Override
    public void deleteDailySelf(ItemMainDailySelfVM vm) {
        DeletedDailySelf deletedDailySelf = DeleteDailySelfHelper.getDeletedDailySelf(vm.mDailySelf);
        Long id = mDeleteDailyDao.insert(deletedDailySelf);
        mDailySelfDao.delete(vm.mDailySelf);

        mView.deleteDailySelf(vm.mItemPosition);
    }

    @Override
    public void deletePasswordReturn(DeletePasswordReturnEvent event) {
        DeletedPassword deletedPassword = event.mDeletedPassword;
        Password password = DeletedPasswordHelper.getPassword(deletedPassword);
        mDao.insert(password);
        PasswordModel model = new PasswordModel(password);
        mData.add(model);

        mView.notifyPasswordData(mData.size());
    }

    @Override
    public void deleteDailySelfReturn(DeleteDailySelfReturnEvent event) {
        DeletedDailySelf deletedDailySelf = event.mDeletedDailySelf;
        DailySelf dailySelf = DeleteDailySelfHelper.getDailySelf(deletedDailySelf);
        mDailySelfDao.insert(dailySelf);

        MainDailySelfModel model = new MainDailySelfModel(dailySelf);
        mData.add(model);
        mDailyData.add(model);

        mView.notifyDailySelfData(mData.size());
    }

    @Override
    public void checkUpdate(String version) {
        ApiServerImpl.checkUpdate(version).subscribe(new Subscriber<RealUpdate>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(RealUpdate realUpdate) {
                switch (realUpdate.getCheck()) {
                    case "true": {
                        if (UpdateStatusManager.isShowUpdateDialog()) {
                            showUpgradeDialog(realUpdate);
                        }
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void checkOrderPassword(CheckOrderEvent event) {
        if (event.mGroupInfo == null) {
            if (TextUtils.equals(event.mDefaultTvGroup, DEFAULT_DAILY_SELF_TV_GROUP)) {
                if (!event.mIsCheck) {
                    mView.showData(mSysEmptyData);
                    return;
                }
                mView.checkOrderSuc(mDailyData);
                return;
            } else if (TextUtils.equals(event.mDefaultTvGroup, DEFAULT_CHECK_GROUP_TITLE)) {
                if (!event.mIsCheck) {
                    mView.showData(mSysEmptyData);
                    return;
                }
                mView.showData(mData);
                return;
            }
        }
        setCheckedData(event);
    }

    @Override
    public void copyDailySelf(ItemMainDailySelfVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mContent.get());
    }

    @Override
    public void copyPasswordInPassword(ItemMainPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mPassword.get());
    }

    @Override
    public void copyPasswordInUserName(ItemMainPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mUserName.get());
    }

    private void showUpgradeDialog(final RealUpdate result) {
        new UpdateDialog(mView.getContext(), result.getSrc()).show();
    }

    @Override
    public void alterPassword(AlterPasswordModel model, int itemPosition) {
        mDao.update(model.mOldPassword);
        mDao.insert(model.mNewPassword);
        mData.add(new PasswordModel(model.mNewPassword));

        //直接更换旧model的isNew数据（引用指向的内存不变）
        PasswordModel oldModel = (PasswordModel) mData.get(itemPosition);
        oldModel.mIsNew = false;

        mView.alterPasswordSuc(itemPosition, mData.size());
    }

    private void setCheckedData(CheckOrderEvent event) {
//        if (event.mIsCheck) {
//            mCheckedList.add(event.mGroupInfo);
//        } else {
//            if (mCheckedList.contains(event.mGroupInfo)) mCheckedList.remove(event.mGroupInfo);
//        }
        if (!event.mIsCheck) {
            mView.showData(mSysEmptyData);
            return;
        }
        List<BaseMainModel> checkData = new ArrayList<>();
        List<Password> data = mDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(event.mGroupInfo.getMTvGroup())).build().list();
        List<PasswordModel> passwordData = new ArrayList<>();
        for (Password password : data) {
            passwordData.add(new PasswordModel(password));
        }
        checkData.addAll(passwordData);
//        for (GroupInfo info : mCheckedList) {
//            List<Password> data = mDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(info.getMTvGroup())).build().list();
//            List<PasswordModel> passwordData = new ArrayList<>();
//            for (Password password : data) {
//                passwordData.add(new PasswordModel(password));
//            }
//            checkData.addAll(passwordData);
//        }
        mView.checkOrderSuc(checkData);
    }
}
