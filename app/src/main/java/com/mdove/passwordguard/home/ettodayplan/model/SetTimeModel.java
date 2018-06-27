package com.mdove.passwordguard.home.ettodayplan.model;

/**
 * Created by MDove on 2018/6/27.
 */

public class SetTimeModel {
    public String mTime;
    public int mSelectStartHour, mSelectStartMin;
    public int mSelectEndHour, mSelectEndMin;

    public SetTimeModel(int selectStartHour, int selectStartMin, int selectEndHour, int selectEndMin) {
        mSelectEndHour = selectEndHour;
        mSelectEndMin = selectEndMin;
        mSelectStartHour = selectStartHour;
        mSelectStartMin = selectStartMin;

        String startHour, startMin;
        String endHour, endMin;

        if (selectStartHour <= 9) {
            startHour = "0" + selectStartHour;
        } else {
            startHour = selectStartHour + "";
        }

        if (selectStartMin <= 9) {
            startMin = "0" + selectStartMin;
        } else {
            startMin = selectStartMin + "";
        }

        if (selectEndHour <= 9) {
            endHour = "0" + selectEndHour;
        } else {
            endHour = selectEndHour + "";
        }

        if (selectEndMin <= 9) {
            endMin = "0" + selectEndMin;
        } else {
            endMin = selectEndMin + "";
        }

        mTime = startHour + ":" + startMin + "-" + endHour + ":" + endMin;
    }
}
