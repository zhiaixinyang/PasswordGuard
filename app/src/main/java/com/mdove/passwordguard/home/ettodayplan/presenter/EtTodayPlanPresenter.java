package com.mdove.passwordguard.home.ettodayplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.presenter.contract.EtTodayPlanContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtTodayPlanPresenter implements EtTodayPlanContract.Presenter {
    private EtTodayPlanContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private SecondTodayPlanDao mSecondTodayPlanDao;
    private long mMainTodayPlanId = -1;

    public EtTodayPlanPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
    }

    @Override
    public void subscribe(EtTodayPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void addMainTodayPlan(MainTodayPlan todayPlan) {
        mMainTodayPlanId = mMainTodayPlanDao.insert(todayPlan);
        mView.addMainTodayPlanReturn(new MainTodayPlanModel(todayPlan));
    }

    @Override
    public void addSecondTodayPlan(SecondTodayPlan todayPlan) {
        todayPlan.setMMainTodayPlanId(mMainTodayPlanId);
        mSecondTodayPlanDao.insert(todayPlan);
        mView.addMainTodayPlanReturn(new SecondTodayPlanModel(todayPlan));
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickTime() {
        new TimeBottomSheetDialog(mView.getContext()).show();
    }

    @Override
    public void initEditData(long id) {
        MainTodayPlan mainTodayPlan = mMainTodayPlanDao.queryBuilder().
                where(MainTodayPlanDao.Properties.Id.eq(id)).unique();
        if (mainTodayPlan == null) {
            return;
        }

        List<BaseTodayPlanModel> data = new ArrayList<>();
        data.add(new MainTodayPlanModel(mainTodayPlan));
        List<SecondTodayPlan> secondPlanData = mSecondTodayPlanDao.queryBuilder().where(SecondTodayPlanDao.Properties.MMainTodayPlanId.eq(id))
                .list();
        for (SecondTodayPlan secondTodayPlan : secondPlanData) {
            data.add(new SecondTodayPlanModel(secondTodayPlan));
        }

        mView.initEditData(data);
    }
}
