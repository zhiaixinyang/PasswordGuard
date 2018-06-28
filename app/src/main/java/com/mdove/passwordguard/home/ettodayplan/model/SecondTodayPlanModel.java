package com.mdove.passwordguard.home.ettodayplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;

/**
 * Created by MDove on 2018/6/27.
 */

public class SecondTodayPlanModel extends BaseTodayPlanModel {
    public String mTodayPlan;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public String mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public long mMainTodayPlanId;

    public SecondTodayPlanModel(SecondTodayPlan secondTodayPlan) {
        mTodayPlan = secondTodayPlan.mTodayPlan;

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
