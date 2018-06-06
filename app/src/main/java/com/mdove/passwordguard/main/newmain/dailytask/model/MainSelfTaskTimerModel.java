package com.mdove.passwordguard.main.newmain.dailytask.model;

import com.mdove.passwordguard.greendao.entity.SelfTask;

/**
 * Created by MDove on 2018/6/6.
 */

public class MainSelfTaskTimerModel {
    public String mTask;
    public long mTime;
    public boolean mIsSuc;
    public long mId;

    public MainSelfTaskTimerModel(SelfTask selfTask) {
        mTask = selfTask.mTask;
        mId = selfTask.id;
        mTime = selfTask.mTime;
        mIsSuc = selfTask.mIsSuc == 1;
    }
}
