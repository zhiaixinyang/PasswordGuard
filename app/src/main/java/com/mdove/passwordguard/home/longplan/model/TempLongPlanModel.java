package com.mdove.passwordguard.home.longplan.model;

import com.mdove.passwordguard.greendao.entity.LongPlan;

import java.io.Serializable;

/**
 * Created by MDove on 2018/7/3.
 */

public class TempLongPlanModel implements Serializable{
    public Long id;
    public String mLongPlan;
    public int mIsSuc;//0表示没有点击完成,1表示按时，2表示未完成(提前)，3表示未完成(延迟)
    public int mIsSee;//0表示不在首页展示(暂时没用，历史遗留字段)
    public long mStartTime, mEndTime;
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public TempLongPlanModel(LongPlan longPlan) {
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
