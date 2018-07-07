package com.mdove.passwordguard.home.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.base.listlayout.TestActivity;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.longplan.LongPlanActivity;
import com.mdove.passwordguard.home.main.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.main.presenter.contract.NewHomeContract;
import com.mdove.passwordguard.home.schedule.ScheduleActivity;
import com.mdove.passwordguard.home.schedule.model.AddScheduleModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.schedule.model.ScheduleModel;
import com.mdove.passwordguard.home.todayreview.TodayReViewActivity;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class NewHomePresenter implements NewHomeContract.Presenter {
    private NewHomeContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private ScheduleDao mScheduleDao;

    public NewHomePresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mScheduleDao = App.getDaoSession().getScheduleDao();
    }

    @Override
    public void subscribe(NewHomeContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initTime() {
        Long time = new Date().getTime();
        String year = DateUtils.getYear(time) + "年";
        String month = DateUtils.getSimpleMonthC(true);
        String day = DateUtils.getDay(time) + "日";

        mView.initTime(new HomeTimeModelVM(year, month, day));
    }

    @Override
    public void initSchedule() {
        List<BaseScheduleModel> modelData = new ArrayList<>();
        List<Schedule> data = mScheduleDao.queryBuilder().list();
        modelData.add(new AddScheduleModel());
        if (data != null) {
            for (Schedule schedule : data) {
                modelData.add(new ScheduleModel(schedule));
            }
            mView.initSchedule(modelData);
        }
    }

    @Override
    public void onClickSchedule() {
        ScheduleActivity.start(mView.getContext());
    }

    @Override
    public void onClickTodayPlanReView() {
        TodayReViewActivity.start(mView.getContext());

//        long time = new Date().getTime();
//        int year = DateUtils.getYear(time);
//        int month = DateUtils.getMonth(time) - 1;
//        int days = DateUtils.getDay(time);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(year, month, days);
//        long curTime = calendar.getTimeInMillis();
//        //计算时候月份要按从0开始，才是正确的
//        calendar.set(year, month, days + 1);
//        long nextTime = calendar.getTimeInMillis();
//
//        List<MainTodayPlan> planData = mMainTodayPlanDao.queryBuilder().where(MainTodayPlanDao.Properties.MTime.ge(curTime),
//                MainTodayPlanDao.Properties.MTime.lt(nextTime)).build().list();
//        if (planData != null && planData.size() > 0) {
//            TodayReViewActivity.start(mView.getContext(), planData.get(0).id);
//        } else {
//            ToastHelper.shortToast("今天还没有任何计划");
//        }
    }

    @Override
    public void onClickAllPlan() {
//        RichEditorActivity.start(mView.getContext());
        TestActivity.start(mView.getContext());
    }

    @Override
    public void onClickEtLongPlan() {
        EtLongPlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickLongPlan() {
        LongPlanActivity.start(mView.getContext());
    }
}
