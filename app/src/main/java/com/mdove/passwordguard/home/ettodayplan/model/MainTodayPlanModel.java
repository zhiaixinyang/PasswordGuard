package com.mdove.passwordguard.home.ettodayplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;

/**
 * Created by MDove on 2018/6/27.
 */

public class MainTodayPlanModel extends BaseTodayPlanModel {
    public String mTodayPlan;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;

    public MainTodayPlanModel(MainTodayPlan mainTodayPlan) {
        mTodayPlan = mainTodayPlan.mTodayPlan;
        mTime = mainTodayPlan.mTime;
        mIsSuc = mainTodayPlan.mIsSuc;
        mIsSee = mainTodayPlan.mIsSee;
        mUrgent = mainTodayPlan.mUrgent;
        mImportant = mainTodayPlan.mImportant;
        mLabel = mainTodayPlan.mLabel;
        mLabelId = mainTodayPlan.mLabelId;
    }
}
