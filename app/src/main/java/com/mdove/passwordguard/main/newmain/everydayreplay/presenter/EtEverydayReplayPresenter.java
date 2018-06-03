package com.mdove.passwordguard.main.newmain.everydayreplay.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.model.CalendarEvent;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.presenter.contract.EtEverydayReplayContract;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.ToastHelper;
import com.mdove.passwordguard.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class EtEverydayReplayPresenter implements EtEverydayReplayContract.Presenter {
    private EtEverydayReplayContract.MvpView mView;
    private List<BaseCalendarModel> mData;
    private DailyPlanDao mDailyPlanDao;

    @Override
    public void subscribe(EtEverydayReplayContract.MvpView view) {
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
            mData.add(new DailyPlanModel(plan));
        }

        mView.showData(mData);
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
    public void onClickDailyPlanDelete(EverydayReplayRlvModelVM vm) {
        int position = -1;
        for (BaseCalendarModel model : mData) {
            if (model == vm.mDailyPlanModel) {
                position = mData.indexOf(model);
            }
        }
        if (position != -1) {
            mDailyPlanDao.delete(vm.mDailyPlanModel.mDailyPlan);
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

    @Override
    public boolean addDailyPlan(long selectTime,String string, int status) {
        long realTime=0L;
        if (isSameDay(selectTime)) {
            realTime=new Date().getTime();
        }else{
            if (selectTime>new Date().getTime()){
                ToastHelper.shortToast("大兄弟，复盘的太超前点了吧...");
                return false;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            long curTime=new Date().getTime();
            calendar.set(DateUtils.getYear(curTime), DateUtils.getMonth(curTime)-1, DateUtils.getDay(curTime));
            realTime=selectTime+(curTime-calendar.getTimeInMillis());
        }
        DailyPlan dailyPlan = new DailyPlan();
        dailyPlan.mContent = string;
        dailyPlan.mTimeStamp = realTime;
        dailyPlan.mStatus = status;
        mDailyPlanDao.insert(dailyPlan);

        mData.add(new DailyPlanModel(dailyPlan));
        mView.addDailyPlan(mData.size());
        return true;
    }

    private boolean isSameDay(long selectTime){
        return DateUtils.isSameDay(selectTime,new Date().getTime());
    }
}
