package com.mdove.passwordguard.main.newmain.home.model;

import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.greendao.entity.DailyPlan;

/**
 * Created by MDove on 2018/5/3.
 */

public class EverydayReplayModel extends BaseCalendarModel {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_GET = 1;
    public static final int STATUS_LOST = 2;
    public static final int STATUS_EMPTY = 3;

    public String mContent;
    public long mTime;
    public long mId;
    public int mStatus;
    public DailyPlan mDailyPlan;

    public EverydayReplayModel(DailyPlan dailyPlan) {
        mContent = dailyPlan.mContent;
        mTime = dailyPlan.mTimeStamp;
        mId = dailyPlan.id;
        mStatus = dailyPlan.mStatus;
        mDailyPlan = dailyPlan;
    }
}
