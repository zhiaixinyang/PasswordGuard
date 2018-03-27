package com.mdove.passwordguard.task.model;

import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskModel {
    public String mTask;
    public long mCreateTime;
    public long mDeleteTime;
    public boolean mIsSuc;
    public boolean mIsSee;
    public int mPriority;
    public DeleteSelfTask mDeleteSelfTask;
    public long mId;

    public DeleteSelfTaskModel(DeleteSelfTask deleteSelfTask) {
        mTask = deleteSelfTask.mTask;
        mId = deleteSelfTask.id;
        mCreateTime = deleteSelfTask.mCreateTime;
        mDeleteTime = deleteSelfTask.mDeleteTime;
        mIsSuc = deleteSelfTask.mIsSuc == 1;
        mIsSee = deleteSelfTask.mIsSee == 1;
        mDeleteSelfTask = deleteSelfTask;
        mPriority = deleteSelfTask.mPriority;
    }
}
