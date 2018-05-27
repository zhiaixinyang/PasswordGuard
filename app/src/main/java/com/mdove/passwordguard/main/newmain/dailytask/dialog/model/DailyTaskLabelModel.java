package com.mdove.passwordguard.main.newmain.dailytask.dialog.model;

import com.mdove.passwordguard.greendao.entity.DailyTaskLabel;

/**
 * Created by MDove on 2018/5/27.
 */

public class DailyTaskLabelModel {
    public DailyTaskLabel mLabel;
    public String mLabelName;
    public boolean isSelect = false;

    public DailyTaskLabelModel(DailyTaskLabel label) {
        mLabel = label;
        mLabelName = label.mTvLabel;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
