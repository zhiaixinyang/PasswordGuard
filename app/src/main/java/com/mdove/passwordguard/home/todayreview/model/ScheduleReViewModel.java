package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;

/**
 * Created by MDove on 2018/7/2.
 */
public class ScheduleReViewModel extends BaseTodayReViewModel {
    public String mSchedule;
    public int mIsSee;//0表示不在首页展示
    public String mSelectTime;
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public int mStartHour, mStartMin;
    public int mEndHour, mEndMin;
    public int mSucStartHour, mSucStartMin;
    public int mSucEndHour, mSucEndMin;

    public ScheduleReViewModel(Schedule schedule) {
        mId = schedule.id;

        mSchedule = schedule.mSchedule;

        mStartHour = schedule.startHour;
        mStartMin = schedule.startMin;
        mEndHour = schedule.endHour;
        mEndMin = schedule.endMin;
        mSucStartHour = schedule.sucStartHour;
        mSucStartMin = schedule.sucStartMin;
        mSucEndHour = schedule.sucEndHour;
        mSucEndMin = schedule.sucEndMin;

        String startHour = "";
        if (schedule.getStartHour() <= 9) {
            startHour = "0" + schedule.getStartHour();
        } else {
            startHour = schedule.getStartHour() + "";
        }
        String startMin = "";
        if (schedule.getStartMin() <= 9) {
            startMin = "0" + schedule.getStartMin();
        } else {
            startMin = schedule.getStartMin() + "";
        }
        String endHour = "";
        if (schedule.getEndHour() <= 9) {
            endHour = "0" + schedule.getEndHour();
        } else {
            endHour = schedule.getEndHour() + "";
        }
        String endMin = "";
        if (schedule.getEndMin() <= 9) {
            endMin = "0" + schedule.getEndMin();
        } else {
            endMin = schedule.getEndMin() + "";
        }
        mSelectTime = startHour + ":" + startMin + "-" + endHour + ":" + endMin;

        mIsSuc = schedule.mIsSuc;
        mIsSee = schedule.mIsSee;
        mUrgent = schedule.mUrgent;
        mImportant = schedule.mImportant;
        mLabel = schedule.mLabel;
        mLabelId = schedule.mLabelId;
        mTips = schedule.mTips;
        mTime = schedule.mTime;
    }
}
