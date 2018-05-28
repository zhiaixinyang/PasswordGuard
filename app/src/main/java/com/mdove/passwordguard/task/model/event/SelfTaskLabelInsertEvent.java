package com.mdove.passwordguard.task.model.event;

import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;

/**
 * Created by MDove on 2018/5/28.
 */

public class SelfTaskLabelInsertEvent {
    public DailyTaskLabelModel mDailyTaskLabelModel;

    public SelfTaskLabelInsertEvent(DailyTaskLabelModel model) {
        mDailyTaskLabelModel = model;
    }
}
