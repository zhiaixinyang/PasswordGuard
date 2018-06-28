package com.mdove.passwordguard.home.utils;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;

import java.util.Date;

/**
 * Created by MDove on 2018/6/27.
 */

public class TodayPlanUtils {
    public static MainTodayPlan getMainTodayPlan(String content, String tips, int important, int urgent,
                                                 int startHour, int startMin, int endHour, int endMin) {
        MainTodayPlan mainTodayPlan = new MainTodayPlan();
        mainTodayPlan.setMImportant(important);
        mainTodayPlan.setMUrgent(urgent);
        mainTodayPlan.setMIsSee(0);
        mainTodayPlan.setMIsSuc(0);
        mainTodayPlan.setMLabel("计划");
        mainTodayPlan.setMTips(tips);
        mainTodayPlan.setMTime(new Date().getTime());
        mainTodayPlan.setMTodayPlan(content);
        mainTodayPlan.setEndHour(endHour);
        mainTodayPlan.setEndMin(endMin);
        mainTodayPlan.setStartHour(startHour);
        mainTodayPlan.setStartMin(startMin);

        return mainTodayPlan;
    }

    //需要自己对这个对象进行MainTodayPlanId的赋值
    public static SecondTodayPlan getSecondTodayPlan(String content, String tips, int important, int urgent,
                                                     int startHour, int startMin, int endHour, int endMin) {
        SecondTodayPlan mainTodayPlan = new SecondTodayPlan();
        mainTodayPlan.setMImportant(important);
        mainTodayPlan.setMUrgent(urgent);
        mainTodayPlan.setMIsSee(0);
        mainTodayPlan.setMIsSuc(0);
        mainTodayPlan.setMLabel("计划");
        mainTodayPlan.setMTips(tips);
        mainTodayPlan.setMTime(new Date().getTime());
        mainTodayPlan.setMTodayPlan(content);
        mainTodayPlan.setEndHour(endHour);
        mainTodayPlan.setEndMin(endMin);
        mainTodayPlan.setStartHour(startHour);
        mainTodayPlan.setStartMin(startMin);

        return mainTodayPlan;
    }
}
