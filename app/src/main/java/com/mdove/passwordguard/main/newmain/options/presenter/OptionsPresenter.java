package com.mdove.passwordguard.main.newmain.options.presenter;

import android.support.annotation.IntDef;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddDailySelfActivity;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.alldata.AllDailySelfActivity;
import com.mdove.passwordguard.alldata.AllPasswordActivity;
import com.mdove.passwordguard.backup.BackUpActivity;
import com.mdove.passwordguard.collect.CollectActivity;
import com.mdove.passwordguard.deletelist.DeleteListDailySelfActivity;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionsTopModel;
import com.mdove.passwordguard.main.newmain.options.model.vm.BaseMainOptionsTopVM;
import com.mdove.passwordguard.main.newmain.options.model.vm.MainOptionsTopOneVM;
import com.mdove.passwordguard.main.newmain.options.model.vm.MainOptionsTopThreeVM;
import com.mdove.passwordguard.main.newmain.options.model.vm.MainOptionsTopTwoVM;
import com.mdove.passwordguard.main.newmain.options.presenter.contract.OptionsContract;
import com.mdove.passwordguard.setting.SettingActivity;
import com.mdove.passwordguard.task.NewSelfTaskActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/5/18.
 */

public class OptionsPresenter implements OptionsContract.Presenter {
    private OptionsContract.MvpView mView;

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

    //标记快捷操作的type
    @IntDef(value = {MAIN_OPEN_INFO_TYPE_SETTING, MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF, MAIN_OPEN_INFO_TYPE_ALL_PASSWORD,
            MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION, MAIN_OPEN_INFO_TYPE_COLLECT,
            MAIN_OPEN_INFO_TYPE_SELF_TASK, MAIN_OPEN_INFO_TYPE_ACCOUNT, MAIN_OPEN_INFO_TYPE_GUIDE,
            MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, MAIN_OPEN_INFO_TYPE_LOCK,
            MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, MAIN_OPEN_INFO_TYPE_BACKUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MainOptionsInfoType {
    }

    @Override
    public void subscribe(OptionsContract.MvpView view) {
        mView = view;

    }

    @Override
    public void unSubscribe() {
    }


    @Override
    public void initData() {
        initOptionsTop();
        initOthersOptionsData();
    }

    @Override
    public void onClickBtnTop(BaseMainOptionsTopVM observable) {
        switch (observable.mType) {
            case MAIN_OPEN_INFO_TYPE_ACCOUNT: {
                AddPasswordActivity.start(mView.getContext());
                break;
            }
            case MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF: {
                AddDailySelfActivity.start(mView.getContext());
                break;
            }
            case MAIN_OPEN_INFO_TYPE_SELF_TASK: {
                NewSelfTaskActivity.start(mView.getContext());
                break;
            }
        }
    }

    private void initOptionsTop() {
        MainOptionNewInfo account = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionNewInfo dailySelf = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionNewInfo selfTask = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);

        MainOptionsTopModel topModel = new MainOptionsTopModel();

        MainOptionsTopOneVM oneVM = new MainOptionsTopOneVM(account);
        MainOptionsTopTwoVM twoVM = new MainOptionsTopTwoVM(dailySelf);
        MainOptionsTopThreeVM threeVM = new MainOptionsTopThreeVM(selfTask);

        topModel.mOneVM = oneVM;
        topModel.mTwoVM = twoVM;
        topModel.mThreeVM = threeVM;

        mView.showTopOptions(topModel);
    }

    public void initOthersOptionsData() {
        List<MainOptionNewInfo> data = new ArrayList<>();

        MainOptionNewInfo deleteAccount = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        MainOptionNewInfo deleteDailySelf = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "随手记", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
        MainOptionNewInfo collect = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_COLLECT, "我的收藏", "所有收藏内容", R.drawable.bg_main_option_btn_8, R.mipmap.ic_btn_collect);
        MainOptionNewInfo mainOption = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION, "更多操作", "显示隐藏操作", R.drawable.bg_main_option_btn_9, R.mipmap.ic_btn_more_option);
        MainOptionNewInfo lock = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_LOCK, "手势锁", "保护信息安全", R.drawable.bg_main_option_btn_2, R.mipmap.ic_btn_lock);
        MainOptionNewInfo account = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_ALL_PASSWORD, "所有账号", "查看所有的账号密码记录", R.drawable.bg_main_option_btn_5, R.mipmap.ic_btn_password);
        MainOptionNewInfo dailySelf = new MainOptionNewInfo(MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF, "所有随手记", "查看所有的随手记", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);

        data.add(account);
        data.add(dailySelf);
        data.add(deleteAccount);
        data.add(deleteDailySelf);
        data.add(collect);
        data.add(mainOption);

        mView.showOtherOptions(data);
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
        NewSelfTaskActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnCollect() {
        CollectActivity.start(mView.getContext());
    }

    @Override
    public void onClickBtnBackUp() {
        BackUpActivity.start(mView.getContext());
    }
}
