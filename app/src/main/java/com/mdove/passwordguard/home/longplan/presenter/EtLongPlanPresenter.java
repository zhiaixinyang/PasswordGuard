package com.mdove.passwordguard.home.longplan.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.LongPlanDao;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.home.longplan.presenter.contract.EtLongPlanContract;

import java.util.Date;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtLongPlanPresenter implements EtLongPlanContract.Presenter {
    private EtLongPlanContract.MvpView mView;
    private LongPlanDao mLongPlanDao;
    private LongPlan mNeedUpdateLongPlan;

    public EtLongPlanPresenter() {
        mLongPlanDao = App.getDaoSession().getLongPlanDao();
    }

    @Override
    public void subscribe(EtLongPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void initLongPlan() {

    }

    @Override
    public void addLongPlan(LongPlan longPlan) {
        mLongPlanDao.insert(longPlan);
    }

    @Override
    public void onClickStartTimePicker() {
        mView.showStartTimePicker();
    }

    @Override
    public void onClickEndTimePicker() {
        mView.showEndTimePicker();
    }

    @Override
    public void initEditData(long id) {
        mNeedUpdateLongPlan = mLongPlanDao.queryBuilder().where(LongPlanDao.Properties.Id.eq(id)).unique();
        if (mNeedUpdateLongPlan != null) {
            mView.initEditData(mNeedUpdateLongPlan);
        }
    }

    @Override
    public void editLongPlan(LongPlan longPlan) {
        if (mNeedUpdateLongPlan != null) {
            mNeedUpdateLongPlan.setMTime(new Date().getTime());
            mNeedUpdateLongPlan.setMLongPlan(longPlan.mLongPlan);
            mNeedUpdateLongPlan.setMTips(longPlan.mTips);
            mNeedUpdateLongPlan.setMUrgent(longPlan.mUrgent);
            mNeedUpdateLongPlan.setMImportant(longPlan.mImportant);
            mNeedUpdateLongPlan.setMEndTime(longPlan.mEndTime);
            mNeedUpdateLongPlan.setMStartTime(longPlan.mStartTime);
            mLongPlanDao.update(mNeedUpdateLongPlan);
        }
    }
}
