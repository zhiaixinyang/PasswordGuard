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
import com.mdove.passwordguard.base.IHideVM;
import com.mdove.passwordguard.collect.CollectActivity;
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
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.deletelist.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.deletelist.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.group.GroupSettingActivity;
import com.mdove.passwordguard.main.AddGroupDialog;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.config.MainConfig;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainDailyPlanModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainPasswordModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.SelfTaskListModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.mainoption.AllMainOptionActivity;
import com.mdove.passwordguard.manager.UpdateStatusManager;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.mystatistics.MyStatisticsActivity;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.task.NewSelfTaskActivity;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.utils.SucSelfTaskHelper;
import com.mdove.passwordguard.update.UpdateDialog;
import com.mdove.passwordguard.utils.ClipboardUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

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
    private SucSelfTaskDao mSucSelfTaskDao;
    private SelfTaskDao mSelfTaskDao;
    private List<MainGroupRlvModel> mGroupData;
    private List<BaseMainModel> mDailyData;
    private List<SelfTaskModel> mMainSelfTaskData;
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
    public static final int MAIN_OPEN_INFO_TYPE_COLLECT = 8;
    public static final int MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION = 9;
    public static final int MAIN_OPEN_INFO_TYPE_ALL_PASSWORD = 10;
    public static final int MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF = 11;
    public static final int MAIN_OPEN_INFO_TYPE_SETTING = 12;
    public static final int MAIN_OPEN_INFO_TYPE_BACKUP = 13;

    private String mCurGroup = DEFAULT_CHECK_GROUP_TITLE;
    private List<BaseMainModel> mCheckData;
    private SelfTaskListModel mSelfTaskModel;

    //标记快捷操作的type
    @IntDef(value = {MAIN_OPEN_INFO_TYPE_SETTING, MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF, MAIN_OPEN_INFO_TYPE_ALL_PASSWORD,
            MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION, MAIN_OPEN_INFO_TYPE_COLLECT,
            MAIN_OPEN_INFO_TYPE_SELF_TASK, MAIN_OPEN_INFO_TYPE_ACCOUNT, MAIN_OPEN_INFO_TYPE_GUIDE,
            MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, MAIN_OPEN_INFO_TYPE_LOCK,
            MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, MAIN_OPEN_INFO_TYPE_BACKUP})
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
        mSucSelfTaskDao = App.getDaoSession().getSucSelfTaskDao();
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
        mMainSelfTaskData = new ArrayList<>();
        List<SelfTask> selfTasks = mSelfTaskDao.queryBuilder().list();
        for (SelfTask selfTask : selfTasks) {
            if (selfTask.mIsSee == 1) {
                selfTaskModels.add(new SelfTaskModel(selfTask));
                mMainSelfTaskData.add(new SelfTaskModel(selfTask));
            }
        }
        mSelfTaskModel = new SelfTaskListModel(selfTaskModels);
        mData.add(mSelfTaskModel);

        List<Password> data = mPasswordDao.queryBuilder().build().list();
        for (Password password : data) {
            if (password.isHide == 0) {
                mData.add(new MainPasswordModel(password));
            }
        }

        List<MainDailySelfModel> favoriteData = new ArrayList<>();
        List<MainDailySelfModel> noFavoriteData = new ArrayList<>();
        List<DailySelf> dailyData = mDailySelfDao.queryBuilder().list();
        for (DailySelf dailySelf : dailyData) {
            if (dailySelf.mIsHide != null && dailySelf.mIsHide == 1) {
                continue;
            }
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
        if (!MainConfig.isHideSysItemTimeTop()) {
            MainTopModel mainTopModel = new MainTopModel();
            mainTopModel.mTime = new Date();
            mainTopModel.mType = 1;
            mainTopModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_TOP_TIME;
            mData.add(mainTopModel);
            mSysEmptyData.add(mainTopModel);
        }

        if (!MainConfig.isHideSysItemSearch()) {
            MainSearchModel mainSearchModel = new MainSearchModel();
            mainSearchModel.mType = 1;
            mainSearchModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_SEARCH;
            mData.add(mainSearchModel);
            mSysEmptyData.add(mainSearchModel);
        }

        if (!MainConfig.isHideSysItemGroup()) {
            mMainGroupModel = new MainGroupModel();
            mMainGroupModel.mType = 1;
            mMainGroupModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_GROUP;
            initGroup();
        }

        //更换隐藏策略
        MainOptionModel optionModel = new MainOptionModel();
        optionModel.isHide = MainConfig.isHideSysItemOption();
        optionModel.mType = 0;
        optionModel.mData = getInitOptionData();
        optionModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_OPTION;

        mData.add(optionModel);
        mSysEmptyData.add(optionModel);

        //每日计划（每日复盘）
        MainDailyPlanModel mainDailyPlanModel = new MainDailyPlanModel();
        mainDailyPlanModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_DAILY_PLAN;
        mData.add(mainDailyPlanModel);
        mSysEmptyData.add(mainDailyPlanModel);

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
            MainPasswordModel model = new MainPasswordModel(password);
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
        dailySelf.mIsHide = 0;
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
    public void btnHidePworDs(IHideVM vm) {
        if (vm instanceof ItemMainPasswordVM) {
            ItemMainPasswordVM passwordVM = (ItemMainPasswordVM) vm;
            Password password = passwordVM.mMainPasswordModel.password;
            password.isHide = 1;
            mPasswordDao.update(password);

            passwordVM.mMainPasswordModel.mHide = true;
            mView.notifyBtnHide(passwordVM.mItemPosition);
        } else if (vm instanceof ItemMainDailySelfVM) {
            ItemMainDailySelfVM dailySelfVM = (ItemMainDailySelfVM) vm;
            DailySelf dailySelf = dailySelfVM.mDailySelf;
            dailySelf.mIsHide = 1;
            mDailySelfDao.update(dailySelf);

            dailySelfVM.mMainDailySelfModel.mIsHide = true;
            mView.notifyBtnHide(dailySelfVM.mItemPosition);
        }
    }

    @Override
    public void postAllPasswordHide(long id, boolean isHide) {
        if (isHide) {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainPasswordModel) {
                    MainPasswordModel model = (MainPasswordModel) baseMainModel;
                    if (model.mPasswordId == id) {
                        position = mData.indexOf(model);
                    }
                }
            }
            if (position != -1) {
                mView.notifyBtnHide(position);
            }
        } else {
            List<Password> list = mPasswordDao.queryBuilder().where(PasswordDao.Properties.Id.eq(id)).build().list();
            if (list == null || list.size() <= 0) {
                return;
            }
            Password password = list.get(0);
            mData.add(new MainPasswordModel(password));
            mView.notifyBtnNoHide(mData.size());
        }
    }

    @Override
    public void postAllPasswordFavorite(long id, boolean isFavorite) {
        if (isFavorite) {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainPasswordModel) {
                    MainPasswordModel model = (MainPasswordModel) baseMainModel;
                    if (model.mPasswordId == id) {
                        model.mFavorite = true;
                        position = mData.indexOf(model);
                    }
                }
            }
            if (position != -1) {
                mView.notifyPasswordData(position);
            }
        } else {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainPasswordModel) {
                    MainPasswordModel model = (MainPasswordModel) baseMainModel;
                    if (model.mPasswordId == id) {
                        model.mFavorite = false;
                        position = mData.indexOf(model);
                    }
                }
            }
            if (position != -1) {
                mView.notifyPasswordData(position);
            }
        }
    }

    @Override
    public void postAllDailySelfHide(long id, boolean isHide) {
        if (isHide) {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainDailySelfModel) {
                    MainDailySelfModel model = (MainDailySelfModel) baseMainModel;
                    if (model.mId == id) {
                        position = mData.indexOf(model);
                    }
                }
            }
            //避免ConcurrentModificationException异常
            if (position != -1) {
                mView.notifyBtnHide(position);
            }
        } else {
            List<DailySelf> list = mDailySelfDao.queryBuilder().where(DailySelfDao.Properties.Id.eq(id)).build().list();
            if (list == null || list.size() <= 0) {
                return;
            }
            DailySelf dailySelf = list.get(0);
            mData.add(new MainDailySelfModel(dailySelf));
            mView.notifyBtnNoHide(mData.size());
        }
    }

    @Override
    public void postAllDailySelfFavorite(long id, boolean isFavorite) {
        if (isFavorite) {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainDailySelfModel) {
                    MainDailySelfModel model = (MainDailySelfModel) baseMainModel;
                    if (model.mId == id) {
                        model.mIsFavorite = true;
                        position = mData.indexOf(model);
                    }
                }
            }
            //避免ConcurrentModificationException异常
            if (position != -1) {
                mView.notifyDailySelfData(position);
            }
        } else {
            int position = -1;
            for (BaseMainModel baseMainModel : mData) {
                if (baseMainModel instanceof MainDailySelfModel) {
                    MainDailySelfModel model = (MainDailySelfModel) baseMainModel;
                    if (model.mId == id) {
                        model.mIsFavorite = false;
                        position = mData.indexOf(model);
                    }
                }
            }
            //避免ConcurrentModificationException异常
            if (position != -1) {
                mView.notifyDailySelfData(position);
            }
        }
    }

    @Override
    public void favoritePassword(ItemMainPasswordVM vm) {
        Password password = vm.mMainPasswordModel.password;
        if (password.isFavorite == 0) {
            password.isFavorite = 1;
            mPasswordDao.update(password);

            vm.mMainPasswordModel.mFavorite = true;
            mView.notifyPasswordData(vm.mItemPosition);
        } else {
            password.isFavorite = 0;
            mPasswordDao.update(password);

            vm.mMainPasswordModel.mFavorite = false;
            mView.notifyPasswordData(vm.mItemPosition);
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
    public void onClickBtnStatistics() {
        MyStatisticsActivity.start(mView.getContext());
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
    public void onClickBtnHideGroup() {
        MainConfig.setHideSysItemGroup(true);
        if (mSysEmptyData == null || mSysEmptyData.size() <= 0) {
            return;
        }
        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_GROUP) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideGroup(position);
            }
        }
        if (position != -1) {
            mSysEmptyData.remove(position);
        }
    }

    @Override
    public void onClickBtnHideSearch() {
        MainConfig.setHideSysItemSearch(true);
        if (mSysEmptyData == null || mSysEmptyData.size() <= 0) {
            return;
        }
        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_SEARCH) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideSearch(position);
            }
        }
        if (position != -1) {
            mSysEmptyData.remove(position);
        }
    }

    @Override
    public void onClickBtnHideOption() {
        MainConfig.setHideSysItemOption(!MainConfig.isHideSysItemOption());

        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_OPTION) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideOption(position);
            }
        }
