package com.mdove.passwordguard.home.schedule.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.longplan.model.AddLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.LongPlanModel;
import com.mdove.passwordguard.home.schedule.EtScheduleActivity;
import com.mdove.passwordguard.home.schedule.model.AddScheduleModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.schedule.model.ScheduleModel;
import com.mdove.passwordguard.home.schedule.presenter.contract.ScheduleContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/7/1.
 */

public class SchedulePresenter implements ScheduleContract.Presenter {
    private ScheduleContract.MvpView mView;
    private ScheduleDao mScheduleDao;
    private List<BaseScheduleModel> mData;
    private boolean isShort = false;

    public SchedulePresenter() {
        mScheduleDao = App.getDaoSession().getScheduleDao();
    }

    @Override
    public void subscribe(ScheduleContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<Schedule> mMainData = mScheduleDao.queryBuilder().list();

        if (mMainData == null || mMainData.size() == 0) {
            mData.add(new AddScheduleModel());
        }

        for (Schedule schedule : mMainData) {
            mData.add(new ScheduleModel(schedule));
        }

        mView.initData(mData);
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickShowShort() {
        isShort = !isShort;
        mView.showShort(isShort);
    }

    @Override
    public void onClickEtSchedule() {
        EtScheduleActivity.start(mView.getContext());
    }

    @Override
    public void onClickEditSchedule(long id) {
        EtScheduleActivity.start(mView.getContext(), EtScheduleActivity.INTENT_TYPE_SCHEDULE, id);
    }
}
