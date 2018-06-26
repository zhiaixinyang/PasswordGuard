package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.greendao.entity.SinglePlan;

import java.util.Date;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanRlvModel {
    public String mSinglePlan;
    public String mSinglePlanTips;
    public int mIsMain;

    public SinglePlanRlvModel(String singlePlan) {
        mSinglePlan = singlePlan;

    }
}
