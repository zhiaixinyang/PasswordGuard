package com.mdove.passwordguard.home.todayreview.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.EmptyScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.model.vm.MainTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.ScheduleReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.SecondTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.contract.TodayReViewContract;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/29.
 */

public class TodayReViewPresenter implements TodayReViewContract.Presenter {
    private TodayReViewContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private SecondTodayPlanDao mSecondTodayPlanDao;
    private ScheduleDao mScheduleDao;
    private List<BaseTodayReViewModel> mData;
    private long mMainTodayPlanId = -1;

    public TodayReViewPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
        mScheduleDao = App.getDaoSession().getScheduleDao();
    }

    @Override
    public void subscribe(TodayReViewContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData(long mainTodayPlanId) {
        mData = new ArrayList<>();

        List<Schedule> data = mScheduleDao.queryBuilder().list();

        if (data == null || data.size() == 0) {
            mData.add(new EmptyScheduleReViewModel());
        }

        for (Schedule schedule : data) {
            mData.add(new ScheduleReViewModel(schedule));
        }
        mView.initData(mData);
//        if (mainTodayPlanId == -1) {
//            return;
//        }
//        MainTodayPlan mainTodayPlan = mMainTodayPlanDao.queryBuilder().where(MainTodayPlanDao.Properties.Id.eq(mainTodayPlanId))
//                .unique();
//        if (mainTodayPlan == null) {
//            return;
//        }
//
//        mData.add(new MainTodayReViewModel(mainTodayPlan));
//        List<SecondTodayPlan> mSecondData = mSecondTodayPlanDao.queryBuilder().where(SecondTodayPlanDao.Properties.MMainTodayPlanId.
//                eq(mainTodayPlan.getId())).build().list();
//        for (SecondTodayPlan secondTodayPlan : mSecondData) {
//            mData.add(new SecondTodayReViewModel(secondTodayPlan));
//        }
    }

    @Override
    public void addMainTodayReView(MainTodayPlan todayPlan) {
        mMainTodayPlanId = mMainTodayPlanDao.insert(todayPlan);
        mView.addMainTodayReViewReturn(new MainTodayReViewModel(todayPlan));
    }

    @Override
    public void addSecondTodayReView(SecondTodayPlan todayPlan) {
        todayPlan.setMMainTodayPlanId(mMainTodayPlanId);
        mSecondTodayPlanDao.insert(todayPlan);
        mView.addMainTodayReViewReturn(new SecondTodayReViewModel(todayPlan));
    }

    @Override
    public void onClickTodayReViewSuc(BaseTodayReViewVM vm) {
        long id = vm.mId.get();
        long time = new Date().getTime();
        int hour = DateUtils.getHour();
        int min = DateUtils.getMinute(time);

        if (vm instanceof ScheduleReViewModelVM) {
            Schedule schedule = mScheduleDao.queryBuilder().
                    where(ScheduleDao.Properties.Id.eq(id)).unique();
            int mIsSucStatus = -1;

            if (schedule.mIsSuc == 0) {
                if (hour < schedule.startHour ||
                        (hour == schedule.startHour && min < schedule.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_PRE;
                } else if (hour > schedule.endHour ||
                        (hour == schedule.endHour && min > schedule.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_LAST;
                } else {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_AT_TIME;
                }
            } else {
                mIsSucStatus = 0;
            }
            schedule.mIsSuc = mIsSucStatus;
            mScheduleDao.update(schedule);

            int position = -1;
            for (BaseTodayReViewModel reViewModel : mData) {
                if (reViewModel instanceof ScheduleReViewModel) {
                    if (reViewModel.mId == id) {
                        position = mData.indexOf(reViewModel);
                        reViewModel.mIsSuc = mIsSucStatus;
                    }
                }
            }

            mView.onClickTodayReViewSuc(position);
        }
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickTime() {
        new TimeBottomSheetDialog(mView.getContext()).show();
    }
}
