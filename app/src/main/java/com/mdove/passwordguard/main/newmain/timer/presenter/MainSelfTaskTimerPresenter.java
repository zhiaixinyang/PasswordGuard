package com.mdove.passwordguard.main.newmain.timer.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskTimerDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTaskTimer;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.main.newmain.timer.TimerActivity;
import com.mdove.passwordguard.main.newmain.timer.TimerConstant;
import com.mdove.passwordguard.main.newmain.timer.TimerReceiver;
import com.mdove.passwordguard.main.newmain.timer.presenter.contract.MainSelfTaskTimerContract;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickEditEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.utils.DeleteSelfTaskHelper;
import com.mdove.passwordguard.utils.ClipboardUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/6.
 */

public class MainSelfTaskTimerPresenter implements MainSelfTaskTimerContract.Presenter {
    private MainSelfTaskTimerContract.MvpView mView;
    private SelfTaskTimerDao mSelfTaskTimerDao;

    @Override
    public void subscribe(MainSelfTaskTimerContract.MvpView view) {
        mView = view;

        mSelfTaskTimerDao = App.getDaoSession().getSelfTaskTimerDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void insertSelfTaskTimer(String content) {
        SelfTaskTimer timer = new SelfTaskTimer();
        timer.mIsSuc = 0;
        timer.mTime = new Date().getTime();
        timer.mTask = content;
        mSelfTaskTimerDao.insert(timer);

        mView.insertSelfTaskTimer(content);
    }
}
