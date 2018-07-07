package com.mdove.passwordguard.home.main.model;

import com.mdove.passwordguard.greendao.entity.Schedule;

public class HomeScheduleModel extends BaseHomeMessModel {
    public String mSchedule;

    public long mStartHour, mStartMin, mEndHour, mEndMin;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public HomeScheduleModel(Schedule schedule) {
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
