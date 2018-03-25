package com.mdove.passwordguard.main.presenter;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddDailySelfActivity;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.addoralter.EditPasswordActivity;
import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
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
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.greendao.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.group.GroupSettingActivity;
import com.mdove.passwordguard.main.AddGroupDialog;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.adapter.MainSelfTaskAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainSelfTaskModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.manager.UpdateStatusManager;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.task.SelfTaskActivity;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.update.UpdateDialog;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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
    private PasswordDao mPasswordDao;
    private DeletedPasswordDao mDeleteDao;
    private GroupInfoDao mGroupInfoDao;
    private DeletedDailySelfDao mDeleteDailyDao;
    private DailySelfDao mDailySelfDao;
    private SelfTaskDao mSelfTaskDao;
    private List<MainGroupRlvModel> mGroupData;
    private List<BaseMainModel> mDailyData;
    private MainGroupModel mMainGroupModel;
    private List<GroupInfo> mCheckedList;
    private static final String DEFAULT_DAILY_SELF_TV_GROUP = AppConstant.DEFAULT_DAILY_SELF_TV_GROUP;
    private static final String DEFAULT_CHECK_GROUP_TITLE = AppConstant.DEFAULT_CHECK_GROUP_TITLE;

    public static final int MAIN_OPEN_INFO_TYPE_ACCOUNT = 1;
    public static final int MAIN_OPEN_INFO_TYPE_LOCK = 2;
    public static final int MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT = 3;
    public static final int MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF = 4;
    public static final int MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF = 5;
    public static final int MAIN_OPEN_INFO_TYPE_GUIDE = 6;
    public static final int MAIN_OPEN_INFO_TYPE_SELF_TASK = 7;

    private String mCurGroup = DEFAULT_CHECK_GROUP_TITLE;
    private List<BaseMainModel> mCheckData;

    @IntDef(value = {MAIN_OPEN_INFO_TYPE_SELF_TASK, MAIN_OPEN_INFO_TYPE_ACCOUNT, MAIN_OPEN_INFO_TYPE_GUIDE, MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, MAIN_OPEN_INFO_TYPE_LOCK, MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MainOpenInfoType {
    }

    @Override
    public void subscribe(MainContract.MvpView view) {
        mView = view;

        mCheckedList = new ArrayList<>();

        mPasswordDao = App.getDaoSession().getPasswordDao();
        mDeleteDao = App.getDaoSession().getDeletedPasswordDao();
        mGroupInfoDao = App.getDaoSession().getGroupInfoDao();
        mDailySelfDao = App.getDaoSession().getDailySelfDao();
        mDeleteDailyDao = App.getDaoSession().getDeletedDailySelfDao();
        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
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

        List<SelfTaskModel> selfTaskModels = new ArrayList<>();
        List<SelfTask> selfTasks = mSelfTaskDao.queryBuilder().list();
        for (SelfTask selfTask : selfTasks) {
            if (selfTask.mIsSee == 1) {
                selfTaskModels.add(new SelfTaskModel(selfTask));
            }
        }
        MainSelfTaskModel mainSearchModel = new MainSelfTaskModel(selfTaskModels);
        mData.add(mainSearchModel);

        List<Password> data = mPasswordDao.queryBuilder().build().list();
        for (Password password : data) {
            mData.add(new PasswordModel(password));
        }

        List<MainDailySelfModel> favoriteData = new ArrayList<>();
        List<MainDailySelfModel> noFavoriteData = new ArrayList<>();
        List<DailySelf> dailyData = mDailySelfDao.queryBuilder().list();
        for (DailySelf dailySelf : dailyData) {
            MainDailySelfModel favoriteModel = new MainDailySelfModel(dailySelf);
            if (dailySelf.mIsFavorite == 1) {
                favoriteData.add(favoriteModel);
            } else {
                noFavoriteData.add(favoriteModel);
            }
        }
        mDailyData.addAll(favoriteData);
        mDailyData.addAll(noFavoriteData);
        mData.addAll(favoriteData);
        mData.addAll(noFavoriteData);

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

        MainOptionModel optionModel = new MainOptionModel();
        optionModel.mType = 0;
        optionModel.mData = getInitOptionData();
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
        long id = mPasswordDao.insert(password);
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
    public void addDailySelf(DailySelf dailySelf) {
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

            MainDailySelfModel model = null;
            if (mCurGroup.equals(DEFAULT_CHECK_GROUP_TITLE)) {
                model = (MainDailySelfModel) mData.get(vm.mItemPosition);
            } else if (mCurGroup.equals(DEFAULT_DAILY_SELF_TV_GROUP)) {
                model = (MainDailySelfModel) mDailyData.get(vm.mItemPosition - mSysEmptyData.size());
            } else {
                model = (MainDailySelfModel) mCheckData.get(vm.mItemPosition - mSysEmptyData.size());
            }
            model.mIsFavorite = true;
            mView.notifyDailySelfData(vm.mItemPosition);
        } else {
            vm.mDailySelf.mIsFavorite = 0;
            mDailySelfDao.update(vm.mDailySelf);

            MainDailySelfModel model = null;
            if (mCurGroup.equals(DEFAULT_CHECK_GROUP_TITLE)) {
                model = (MainDailySelfModel) mData.get(vm.mItemPosition);
            } else if (mCurGroup.equals(DEFAULT_DAILY_SELF_TV_GROUP)) {
                model = (MainDailySelfModel) mDailyData.get(vm.mItemPosition - mSysEmptyData.size());
            } else {
                model = (MainDailySelfModel) mCheckData.get(vm.mItemPosition - mSysEmptyData.size());
            }
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
    public void onClickItemDailySelf(ItemMainDailySelfVM model) {
        AddDailySelfActivity.startEdit(mView.getContext(), model, model.mItemPosition);
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
    public void onClickBtnActivityDailySelf() {
        AddDailySelfActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnGuide() {
        mView.onShowGuide();
    }

    @Override
    public void onClickBtnSelfTask() {
        SelfTaskActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnSearch() {
        mView.onClickBtnSearch();
    }

    @Override
    public void onClickBtnAddDailySelf() {
        mView.onClickBtnAddDailySelf();
    }

    @Override
    public void querySearch(String queryKey) {
        List<Password> passwordList = mPasswordDao.queryBuilder().whereOr(PasswordDao.Properties.MTitle.like("%" + queryKey + "%"),
                PasswordDao.Properties.MUserName.like("%" + queryKey + "%"),
                PasswordDao.Properties.MPassword.like("%" + queryKey + "%")).list();
        List<DailySelf> dailySelfList = mDailySelfDao.queryBuilder().whereOr(DailySelfDao.Properties.MTvGroup.like("%" + queryKey + "%"),
                DailySelfDao.Properties.MContent.like("%" + queryKey + "%")).list();
        List<BaseMainModel> data = new ArrayList<>();
        for (Password password : passwordList) {
            data.add(new PasswordModel(password));
        }
        for (DailySelf dailySelf : dailySelfList) {
            data.add(new MainDailySelfModel(dailySelf));
        }
        if (data.size() > 0) {
            mView.searchReturn(data, "");
            return;
        }
        mView.searchReturn(null, "未找到和此关键字匹配的账号信息");
    }

    @Override
    public void deletePassword(int position, Password password) {
        DeletedPassword deletedPassword = DeletedPasswordHelper.getDeletedPassword(password);
        mDeleteDao.insert(deletedPassword);
        mPasswordDao.delete(password);

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
        mPasswordDao.insert(password);
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
                    mCurGroup = "";
                    return;
                }
                mCurGroup = DEFAULT_DAILY_SELF_TV_GROUP;
                mDailyData = getDefaultDailySelf();
                mView.checkOrderSuc(mDailyData);
                return;
            } else if (TextUtils.equals(event.mDefaultTvGroup, DEFAULT_CHECK_GROUP_TITLE)) {
                if (!event.mIsCheck) {
                    mView.showData(mSysEmptyData);
                    mCurGroup = "";
                    return;
                }
                mCurGroup = DEFAULT_CHECK_GROUP_TITLE;
                mData = getDefaultGroup();
                mView.showData(mData);
                return;
            }
        }
        setCheckedData(event);
    }

    public List<BaseMainModel> getDefaultDailySelf() {
        List<DailySelf> dailySelfList = mDailySelfDao.queryBuilder().where(DailySelfDao.Properties.MTvGroup.eq(DEFAULT_DAILY_SELF_TV_GROUP)).build().list();
        List<BaseMainModel> dailySelfData = new ArrayList<>();
        for (DailySelf dailySelf : dailySelfList) {
            dailySelfData.add(new MainDailySelfModel(dailySelf));
        }
        return dailySelfData;
    }

    public List<BaseMainModel> getDefaultGroup() {
        List<BaseMainModel> passwordData = new ArrayList<>();
        passwordData.addAll(mSysEmptyData);

        List<Password> data = mPasswordDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(DEFAULT_CHECK_GROUP_TITLE)).build().list();
        for (Password password : data) {
            passwordData.add(new PasswordModel(password));
        }
        List<DailySelf> dailySelfList = mDailySelfDao.queryBuilder().build().list();
        for (DailySelf dailySelf : dailySelfList) {
            passwordData.add(new MainDailySelfModel(dailySelf));
        }
        return passwordData;
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

    @Override
    public void onClickTaskSuc(SelfTaskModelVM vm, MainSelfTaskAdapter adapter) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        if (vm.mSelfTaskModel.mIsSuc) {
            selfTask.mIsSuc = 0;
            vm.mSelfTaskModel.mIsSuc = false;
            mSelfTaskDao.update(selfTask);
        } else {
            selfTask.mIsSuc = 1;
            vm.mSelfTaskModel.mIsSuc = true;
            mSelfTaskDao.update(selfTask);
        }
        adapter.onClickTaskSuc(vm.mPosition);
    }

    private void showUpgradeDialog(final RealUpdate result) {
        new UpdateDialog(mView.getContext(), result.getSrc()).show();
    }

    @Override
    public void alterPassword(AlterPasswordModel model, int itemPosition) {
        mPasswordDao.update(model.mNeedEditPassword);
        mDeleteDao.insert(DeletedPasswordHelper.getDeletedPassword(model.mTempPassword));

        PasswordModel oldModel = (PasswordModel) mData.get(itemPosition);
        oldModel.setPassword(model.mNeedEditPassword);

        mView.alterPasswordSuc(itemPosition, mData.size());
    }

    @Override
    public void alterDailySelf(AlterDailySelfModel model, int itemPosition) {
        mDailySelfDao.update(model.mOldDailySelf);
//        mPasswordDao.insert(model.mNewPassword);
//        mData.add(new PasswordModel(model.mNewPassword));

        //直接更换旧model的数据（引用指向的内存不变）
        MainDailySelfModel oldModel = (MainDailySelfModel) mData.get(itemPosition);
        oldModel.setDailySelf(model.mOldDailySelf);

        mView.alterPasswordSuc(itemPosition, mData.size());
    }

    private void setCheckedData(CheckOrderEvent event) {
        if (!event.mIsCheck) {
            mView.showData(mSysEmptyData);
            mCurGroup = "";
            return;
        }
        mCurGroup = event.mGroupInfo.mTvGroup;
        mCheckData = new ArrayList<>();
        List<Password> data = mPasswordDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(event.mGroupInfo.getMTvGroup())).build().list();
        List<PasswordModel> passwordData = new ArrayList<>();
        for (Password password : data) {
            passwordData.add(new PasswordModel(password));
        }
        List<DailySelf> dailySelfList = mDailySelfDao.queryBuilder().where(DailySelfDao.Properties.MTvGroup.eq(event.mGroupInfo.getMTvGroup())).build().list();
        List<MainDailySelfModel> dailySelfData = new ArrayList<>();
        for (DailySelf dailySelf : dailySelfList) {
            dailySelfData.add(new MainDailySelfModel(dailySelf));
        }

        mCheckData.addAll(passwordData);
        mCheckData.addAll(dailySelfData);
//        for (GroupInfo info : mCheckedList) {
//            List<Password> data = mPasswordDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(info.getMTvGroup())).build().list();
//            List<PasswordModel> passwordData = new ArrayList<>();
//            for (Password password : data) {
//                passwordData.add(new PasswordModel(password));
//            }
//            checkData.addAll(passwordData);
//        }
        mView.checkOrderSuc(mCheckData);
    }

    public List<MainOptionInfo> getInitOptionData() {
        List<MainOptionInfo> data = new ArrayList<>();
        MainOptionInfo account = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionInfo dailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionInfo selfTask = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        MainOptionInfo lock = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_LOCK, "手势锁", "保护信息安全", R.drawable.bg_main_option_btn_2, R.mipmap.ic_btn_lock);
        MainOptionInfo deleteAccount = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_btn_delete);
        MainOptionInfo deleteDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "随手记", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
        MainOptionInfo guide = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_GUIDE, "引导", "了解一下", R.drawable.bg_main_option_btn_5, R.mipmap.ic_btn_guide);
        data.add(guide);
        data.add(account);
        data.add(dailySelf);
        data.add(selfTask);
        data.add(lock);
        data.add(deleteAccount);
        data.add(deleteDailySelf);
        return data;
    }
}
