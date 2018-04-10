package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.DailyPlan;

/**
 * Created by MDove on 2018/4/9.
 */

public class DailyPlanModel {
    public static final int STATUS_LOST = 2;
    public static final int STATUS_GET = 1;
    public static final int STATUS_NORMAL = 0;

    public String mContent;
    public long mTime;
    public long mId;
    public int mStatus;
    public DailyPlan mDailyPlan;

    public DailyPlanModel(DailyPlan dailyPlan) {
        mContent = dailyPlan.mContent;
        mTime = dailyPlan.mTimeStamp;
        mId = dailyPlan.id;
        mStatus = dailyPlan.mStatus;
        mDailyPlan = dailyPlan;
    }
}
