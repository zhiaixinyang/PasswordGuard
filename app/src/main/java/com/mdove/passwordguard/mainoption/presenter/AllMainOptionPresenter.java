package com.mdove.passwordguard.mainoption.presenter;

import android.Manifest;
import android.app.Activity;
import android.util.SparseArray;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddDailySelfActivity;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.alldata.AllDailySelfActivity;
import com.mdove.passwordguard.alldata.AllPasswordActivity;
import com.mdove.passwordguard.backup.BackUpActivity;
import com.mdove.passwordguard.backup.BackUpService;
import com.mdove.passwordguard.collect.CollectActivity;
import com.mdove.passwordguard.deletelist.DeleteListDailySelfActivity;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.mainoption.presenter.contract.AllMainOptionContract;
import com.mdove.passwordguard.setting.SettingActivity;
import com.mdove.passwordguard.task.NewSelfTaskActivity;
import com.mdove.passwordguard.utils.permission.PermissionGrantCallback;
import com.mdove.passwordguard.utils.permission.PermissionManager;
import com.mdove.passwordguard.utils.permission.PermissionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ALL_PASSWORD;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_COLLECT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_LOCK;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_SELF_TASK;

/**
 * Created by MDove on 2018/4/2.
 */

public class AllMainOptionPresenter implements AllMainOptionContract.Presenter {
    private AllMainOptionContract.MvpView mView;
    private List<MainOptionInfo> mData;

    @Override
    public void subscribe(AllMainOptionContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        mData = getInitOptionData();
        mView.showData(mData);
    }

    @Override
    public void onClickBtnPassword() {
        AddPasswordActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnLock() {
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
    public void onClickBtnSetting() {
        SettingActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnAllPassword() {
        AllPasswordActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnAllDailySelf() {
        AllDailySelfActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnActivityDailySelf() {
        AddDailySelfActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnSelfTask() {
//        SelfTaskActivity.start(mView.getContext());
        NewSelfTaskActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnCollect() {
        CollectActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnBackUp() {
        BackUpActivity.start(mView.getContext());
//        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (!PermissionUtils.hasPermissions(mView.getContext(), permissions)) {
//            PermissionManager.askForPermission((Activity) mView.getContext(), 0, permissions, new PermissionGrantCallback() {
//                @Override
//                public void permissionGranted(int requestCode) {
//                    BackUpService.start(mView.getContext());
//                }
//
//                @Override
//                public void permissionRefused(int requestCode) {
//
//                }
//            });
//        } else {
//            BackUpService.start(mView.getContext());
//        }
    }

    public List<MainOptionInfo> getInitOptionData() {
        List<MainOptionInfo> data = new ArrayList<>();
        MainOptionInfo account = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录自己想要保存账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionInfo dailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录生活中有趣的瞬间", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionInfo selfTask = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的随手计划", "记录自己突然的计划", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        MainOptionInfo lock = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_LOCK, "手势锁", "保护自己的信息安全", R.drawable.bg_main_option_btn_2, R.mipmap.ic_btn_lock);
        MainOptionInfo deleteAccount = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "所有的被删除的账号信息归档于此", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        MainOptionInfo deleteDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "所有被删除的随手记归档于此", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
//        MainOptionInfo guide = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_GUIDE, "引导", "了解一下", R.drawable.bg_main_option_btn_5, R.mipmap.ic_btn_guide);
        MainOptionInfo collect = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_COLLECT, "我的收藏", "所有收藏内容归档于此", R.drawable.bg_main_option_btn_8, R.mipmap.ic_btn_collect);
        MainOptionInfo allPassword = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ALL_PASSWORD, "所有账号", "所有账号记录（包括隐藏显示）", R.drawable.bg_main_option_btn_10, R.mipmap.ic_btn_password);
        MainOptionInfo allDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF, "所有随手记", "所有随手记（包括隐藏显示）", R.drawable.bg_main_option_btn_11, R.mipmap.ic_btn_password);
        MainOptionInfo setting = new MainOptionInfo(MainPresenter.MAIN_OPEN_INFO_TYPE_SETTING, "设置", "系统设置（恢复隐藏按钮）", R.drawable.bg_main_option_btn_12, R.mipmap.ic_settings);
        MainOptionInfo backup = new MainOptionInfo(MainPresenter.MAIN_OPEN_INFO_TYPE_BACKUP, "备份", "备份数据至文件", R.drawable.bg_main_option_btn_13, R.mipmap.ic_settings);
//        data.add(guide);
        data.add(account);
        data.add(dailySelf);
        data.add(selfTask);
        data.add(lock);
        data.add(deleteAccount);
        data.add(deleteDailySelf);
        data.add(allPassword);
        data.add(allDailySelf);
        data.add(collect);
        data.add(setting);
        data.add(backup);
        return data;
    }
}
