package com.mdove.passwordguard.task.model;

import com.mdove.passwordguard.greendao.entity.SelfTask;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskModel {
    public String mTask;
    public long mTime;
    public boolean mIsSuc;
    public boolean mIsSee;
    public SelfTask mSelfTask;
    public long mId;

    public SelfTaskModel(SelfTask selfTask) {
        mTask = selfTask.mTask;
        mId = selfTask.id;
        mTime = selfTask.mTime;
        mIsSuc = selfTask.mIsSuc == 1;
        mIsSee = selfTask.mIsSee == 1;
        mSelfTask = selfTask;
    }
}
