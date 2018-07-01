package com.mdove.passwordguard.home.allplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.AddTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.EditTodayPlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class AllPlanModel {
    public MainTodayPlan mMainTodayPlan;
    public List<SecondTodayPlan> mSecondTodayPlans;

    public AllPlanModel(MainTodayPlan mainTodayPlan, List<SecondTodayPlan> data) {
        mMainTodayPlan = mainTodayPlan;
        mSecondTodayPlans = data;
    }
}