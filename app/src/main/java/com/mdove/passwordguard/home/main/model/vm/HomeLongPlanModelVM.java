package com.mdove.passwordguard.home.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.longplan.model.LongPlanModel;
import com.mdove.passwordguard.home.main.model.HomeLongPlanModel;
import com.mdove.passwordguard.utils.DateUtils;

public class HomeLongPlanModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mLongPlan = new ObservableField<>();
    public ObservableField<String> mStartTime = new ObservableField<>();
    public ObservableField<String> mEndTime = new ObservableField<>();
    public ObservableField<String> mUrgent = new ObservableField<>();
    public ObservableField<String> mImportant = new ObservableField<>();
    public ObservableField<String> mTips = new ObservableField<>();
    public ObservableField<Long> mId = new ObservableField<>();

    public HomeLongPlanModelVM(HomeLongPlanModel model) {
        mTime.set("记录于：[" + DateUtils.getDateChinese(model.mTime) + "]");
        mLongPlan.set(model.mLongPlan);
        mStartTime.set(DateUtils.getDateChineseNoH(model.mStartTime));
        mEndTime.set(DateUtils.getDateChineseNoH(model.mEndTime));
        mUrgent.set(model.mUrgent + "");
        mImportant.set(model.mImportant + "");
        mTips.set("标签：[" + model.mTips + "]");
        mId.set(model.id);
    }
}
