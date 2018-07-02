package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.schedule.model.ScheduleModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/7/2.
 */

public class ScheduleReViewModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mSchedule = new ObservableField<>();
    public ObservableField<String> mSelectTime = new ObservableField<>();
    public ObservableField<String> mUrgent = new ObservableField<>();
    public ObservableField<String> mImportant = new ObservableField<>();
    public ObservableField<String> mTips = new ObservableField<>();
    public ObservableField<Long> mId = new ObservableField<>();

    public ScheduleReViewModelVM(ScheduleReViewModel model) {
        mTime.set("记录于：[" + DateUtils.getDateChinese(model.mTime) + "]");
        mSchedule.set(model.mSchedule);
        mSelectTime.set(model.mSelectTime);

        mUrgent.set(model.mUrgent + "");
        mImportant.set(model.mImportant + "");
        mTips.set("标签：[" + model.mTips + "]");
        mId.set(model.mId);
    }
}
