package com.mdove.passwordguard.home.utils;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;

import java.util.Date;

/**
 * Created by MDove on 2018/6/27.
 */

public class TodayPlanUtils {
    public static MainTodayPlan getMainTodatPlan(String content, String tips) {
        MainTodayPlan mainTodayPlan = new MainTodayPlan();
        mainTodayPlan.setMImportant(0);
        mainTodayPlan.setMUrgent(0);
        mainTodayPlan.setMIsSee(0);
        mainTodayPlan.setMIsSuc(0);
        mainTodayPlan.setMLabel("计划");
        mainTodayPlan.setMTips(tips);
        mainTodayPlan.setMTime(new Date().getTime());
        mainTodayPlan.setMTodayPlan(content);

        return mainTodayPlan;
    }

    //需要自己对这个对象进行MainTodayPlanId的赋值
    public static SecondTodayPlan getSecondTodayPlan(String content, String tips) {
        SecondTodayPlan mainTodayPlan = new SecondTodayPlan();
        mainTodayPlan.setMImportant(0);
        mainTodayPlan.setMUrgent(0);
        mainTodayPlan.setMIsSee(0);
        mainTodayPlan.setMIsSuc(0);
        mainTodayPlan.setMLabel("计划");
        mainTodayPlan.setMTips(tips);
        mainTodayPlan.setMTime(new Date().getTime());
        mainTodayPlan.setMTodayPlan(content);

        return mainTodayPlan;
    }
}
