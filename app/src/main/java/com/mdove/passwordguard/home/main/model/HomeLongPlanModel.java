package com.mdove.passwordguard.home.main.model;

import com.mdove.passwordguard.greendao.entity.LongPlan;

public class HomeLongPlanModel extends BaseHomeMessModel {
    public String mLongPlan;
    public long mStartTime, mEndTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public HomeLongPlanModel(LongPlan longPlan) {
        id = longPlan.id;
        mLongPlan = longPlan.mLongPlan;
        mIsSuc = longPlan.mIsSuc;
        mIsSee = longPlan.mIsSee;
        mTime = longPlan.mTime;
        mStartTime = longPlan.mStartTime;
        mEndTime = longPlan.mEndTime;
        mUrgent = longPlan.mUrgent;
        mImportant = longPlan.mImportant;
        mLabelId = longPlan.mLabelId;
        mLabel = longPlan.mLabel;
        mTips = longPlan.mTips;
    }
}
