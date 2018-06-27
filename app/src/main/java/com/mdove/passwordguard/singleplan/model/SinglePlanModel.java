package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.greendao.entity.SinglePlan;

import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/27.
 */

public class SinglePlanModel {
    public MainTodayPlan mMainTodayPlan;
    public List<SecondTodayPlan> mSecondTodayPlans;

    public SinglePlanModel(MainTodayPlan mainTodayPlan, List<SecondTodayPlan> data) {
        mMainTodayPlan = mainTodayPlan;
        mSecondTodayPlans = data;
    }
}