package com.mdove.passwordguard.home.todayreview.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.model.vm.MainTodayReViewModelVM;
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
    private List<BaseTodayReViewModel> mData;
    private long mMainTodayPlanId = -1;

    public TodayReViewPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
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

        if (mainTodayPlanId == -1) {
            return;
        }

        MainTodayPlan mainTodayPlan = mMainTodayPlanDao.queryBuilder().where(MainTodayPlanDao.Properties.Id.eq(mainTodayPlanId))
                .unique();
        if (mainTodayPlan == null) {
            return;
        }

        mData.add(new MainTodayReViewModel(mainTodayPlan));
        List<SecondTodayPlan> mSecondData = mSecondTodayPlanDao.queryBuilder().where(SecondTodayPlanDao.Properties.MMainTodayPlanId.
                eq(mainTodayPlan.getId())).build().list();
        for (SecondTodayPlan secondTodayPlan : mSecondData) {
            mData.add(new SecondTodayReViewModel(secondTodayPlan));
        }

        mView.initData(mData);
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
        int hour = DateUtils.getHour(time);
        int min = DateUtils.getMinute(time);

        if (vm instanceof MainTodayReViewModelVM) {
            MainTodayPlan mainTodayPlan = mMainTodayPlanDao.queryBuilder().
                    where(MainTodayPlanDao.Properties.Id.eq(id)).unique();
            int mIsSucStatus = -1;

            if (mainTodayPlan.mIsSuc == 0) {
                if (hour > mainTodayPlan.startHour ||
                        (hour == mainTodayPlan.startHour && min < mainTodayPlan.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_PRE;
                } else if (hour < mainTodayPlan.endHour ||
                        (hour == mainTodayPlan.endHour && min > mainTodayPlan.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_LAST;
                } else {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_AT_TIME;
                }
            } else {
                mIsSucStatus = 0;
            }
            mainTodayPlan.mIsSuc = mIsSucStatus;
            mMainTodayPlanDao.update(mainTodayPlan);

            MainTodayReViewModel model = (MainTodayReViewModel) mData.get(0);
            model.mIsSuc = mIsSucStatus;

            mView.onClickTodayReViewSuc(0);
        } else if (vm instanceof SecondTodayReViewModelVM) {
            SecondTodayPlan secondTodayPlan = mSecondTodayPlanDao.queryBuilder().
                    where(SecondTodayPlanDao.Properties.Id.eq(id)).unique();
            int mIsSucStatus = -1;

            if (secondTodayPlan.mIsSuc == 0) {
                if (hour > secondTodayPlan.startHour ||
                        (hour == secondTodayPlan.startHour && min < secondTodayPlan.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_PRE;
                } else if (hour < secondTodayPlan.endHour ||
                        (hour == secondTodayPlan.endHour && min > secondTodayPlan.startMin)) {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_LAST;
                } else {
                    mIsSucStatus = BaseTodayReViewModel.DEFAULT_SUC_AT_TIME;
                }
            } else {
                mIsSucStatus = 0;
            }
            secondTodayPlan.mIsSuc = mIsSucStatus;
            mSecondTodayPlanDao.update(secondTodayPlan);

            int position = -1;
            for (BaseTodayReViewModel model : mData) {
                if (model instanceof SecondTodayReViewModel) {
                    if (model.mId == secondTodayPlan.id) {
                        model.mIsSuc = mIsSucStatus;
                        position = mData.indexOf(model);
                    }
                }
            }

            if (position != -1) {
                mView.onClickTodayReViewSuc(position);
            }
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
