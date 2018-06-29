package com.mdove.passwordguard.home.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.presenter.contract.NewHomeContract;
import com.mdove.passwordguard.home.todayreview.TodayReViewActivity;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.singleplan.SinglePlanActivity;
import com.mdove.passwordguard.task.LabelSettingActivity;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class NewHomePresenter implements NewHomeContract.Presenter {
    private NewHomeContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;

    public NewHomePresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
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
        String day = DateUtils.getDay(time) + "";

        mView.initTime(new HomeTimeModelVM(year, month, day));
    }

    @Override
    public void onClickEtTodayPlan() {
        SinglePlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickTodayPlanReView() {
        long time = new Date().getTime();
        int year = DateUtils.getYear(time);
        int month = DateUtils.getMonth(time);
        int days = DateUtils.getDay(time);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, days);
        long curTime = calendar.getTimeInMillis();
        calendar.set(year, month, days + 1);
        long nextTime = calendar.getTimeInMillis();

        List<MainTodayPlan> planData = mMainTodayPlanDao.queryBuilder().where(MainTodayPlanDao.Properties.MTime.ge(curTime),
                MainTodayPlanDao.Properties.MTime.lt(nextTime)).build().list();
        if (planData != null && planData.size() > 0) {
            TodayReViewActivity.start(mView.getContext(), planData.get(0).id);
        } else {
            ToastHelper.shortToast("今天还没有任何计划");
        }
    }
}
