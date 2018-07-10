package com.mdove.passwordguard.home.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.CustomReViewDao;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.main.model.BaseHomeMessModel;
import com.mdove.passwordguard.home.main.model.HomeCustomReViewModel;
import com.mdove.passwordguard.home.main.model.HomeLongPlanModel;
import com.mdove.passwordguard.home.main.model.HomeScheduleModel;
import com.mdove.passwordguard.home.main.presenter.contract.HomeMessContract;

import java.util.ArrayList;
import java.util.List;

public class HomeMessPresenter implements HomeMessContract.Presenter {
    private HomeMessContract.MvpView mView;
    private List<BaseHomeMessModel> mData;
    private ScheduleDao mScheduleDao;
    private LongPlanDao mLongPlanDao;
    private CustomReViewDao mCustomReViewDao;

    public HomeMessPresenter() {
        mScheduleDao = App.getDaoSession().getScheduleDao();
        mLongPlanDao = App.getDaoSession().getLongPlanDao();
        mCustomReViewDao = App.getDaoSession().getCustomReViewDao();
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<Schedule> schedules = mScheduleDao.queryBuilder().list();
        for (Schedule schedule : schedules) {
            mData.add(new HomeScheduleModel(schedule));
        }

        List<LongPlan> longPlans = mLongPlanDao.queryBuilder().list();
        for (LongPlan longPlan : longPlans) {
            mData.add(new HomeLongPlanModel(longPlan));
        }

        List<CustomReView> customReViews = mCustomReViewDao.queryBuilder().list();
        for (CustomReView customReView : customReViews) {
            mData.add(new HomeCustomReViewModel(customReView));
        }

        mView.initData(mData);
    }

    @Override
    public void subscribe(HomeMessContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
