package com.mdove.passwordguard.home.alltodayplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.alltodayplan.presenter.contract.AllTodayPlanContract;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class AllTodayPlanPresenter implements AllTodayPlanContract.Presenter {
    private AllTodayPlanContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private SecondTodayPlanDao mSecondTodayPlanDao;
    private List<BaseTodayPlanModel> mData;

    public AllTodayPlanPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
    }

    @Override
    public void subscribe(AllTodayPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData(long id) {
        mData = new ArrayList<>();

        MainTodayPlan mainTodayPlan = mMainTodayPlanDao.queryBuilder().where(MainTodayPlanDao.Properties.Id.eq(id)).unique();
        if (mainTodayPlan == null) {
            return;
        }
        mData.add(new MainTodayPlanModel(mainTodayPlan));

        List<SecondTodayPlan> mPlanData = mSecondTodayPlanDao.queryBuilder().
                where(SecondTodayPlanDao.Properties.MMainTodayPlanId.eq(mainTodayPlan.getId())).list();
        for (SecondTodayPlan secondTodayPlan : mPlanData) {
            mData.add(new SecondTodayPlanModel(secondTodayPlan));
        }

        mView.initData(mData);
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }
}
