package com.mdove.passwordguard.main.newmain.dailytask.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.model.CalendarEvent;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.DailyTaskContract;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class DailyTaskPresenter implements DailyTaskContract.Presenter {
    private DailyTaskContract.MvpView mView;
    private List<SelfTaskModel> mData;
    private SelfTaskDao mSelfTaskDao;

    @Override
    public void subscribe(DailyTaskContract.MvpView view) {
        mView = view;

        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        List<SelfTask> data = mSelfTaskDao.queryBuilder().build().list();
        Comparator<SelfTask> comparator = new Comparator<SelfTask>() {
            @Override
            public int compare(SelfTask o1, SelfTask o2) {
                return o1.compareTo(o2);
            }
        };
        Collections.sort(data, comparator);

        for (SelfTask selfTask : data) {
            mData.add(new SelfTaskModel(selfTask));
        }

        mView.initData(mData);
    }

    @Override
    public void insertSelfTask(String content) {

    }
}
