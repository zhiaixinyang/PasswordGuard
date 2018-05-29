package com.mdove.passwordguard.main.newmain.dailytask.util;

/**
 * Created by MDove on 2018/5/29.
 */

public class LabelTempModel {
    public String mLabel;
    //被选中的Label的id
    public long mSelectId;

    public LabelTempModel(String label, long id) {
        mLabel = label;
        mSelectId = id;
    }
}
