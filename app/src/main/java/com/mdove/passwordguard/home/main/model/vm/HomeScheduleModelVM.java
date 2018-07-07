package com.mdove.passwordguard.home.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.main.model.HomeScheduleModel;
import com.mdove.passwordguard.home.schedule.model.ScheduleModel;
import com.mdove.passwordguard.utils.DateUtils;

public class HomeScheduleModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mSchedule = new ObservableField<>();
    public ObservableField<String> mSelectTime = new ObservableField<>();
    public ObservableField<String> mUrgent = new ObservableField<>();
    public ObservableField<String> mImportant = new ObservableField<>();
    public ObservableField<String> mTips = new ObservableField<>();
    public ObservableField<Long> mId = new ObservableField<>();

    public HomeScheduleModelVM(HomeScheduleModel model) {
        mTime.set("记录于：[" + DateUtils.getDateChinese(model.mTime) + "]");
        mSchedule.set(model.mSchedule);

        String startHour = "";
        if (model.mStartHour <= 9) {
            startHour = "0" + model.mStartHour;
        } else {
            startHour = model.mStartHour + "";
        }
        String startMin = "";
        if (model.mStartMin <= 9) {
            startMin = "0" + model.mStartMin;
        } else {
            startMin = model.mStartMin + "";
        }
        String endHour = "";
        if (model.mEndHour <= 9) {
            endHour = "0" + model.mEndHour;
        } else {
            endHour = model.mEndHour + "";
        }
        String endMin = "";
        if (model.mEndMin <= 9) {
            endMin = "0" + model.mEndMin;
        } else {
            endMin = model.mEndMin + "";
        }
        String selectTime = startHour + ":" + startMin + "-" + endHour + ":" + endMin;
        mSelectTime.set(selectTime);

        mUrgent.set(model.mUrgent + "");
        mImportant.set(model.mImportant + "");
        mTips.set("标签：[" + model.mTips + "]");
        mId.set(model.id);
    }
}
