package com.mdove.passwordguard.main.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.presenter.contract.TodayPlanContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/4/9.
 */

public class TodayPlanPresenter implements TodayPlanContract.Presenter {
    private TodayPlanContract.MvpView mView;
    private List<DailyPlanModel> mData;
    private DailyPlanDao mDailyPlanDao;

    public TodayPlanPresenter() {
        mData = new ArrayList<>();

        mDailyPlanDao = App.getDaoSession().getDailyPlanDao();
    }

    @Override
    public void subscribe(TodayPlanContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        List<DailyPlan> data = mDailyPlanDao.queryBuilder().list();
        for (DailyPlan plan : data) {
            mData.add(new DailyPlanModel(plan));
        }
        mView.initData(mData);
    }

    @Override
    public void addDailyPlan(String string) {
        DailyPlan dailyPlan = new DailyPlan();
        dailyPlan.mContent = string;
        dailyPlan.mTimeStamp = new Date().getTime();
        dailyPlan.mStatus = 0;
        mDailyPlanDao.insert(dailyPlan);

        mData.add(new DailyPlanModel(dailyPlan));
        mView.addDailyPlan(mData.size());
    }

    @Override
    public void updateLostOrGet(long id, int type) {
        DailyPlan curDailyPlan = null;
        DailyPlanModel curDailyPlanModel = null;
        int position = -1;
        for (DailyPlanModel dailyPlanModel : mData) {
            if (id == dailyPlanModel.mId) {
                curDailyPlan = dailyPlanModel.mDailyPlan;
                curDailyPlanModel = dailyPlanModel;
                position = mData.indexOf(dailyPlanModel);
            }
        }
        if (curDailyPlan == null || position == -1) {
            return;
        }
        curDailyPlan.mStatus = type;
        curDailyPlanModel.mStatus = type;
        mDailyPlanDao.update(curDailyPlan);
        mView.updateLostOrGet(position);
    }

    @Override
    public void onClickDailyPlanDelete(DailyPlanModelVM vm) {
        int position = -1;
        for (DailyPlanModel model : mData) {
            if (model == vm.mDailyPlanModel) {
                position = mData.indexOf(model);
            }
        }
        if (position != -1) {
            mDailyPlanDao.delete(vm.mDailyPlanModel.mDailyPlan);
            mView.onClickDailyPlanDelete(position);
        }
    }
}
