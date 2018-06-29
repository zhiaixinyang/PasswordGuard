package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;

/**
 * Created by MDove on 2018/6/29.
 */

public class SecondTodayReViewModel extends BaseTodayReViewModel {
    public String mTodayPlan;
    public int mIsSee;//0表示不在首页展示
    public String mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public long mMainTodayPlanId;

    public int mStartHour, mStartMin;
    public int mEndHour, mEndMin;
    public int mSucStartHour, mSucStartMin;
    public int mSucEndHour, mSucEndMin;

    public SecondTodayReViewModel(SecondTodayPlan secondTodayPlan) {
        mId = secondTodayPlan.id;
        mTodayPlan = secondTodayPlan.mTodayPlan;

        mStartHour = secondTodayPlan.startHour;
        mStartMin = secondTodayPlan.startMin;
        mEndHour = secondTodayPlan.endHour;
        mEndMin = secondTodayPlan.endMin;
        mSucStartHour = secondTodayPlan.sucStartHour;
        mSucStartMin = secondTodayPlan.sucStartMin;
        mSucEndHour = secondTodayPlan.sucEndHour;
        mSucEndMin = secondTodayPlan.sucEndMin;

        String startHour = "";
        if (secondTodayPlan.getStartHour() <= 9) {
            startHour = "0" + secondTodayPlan.getStartHour();
        } else {
            startHour = secondTodayPlan.getStartHour() + "";
        }
        String startMin = "";
        if (secondTodayPlan.getStartMin() <= 9) {
            startMin = "0" + secondTodayPlan.getStartMin();
        } else {
            startMin = secondTodayPlan.getStartMin() + "";
        }
        String endHour = "";
        if (secondTodayPlan.getEndHour() <= 9) {
            endHour = "0" + secondTodayPlan.getEndHour();
        } else {
            endHour = secondTodayPlan.getEndHour() + "";
        }
        String endMin = "";
        if (secondTodayPlan.getEndMin() <= 9) {
            endMin = "0" + secondTodayPlan.getEndMin();
        } else {
            endMin = secondTodayPlan.getEndMin() + "";
        }
        mTime = startHour + ":" + startMin + "-" + endHour + ":" + endMin;

        mIsSuc = secondTodayPlan.mIsSuc;
        mIsSee = secondTodayPlan.mIsSee;
        mUrgent = secondTodayPlan.mUrgent;
        mImportant = secondTodayPlan.mImportant;
        mLabel = secondTodayPlan.mLabel;
        mLabelId = secondTodayPlan.mLabelId;
        mMainTodayPlanId = secondTodayPlan.mMainTodayPlanId;
    }
}
