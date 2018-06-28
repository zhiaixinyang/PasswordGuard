package com.mdove.passwordguard.singleplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.ettodayplan.model.AddTodayPlanModel;
import com.mdove.passwordguard.singleplan.EtSinglePlanActivity;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.presenter.contract.SinglePlanContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanPresenter implements SinglePlanContract.Presenter {
    private SinglePlanContract.MvpView mView;
    private MainTodayPlanDao mMainTodayPlanDao;
    private SecondTodayPlanDao mSecondTodayPlanDao;

    private List<SinglePlanModel> mData;

    public SinglePlanPresenter() {
        mMainTodayPlanDao = App.getDaoSession().getMainTodayPlanDao();
        mSecondTodayPlanDao = App.getDaoSession().getSecondTodayPlanDao();
    }

    @Override
    public void subscribe(SinglePlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<MainTodayPlan> mMainData = mMainTodayPlanDao.queryBuilder().list();

        if (mMainData == null || mMainData.size() == 0) {
            mData.add(new SinglePlanModel(null, null));
        }

        for (MainTodayPlan mainTodayPlan : mMainData) {
            List<SecondTodayPlan> mSecondData = mSecondTodayPlanDao.queryBuilder().where(SecondTodayPlanDao.Properties.MMainTodayPlanId.
                    eq(mainTodayPlan.getId())).build().list();
            mData.add(new SinglePlanModel(mainTodayPlan, mSecondData));
        }

        mView.initData(mData);
    }

    @Override
    public void onClickInEtSinglePlan() {
        EtTodayPlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickEtPlan() {
        EtTodayPlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickDeleteSinglePlan(long id) {
    }
}
