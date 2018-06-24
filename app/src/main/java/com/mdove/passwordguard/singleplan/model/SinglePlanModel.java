package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.greendao.entity.SinglePlan;

import java.util.Date;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanModel {
    public long mId;
    public String mSinglePlan;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mUrgent;
    public int mImportant;
    public long mLabelId;
    public String mLabel;

    public SinglePlanModel(SinglePlan singlePlan) {
        mId = singlePlan.id;
        mSinglePlan = singlePlan.mSinglePlan;
        mTime = singlePlan.mTime;
        mImportant = singlePlan.mImportant;
        mIsSee = singlePlan.mIsSee;
        mIsSuc = singlePlan.mIsSuc;
        mUrgent = singlePlan.mUrgent;
        mLabelId = singlePlan.mLabelId;
        mLabel = singlePlan.mLabel;
    }

    public SinglePlanModel(String singlePlan) {
        mId = -1;
        mSinglePlan = singlePlan;
        mTime = new Date().getTime();
        mImportant = 0;
        mIsSee = 0;
        mIsSuc = 0;
        mUrgent = 0;
        mLabelId = 0;
        mLabel = "默认";
    }
}
