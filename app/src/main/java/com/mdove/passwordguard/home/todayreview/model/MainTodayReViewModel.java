package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;

/**
 * Created by MDove on 2018/6/29.
 */

public class MainTodayReViewModel extends BaseTodayReViewModel {
    public String mTodayPlan;
    public int mIsSee;//0表示不在首页展示
    public String mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;

    public int mStartHour, mStartMin;
    public int mEndHour, mEndMin;
    public int mSucStartHour, mSucStartMin;
    public int mSucEndHour, mSucEndMin;

    public MainTodayReViewModel(MainTodayPlan mainTodayPlan) {
        mId = mainTodayPlan.id;

        mTodayPlan = mainTodayPlan.mTodayPlan;

        mStartHour = mainTodayPlan.startHour;
        mStartMin = mainTodayPlan.startMin;
        mEndHour = mainTodayPlan.endHour;
        mEndMin = mainTodayPlan.endMin;
        mSucStartHour = mainTodayPlan.sucStartHour;
        mSucStartMin = mainTodayPlan.sucStartMin;
        mSucEndHour = mainTodayPlan.sucEndHour;
        mSucEndMin = mainTodayPlan.sucEndMin;

        String startHour = "";
        if (mainTodayPlan.getStartHour() <= 9) {
            startHour = "0" + mainTodayPlan.getStartHour();
        } else {
            startHour = mainTodayPlan.getStartHour() + "";
        }
        String startMin = "";
        if (mainTodayPlan.getStartMin() <= 9) {
            startMin = "0" + mainTodayPlan.getStartMin();
        } else {
            startMin = mainTodayPlan.getStartMin() + "";
        }
        String endHour = "";
        if (mainTodayPlan.getEndHour() <= 9) {
            endHour = "0" + mainTodayPlan.getEndHour();
        } else {
            endHour = mainTodayPlan.getEndHour() + "";
        }
        String endMin = "";
        if (mainTodayPlan.getEndMin() <= 9) {
            endMin = "0" + mainTodayPlan.getEndMin();
        } else {
            endMin = mainTodayPlan.getEndMin() + "";
        }
        mTime = startHour + ":" + startMin + "-" + endHour + ":" + endMin;

        mIsSuc = mainTodayPlan.mIsSuc;
        mIsSee = mainTodayPlan.mIsSee;
        mUrgent = mainTodayPlan.mUrgent;
        mImportant = mainTodayPlan.mImportant;
        mLabel = mainTodayPlan.mLabel;
        mLabelId = mainTodayPlan.mLabelId;
    }
}
