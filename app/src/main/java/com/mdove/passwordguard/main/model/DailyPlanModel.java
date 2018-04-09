package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.DailyPlan;

/**
 * Created by MDove on 2018/4/9.
 */

public class DailyPlanModel{
    public String mContent;
    public long mTime;
    public long mId;

    public DailyPlanModel(DailyPlan dailyPlan) {
        mContent = dailyPlan.mContent;
        mTime = dailyPlan.mTimeStamp;
        mId = dailyPlan.id;
    }
}
