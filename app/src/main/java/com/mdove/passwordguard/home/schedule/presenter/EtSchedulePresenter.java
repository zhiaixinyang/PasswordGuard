package com.mdove.passwordguard.home.schedule.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.schedule.presenter.contract.EtScheduleContract;

import java.util.Date;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtSchedulePresenter implements EtScheduleContract.Presenter {
    private EtScheduleContract.MvpView mView;
    private ScheduleDao mScheduleDao;
    private Schedule mNeedUpdateSchedule;

    public EtSchedulePresenter() {
        mScheduleDao = App.getDaoSession().getScheduleDao();
    }

    @Override
    public void subscribe(EtScheduleContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        mScheduleDao.insert(schedule);
    }

    @Override
    public void onClickTimePicker() {
        mView.showTimePicker();
    }


    @Override
    public void initEditData(long id) {
        mNeedUpdateSchedule = mScheduleDao.queryBuilder().where(ScheduleDao.Properties.Id.eq(id)).unique();
        if (mNeedUpdateSchedule != null) {
            mView.initEditData(mNeedUpdateSchedule);
        }
    }

    @Override
    public void editSchedule(Schedule schedule) {
        if (mNeedUpdateSchedule != null) {
            mNeedUpdateSchedule.setMTime(new Date().getTime());
            mNeedUpdateSchedule.setMSchedule(schedule.mSchedule);
            mNeedUpdateSchedule.setMTips(schedule.mTips);
            mNeedUpdateSchedule.setMUrgent(schedule.mUrgent);
            mNeedUpdateSchedule.setMImportant(schedule.mImportant);
            mNeedUpdateSchedule.setStartHour(schedule.startHour);
            mNeedUpdateSchedule.setStartMin(schedule.startMin);
            mNeedUpdateSchedule.setEndMin(schedule.endMin);
            mNeedUpdateSchedule.setEndHour(schedule.endHour);
            mScheduleDao.update(mNeedUpdateSchedule);
        }
    }
}
