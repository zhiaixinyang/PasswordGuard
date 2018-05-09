package com.mdove.passwordguard.main.newmain.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.model.CalendarEvent;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.newmain.model.EverydayReplayModel;
import com.mdove.passwordguard.main.newmain.model.EverydayReplayModelVM;
import com.mdove.passwordguard.main.newmain.presenter.contract.EverydayReplayContract;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class EverydayReplayPresenter implements EverydayReplayContract.Presenter {
    private EverydayReplayContract.MvpView mView;
    private List<BaseCalendarModel> mData;
    private DailyPlanDao mDailyPlanDao;

    @Override
    public void subscribe(EverydayReplayContract.MvpView view) {
        mView = view;

        mDailyPlanDao = App.getDaoSession().getDailyPlanDao();
    }

    @Override
    public void unSubscribe() {
    }


    @Override
    public void initData() {
        mData = new ArrayList<>();
        List<DailyPlan> data = mDailyPlanDao.queryBuilder().list();
        for (DailyPlan plan : data) {
            mData.add(new EverydayReplayModel(plan));
        }

        mView.showData(mData);
    }

    @Override
    public void onClickEt() {
        mView.onClickEt();
    }

    @Override
    public void sendEverydayReplayContent(String content) {
        DailyPlan plan = new DailyPlan();
        plan.mTimeStamp = new Date().getTime();
        plan.mContent = content;
        plan.mStatus = EverydayReplayModel.STATUS_EMPTY;
        mDailyPlanDao.insert(plan);
    }

    @Override
    public void updateLostOrGet(long id, int type) {
        DailyPlan curDailyPlan = null;
        DailyPlanModel curDailyPlanModel = null;
        int position = -1;
        for (BaseCalendarModel model : mData) {
            if (model instanceof DailyPlanModel) {
                DailyPlanModel dailyPlanModel = (DailyPlanModel) model;
                if (id == dailyPlanModel.mId) {
                    curDailyPlan = dailyPlanModel.mDailyPlan;
                    curDailyPlanModel = dailyPlanModel;
                    position = mData.indexOf(dailyPlanModel);
                }
            }
        }
        if (curDailyPlan == null || position == -1) {
            return;
        }
        CalendarEvent event;
        switch (type) {
            case DailyPlanModel.STATUS_LOST: {
                event = new CalendarEvent(CalendarEvent.UPDATE_TYPE_LOST, id);
                break;
            }
            case DailyPlanModel.STATUS_GET: {
                event = new CalendarEvent(CalendarEvent.UPDATE_TYPE_GET, id);
                break;
            }
            case DailyPlanModel.STATUS_NORMAL: {
                event = new CalendarEvent(CalendarEvent.UPDATE_TYPE_NORMAL, id);
                break;
            }
            default:
                event = new CalendarEvent(CalendarEvent.UPDATE_TYPE_NORMAL, id);
                break;
        }
        RxBus.get().post(event);
        curDailyPlan.mStatus = type;
        curDailyPlanModel.mStatus = type;
        mDailyPlanDao.update(curDailyPlan);
        mView.updateLostOrGet(position);
    }

    @Override
    public void onClickDailyPlanDelete(EverydayReplayModelVM vm) {
        int position = -1;
        for (BaseCalendarModel model : mData) {
            if (model == vm.mEverydayReplayModel) {
                position = mData.indexOf(model);
            }
        }
        if (position != -1) {
            mDailyPlanDao.delete(vm.mEverydayReplayModel.mDailyPlan);
            mView.onClickDailyPlanDelete(position);
        }
    }

    @Override
    public void onSelectDay(CalendarDay selectDay) {
        int days = selectDay.getDay();
        int year = selectDay.getYear();
        int month = selectDay.getMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, days);
        long curTime = calendar.getTimeInMillis();
        calendar.set(year, month, days + 1);
        long nextTime = calendar.getTimeInMillis();

        List<DailyPlan> data = mDailyPlanDao.queryBuilder().where(DailyPlanDao.Properties.MTimeStamp.ge(curTime),
                DailyPlanDao.Properties.MTimeStamp.lt(nextTime))
                .build().list();
        if (data != null && data.size() > 0 && mData != null) {
            mData.removeAll(mData);
            for (DailyPlan dailyPlan : data) {
                mData.add(new DailyPlanModel(dailyPlan));
                mView.showData(mData);
            }
        }
    }
}
