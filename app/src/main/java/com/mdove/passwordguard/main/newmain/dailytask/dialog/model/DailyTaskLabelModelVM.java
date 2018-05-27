package com.mdove.passwordguard.main.newmain.dailytask.dialog.model;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/5/27.
 */

public class DailyTaskLabelModelVM {
    public ObservableField<String> mLabelName = new ObservableField<>();

    public DailyTaskLabelModelVM(DailyTaskLabelModel dailyTaskModel) {
        mLabelName.set(dailyTaskModel.mLabelName);
    }
}
