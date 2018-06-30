package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.home.ettodayplan.model.AddTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.EditTodayPlanModel;

import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/27.
 */

public class SinglePlanModel {
    public AddTodayPlanModel mAddTodayPlanModel = new AddTodayPlanModel();
    public MainTodayPlan mMainTodayPlan;
    public EditTodayPlanModel mEditTodayPlanModel;
    public List<SecondTodayPlan> mSecondTodayPlans;

    public SinglePlanModel(MainTodayPlan mainTodayPlan, List<SecondTodayPlan> data) {
        mMainTodayPlan = mainTodayPlan;
        mSecondTodayPlans = data;
        mEditTodayPlanModel = new EditTodayPlanModel();
    }
}