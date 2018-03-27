package com.mdove.passwordguard.task.model;

import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskModel {
    public String mTask;
    public long mTime;
    public boolean mIsSuc;
    public boolean mIsSee;
    public int mPriority;
    public SucSelfTask mSucSelfTask;
    public SelfTask mSelfTask;
    public long mId;

    public SucSelfTaskModel(SucSelfTask sucSelfTask) {
        mTask = sucSelfTask.mTask;
        mId = sucSelfTask.id;
        mTime = sucSelfTask.mTime;
        mIsSuc = sucSelfTask.mIsSuc == 1;
        mIsSee = sucSelfTask.mIsSee == 1;
        mSucSelfTask = sucSelfTask;
        mPriority = sucSelfTask.mPriority;
    }

    public SucSelfTaskModel(SelfTask selfTask) {
        mTask = selfTask.mTask;
        mId = selfTask.id;
        mTime = selfTask.mTime;
        mIsSuc = selfTask.mIsSuc == 1;
        mIsSee = selfTask.mIsSee == 1;
        mSelfTask = selfTask;
        mPriority = selfTask.mPriority;
    }
}
