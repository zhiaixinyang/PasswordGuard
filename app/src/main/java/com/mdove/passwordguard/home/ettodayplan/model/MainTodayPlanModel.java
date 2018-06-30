package com.mdove.passwordguard.home.ettodayplan.model;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;

/**
 * Created by MDove on 2018/6/27.
 */

public class MainTodayPlanModel extends BaseTodayPlanModel {
    public long mId;
    public String mTodayPlan;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public String mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;

    public MainTodayPlanModel(MainTodayPlan mainTodayPlan) {
        mId = mainTodayPlan.id;
        mTodayPlan = mainTodayPlan.mTodayPlan;

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