//        if (position != -1) {
//            mSysEmptyData.remove(position);
//        }
    }

    @Override
    public void onClickBtnHideTimeTop() {
        MainConfig.setHideSysItemTimeTop(true);
        if (mSysEmptyData == null || mSysEmptyData.size() <= 0) {
            return;
        }
        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_TOP_TIME) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideTimeTop(position);
            }
        }
        if (position != -1) {
            mSysEmptyData.remove(position);
        }
    }

    @Override
    public void onClickBtnHideDailyPlan() {
        MainConfig.setHideSysItemDailyPlan(!MainConfig.isHideSysItemDailyPlan());

        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_DAILY_PLAN) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideDailyPlan(position);
            }
        }
    }

    @Override
    public void onClickBtnHideDailySelf() {
        MainConfig.setHideSysItemDailySelf(!MainConfig.isHideSysItemDailySelf());

        int position = -1;
        for (BaseMainModel baseMainModel : mSysEmptyData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_DAILY_SELF) {
                position = mSysEmptyData.indexOf(baseMainModel);
                mView.onClickBtnHideDailySelf(position);
            }
        }
    }

    @Override
    public void onClickBtnSetting() {
        GroupSettingActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnMainSelfTaskIn() {
        NewSelfTaskActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnMainSelfTaskSend(String content) {
        if (TextUtils.isEmpty(content)) {
            ToastHelper.shortToast("多少写一些计划...");
            return;
        }
        SelfTask selfTask = new SelfTask();
        selfTask.mTask = content;
        selfTask.mTime = new Date().getTime();
        selfTask.mIsSuc = 0;
        selfTask.mIsSee = 1;
        selfTask.mPriority = 0;
        mSelfTaskDao.insert(selfTask);
        mView.insertItemMainSelfTask(new com.mdove.passwordguard.task.model.SelfTaskModel(selfTask));
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
        NewSelfTaskActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnCollect() {
        CollectActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnAllMainOption() {
        AllMainOptionActivity.start(mView.getContext());
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
            data.add(new MainPasswordModel(password));
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
        MainPasswordModel model = new MainPasswordModel(password);
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
        //工作线Item
        if (mSelfTaskModel != null) {
            dailySelfData.add(mSelfTaskModel);
        }
        for (DailySelf dailySelf : dailySelfList) {
            dailySelfData.add(new MainDailySelfModel(dailySelf));
        }
        return dailySelfData;
    }

    public List<BaseMainModel> getDefaultGroup() {
        List<BaseMainModel> passwordData = new ArrayList<>();
        passwordData.addAll(mSysEmptyData);
        //工作线Item
        if (mSelfTaskModel != null) {
            passwordData.add(mSelfTaskModel);
        }
        List<Password> data = mPasswordDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(DEFAULT_CHECK_GROUP_TITLE)).build().list();
        for (Password password : data) {
            passwordData.add(new MainPasswordModel(password));
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
    public void onClickTaskSuc(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        List<SucSelfTask> sucSelfTasks = mSucSelfTaskDao.queryBuilder().where(SucSelfTaskDao.Properties.MBelongId.eq(selfTask.id)).list();
        SucSelfTask sucSelfTask = null;
        if (sucSelfTasks != null && sucSelfTasks.size() > 0) {
            sucSelfTask = sucSelfTasks.get(0);
        }

        if (vm.mSelfTaskModel.mIsSuc) {
            selfTask.mIsSuc = 0;
            vm.mSelfTaskModel.mIsSuc = false;
            mSelfTaskDao.update(selfTask);

            if (sucSelfTask != null) {
                mSucSelfTaskDao.delete(sucSelfTask);
            }
        } else {
            selfTask.mIsSuc = 1;
            vm.mSelfTaskModel.mIsSuc = true;
            mSelfTaskDao.update(selfTask);

            if (sucSelfTask == null) {
                sucSelfTask = SucSelfTaskHelper.getSucSelfTask(vm.mSelfTaskModel.mSelfTask);
                mSucSelfTaskDao.insert(sucSelfTask);
            }
        }
        mView.onClickTaskSuc(vm.mPosition);
    }

    @Override
    public void onClickSee(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        if (vm.mSelfTaskModel.mIsSee) {
            selfTask.mIsSee = 0;
            vm.mSelfTaskModel.mIsSee = false;
            mSelfTaskDao.update(selfTask);
        } else {
            selfTask.mIsSee = 1;
            vm.mSelfTaskModel.mIsSee = true;
            mSelfTaskDao.update(selfTask);
        }
        mView.notifyTaskSelfSee(vm.mPosition);
    }

    @Override
    public void onClickPriority(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        int curPriority = selfTask.mPriority;
        curPriority++;
        if (curPriority >= 3) {
            curPriority = 0;
        }
        selfTask.mPriority = curPriority;
        mSelfTaskDao.update(selfTask);

        vm.mSelfTaskModel.mPriority = curPriority;
        mView.notifyTaskSelf(vm.mPosition);
    }

    @Override
    public void onClickCopyTaskSelf(SelfTaskModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mTask.get());
    }

    private void showUpgradeDialog(final RealUpdate result) {
        new UpdateDialog(mView.getContext(), result.getSrc()).show();
    }

    @Override
    public void alterPassword(AlterPasswordModel model, int itemPosition) {
        mPasswordDao.update(model.mNeedEditPassword);
        mDeleteDao.insert(DeletedPasswordHelper.getDeletedPassword(model.mTempPassword));

        MainPasswordModel oldModel = (MainPasswordModel) mData.get(itemPosition);
        oldModel.setPassword(model.mNeedEditPassword);

        mView.alterPasswordSuc(itemPosition, mData.size());
    }

    @Override
    public void alterDailySelf(AlterDailySelfModel model, int itemPosition) {
        mDailySelfDao.update(model.mOldDailySelf);

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

        //工作线Item
        if (mSelfTaskModel != null) {
            mCheckData.add(mSelfTaskModel);
        }
        List<Password> data = mPasswordDao.queryBuilder().where(PasswordDao.Properties.MTvGroup.eq(event.mGroupInfo.getMTvGroup())).build().list();
        List<MainPasswordModel> passwordData = new ArrayList<>();
        for (Password password : data) {
            passwordData.add(new MainPasswordModel(password));
        }
        List<DailySelf> dailySelfList = mDailySelfDao.queryBuilder().where(DailySelfDao.Properties.MTvGroup.eq(event.mGroupInfo.getMTvGroup())).build().list();
        List<MainDailySelfModel> dailySelfData = new ArrayList<>();
        for (DailySelf dailySelf : dailySelfList) {
            dailySelfData.add(new MainDailySelfModel(dailySelf));
        }

        mCheckData.addAll(passwordData);
        mCheckData.addAll(dailySelfData);

        mView.checkOrderSuc(mCheckData);
    }

    public List<MainOptionInfo> getInitOptionData() {
        List<MainOptionInfo> data = new ArrayList<>();
        MainOptionInfo account = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionInfo dailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionInfo selfTask = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        MainOptionInfo deleteAccount = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        MainOptionInfo deleteDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "随手记", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
        MainOptionInfo collect = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_COLLECT, "我的收藏", "所有收藏内容", R.drawable.bg_main_option_btn_8, R.mipmap.ic_btn_collect);
        MainOptionInfo mainOption = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION, "更多操作", "显示隐藏操作", R.drawable.bg_main_option_btn_9, R.mipmap.ic_btn_more_option);
        MainOptionInfo lock = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_LOCK, "手势锁", "保护信息安全", R.drawable.bg_main_option_btn_2, R.mipmap.ic_btn_lock);

        data.add(lock);
        data.add(account);
        data.add(dailySelf);
        data.add(selfTask);
        data.add(deleteAccount);
        data.add(deleteDailySelf);
        data.add(collect);
        data.add(mainOption);
        return data;
    }
}
