package com.mdove.passwordguard.home.allplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.allplan.model.AllPlanModel;
import com.mdove.passwordguard.home.allplan.presenter.contract.AllPlanContract;
import com.mdove.passwordguard.home.alltodayplan.AllTodayPlanActivity;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class AllPlanPresenter implements AllPlanContract.Presenter {
    private AllPlanContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private SecondTodayPlanDao mSecondTodayPlanDao;

    private List<AllPlanModel> mData;

    public AllPlanPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
    }

    @Override
    public void subscribe(AllPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<MainTodayPlan> mMainData = mMainTodayPlanDao.queryBuilder().list();

        for (MainTodayPlan mainTodayPlan : mMainData) {
            List<SecondTodayPlan> mSecondData = mSecondTodayPlanDao.queryBuilder().where(SecondTodayPlanDao.Properties.MMainTodayPlanId.
                    eq(mainTodayPlan.getId())).build().list();
            mData.add(new AllPlanModel(mainTodayPlan, mSecondData));
        }

        mView.initData(mData);
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickEtPlan() {
        EtTodayPlanActivity.start(mView.getContext());
    }
}
