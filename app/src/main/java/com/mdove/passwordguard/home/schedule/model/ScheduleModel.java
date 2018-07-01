package com.mdove.passwordguard.home.schedule.model;

import com.mdove.passwordguard.greendao.entity.Schedule;

/**
 * Created by MDove on 2018/7/1.
 */

public class ScheduleModel extends BaseScheduleModel {
    public Long id;
    public String mSchedule;
    public int mIsSuc;//0表示没有点击完成,1表示按时，2表示未完成(提前)，3表示未完成(延迟)
    public int mIsSee;//0表示不在首页展示(暂时没用，历史遗留字段)
    public long mStartHour, mStartMin, mEndHour, mEndMin;
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public ScheduleModel(Schedule schedule) {
        id = schedule.id;
        mSchedule = schedule.mSchedule;
        mIsSuc = schedule.mIsSuc;
        mIsSee = schedule.mIsSee;
        mTime = schedule.mTime;
        mStartHour = schedule.startHour;
        mStartMin = schedule.startMin;
        mEndHour = schedule.endHour;
        mEndMin = schedule.endMin;
        mUrgent = schedule.mUrgent;
        mImportant = schedule.mImportant;
        mLabelId = schedule.mLabelId;
        mLabel = schedule.mLabel;
        mTips = schedule.mTips;
    }
}
