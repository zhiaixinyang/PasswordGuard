package com.mdove.passwordguard.main.newmain.dailytask.dialog.model;

import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;

/**
 * Created by MDove on 2018/5/27.
 */

public class DailyTaskLabelModel {
    public SelfTaskLabel mLabel;
    public String mLabelName;
    public boolean isSelect = false;

    public DailyTaskLabelModel(SelfTaskLabel label) {
        mLabel = label;
        mLabelName = label.mTvLabel;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
