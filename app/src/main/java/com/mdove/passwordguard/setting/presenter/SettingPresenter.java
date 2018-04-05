package com.mdove.passwordguard.setting.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.main.config.MainConfig;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.event.HideItemMainEvent;
import com.mdove.passwordguard.setting.presenter.contract.SettingContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mdove.passwordguard.main.presenter.MainPresenter.*;

/**
 * Created by MDove on 2018/4/5.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private static final String DEFAULT_DAILY_SELF_TV_GROUP = AppConstant.DEFAULT_DAILY_SELF_TV_GROUP;
    private static final String DEFAULT_CHECK_GROUP_TITLE = AppConstant.DEFAULT_CHECK_GROUP_TITLE;

    private SettingContract.MvpView mView;
    private GroupInfoDao mGroupInfoDao;
    private MainGroupModel mMainGroupModel;

    private List<BaseMainModel> mData;
    private List<MainGroupRlvModel> mGroupData;

    public SettingPresenter() {
        mGroupInfoDao = App.getDaoSession().getGroupInfoDao();

        mData = new ArrayList<>();
        mGroupData = new ArrayList<>();
    }

    @Override
    public void subscribe(SettingContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initHideSysItem() {
        if (MainConfig.isHideSysItemTimeTop()) {
            MainTopModel mainTopModel = new MainTopModel();
            mainTopModel.mTime = new Date();
            mainTopModel.mType = 1;
            mainTopModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_TOP_TIME;
            mData.add(mainTopModel);
        }

        if (MainConfig.isHideSysItemSearch()) {
            MainSearchModel mainSearchModel = new MainSearchModel();
            mainSearchModel.mType = 1;
            mainSearchModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_SEARCH;
            mData.add(mainSearchModel);
        }

        if (MainConfig.isHideSysItemGroup()) {
            mMainGroupModel = new MainGroupModel();
            mMainGroupModel.mType = 1;
            mMainGroupModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_GROUP;
            initGroup();
        }

        if (MainConfig.isHideSysItemOption()) {
            MainOptionModel optionModel = new MainOptionModel();
            optionModel.mType = 0;
            optionModel.mData = getInitOptionData();
            optionModel.mSysType = BaseMainModel.MAIN_ITEM_SYS_TYPE_OPTION;

            mData.add(optionModel);
        }

        mView.showShowHideSysItem(mData);
    }

    @Override
    public void onClickBtnHideGroup() {
        MainConfig.setHideSysItemGroup(false);
        int position = -1;

        for (BaseMainModel baseMainModel : mData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_GROUP) {
                position = mData.indexOf(baseMainModel);
            }
        }

        if (position != -1) {
            mView.onClickBtnHideGroup(position);
            RxBus.get().post(new HideItemMainEvent(HideItemMainEvent.TYPE_HIDE_ITEM_MAIN_GROUP));
        }
    }

    @Override
    public void onClickBtnHideSearch() {
        MainConfig.setHideSysItemSearch(false);
        int position = -1;

        for (BaseMainModel baseMainModel : mData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_SEARCH) {
                position = mData.indexOf(baseMainModel);
            }
        }
        if (position!=-1) {
            mView.onClickBtnHideSearch(position);
            RxBus.get().post(new HideItemMainEvent(HideItemMainEvent.TYPE_HIDE_ITEM_MAIN_SEARCH));
        }
    }

    @Override
    public void onClickBtnHideTimeTop() {
        MainConfig.setHideSysItemTimeTop(false);
        int position = -1;
        for (BaseMainModel baseMainModel : mData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_TOP_TIME) {
                position = mData.indexOf(baseMainModel);
            }
        }
        if (position != -1) {
            mView.onClickBtnHideTimeTop(position);
            RxBus.get().post(new HideItemMainEvent(HideItemMainEvent.TYPE_HIDE_ITEM_MAIN_TIME_TOP));
        }
    }

    @Override
    public void onClickBtnHideOption() {
        MainConfig.setHideSysItemOption(false);
        int position = -1;
        for (BaseMainModel baseMainModel : mData) {
            if (baseMainModel.mSysType == BaseMainModel.MAIN_ITEM_SYS_TYPE_OPTION) {
                position = mData.indexOf(baseMainModel);
            }
        }

        if (position!=-1) {
            mView.onClickBtnHideOption(position);
            RxBus.get().post(new HideItemMainEvent(HideItemMainEvent.TYPE_HIDE_ITEM_MAIN_OPTION));
        }
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
    }

    public List<MainOptionInfo> getInitOptionData() {
        List<MainOptionInfo> data = new ArrayList<>();
        MainOptionInfo account = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionInfo dailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionInfo selfTask = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        MainOptionInfo deleteAccount = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        MainOptionInfo deleteDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "随手记", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
        MainOptionInfo guide = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_GUIDE, "引导", "了解一下", R.drawable.bg_main_option_btn_5, R.mipmap.ic_btn_guide);
        MainOptionInfo collect = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_COLLECT, "我的收藏", "所有收藏内容", R.drawable.bg_main_option_btn_8, R.mipmap.ic_btn_collect);
        MainOptionInfo mainOption = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION, "更多操作", "显示隐藏操作", R.drawable.bg_main_option_btn_9, R.mipmap.ic_btn_more_option);

        data.add(guide);
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
