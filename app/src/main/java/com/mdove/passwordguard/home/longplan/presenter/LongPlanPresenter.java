package com.mdove.passwordguard.home.longplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.alltodayplan.AllTodayPlanActivity;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.longplan.LongPlanActivity;
import com.mdove.passwordguard.home.longplan.model.AddLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.BaseLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.LongPlanModel;
import com.mdove.passwordguard.home.longplan.presenter.contract.LongPlanContract;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.presenter.contract.SinglePlanContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/7/1.
 */

public class LongPlanPresenter implements LongPlanContract.Presenter {
    private LongPlanContract.MvpView mView;
    private LongPlanDao mLongPlanDao;
    private List<BaseLongPlanModel> mData;

    public LongPlanPresenter() {
        mLongPlanDao = App.getDaoSession().getLongPlanDao();
    }

    @Override
    public void subscribe(LongPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<LongPlan> mMainData = mLongPlanDao.queryBuilder().list();

        if (mMainData == null || mMainData.size() == 0) {
            mData.add(new AddLongPlanModel());
        }

        for (LongPlan longPlan : mMainData) {
            mData.add(new LongPlanModel(longPlan));
        }

        mView.initData(mData);
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickAddLongPlan() {
        EtLongPlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickEditLongPlan(long id) {
        EtLongPlanActivity.start(mView.getContext(), EtLongPlanActivity.INTENT_TYPE_EDIT_PLAN, id);
    }
}
