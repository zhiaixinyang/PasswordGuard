package com.mdove.passwordguard.mainoption.presenter;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddDailySelfActivity;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.addoralter.EditPasswordActivity;
import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.collect.CollectActivity;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.deletelist.DeleteListDailySelfActivity;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.deletelist.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.deletelist.utils.DeletedPasswordHelper;
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
import com.mdove.passwordguard.group.GroupSettingActivity;
import com.mdove.passwordguard.main.AddGroupDialog;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainPasswordModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainSelfTaskModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.impl.IHideVm;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.mainoption.AllMainOptionActivity;
import com.mdove.passwordguard.mainoption.presenter.contract.AllMainOptionContract;
import com.mdove.passwordguard.manager.UpdateStatusManager;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.setting.SettingActivity;
import com.mdove.passwordguard.task.NewSelfTaskActivity;
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

import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_ALL_PASSWORD;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_COLLECT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_GUIDE;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_LOCK;
import static com.mdove.passwordguard.main.presenter.MainPresenter.MAIN_OPEN_INFO_TYPE_MAIN_ALL_OPTION;
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

    }

    @Override
    public void onClickBtnAllDailySelf() {

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

    public List<MainOptionInfo> getInitOptionData() {
        List<MainOptionInfo> data = new ArrayList<>();
        MainOptionInfo account = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ACCOUNT, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        MainOptionInfo dailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ADD_DAILY_SELF, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        MainOptionInfo selfTask = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_SELF_TASK, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        MainOptionInfo lock = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_LOCK, "手势锁", "保护信息安全", R.drawable.bg_main_option_btn_2, R.mipmap.ic_btn_lock);
        MainOptionInfo deleteAccount = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_ACCOUNT, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        MainOptionInfo deleteDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_DELETE_DAILY_SELF, "删除记录", "随手记", R.drawable.bg_main_option_btn_7, R.mipmap.ic_btn_delete);
        MainOptionInfo guide = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_GUIDE, "引导", "了解一下", R.drawable.bg_main_option_btn_5, R.mipmap.ic_btn_guide);
        MainOptionInfo collect = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_COLLECT, "我的收藏", "所有收藏内容", R.drawable.bg_main_option_btn_8, R.mipmap.ic_btn_collect);
        MainOptionInfo allPassword = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ALL_PASSWORD, "所有账号", "所有账号记录", R.drawable.bg_main_option_btn_10, R.mipmap.ic_btn_password);
        MainOptionInfo allDailySelf = new MainOptionInfo(MAIN_OPEN_INFO_TYPE_ALL_DAILY_SELF, "所有随手记", "所有随手记", R.drawable.bg_main_option_btn_11, R.mipmap.ic_btn_password);
        MainOptionInfo setting = new MainOptionInfo(MainPresenter.MAIN_OPEN_INFO_TYPE_SETTING, "设置", "系统设置", R.drawable.bg_main_option_btn_12, R.mipmap.ic_settings);
        data.add(guide);
        data.add(account);
        data.add(dailySelf);
        data.add(selfTask);
        data.add(lock);
        data.add(deleteAccount);
        data.add(deleteDailySelf);
        data.add(allPassword);
        data.add(collect);
        data.add(allDailySelf);
        data.add(setting);
        return data;
    }
}
