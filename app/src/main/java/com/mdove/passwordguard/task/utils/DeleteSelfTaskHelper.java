package com.mdove.passwordguard.task.utils;

import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTask;

import java.util.Date;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskHelper {
    public static DeleteSelfTask getDeletedSelfTask(SelfTask selfTask) {
        DeleteSelfTask deleteSelfTask = new DeleteSelfTask();
        deleteSelfTask.mCreateTime = selfTask.mTime;
        deleteSelfTask.mDeleteTime = new Date().getTime();
        deleteSelfTask.mIsSee = selfTask.mIsSee;
        deleteSelfTask.mIsSuc = selfTask.mIsSuc;
        deleteSelfTask.mPriority = selfTask.mPriority;
        deleteSelfTask.mTask = selfTask.mTask;
        return deleteSelfTask;
    }

    public static SelfTask getSelfTask(DeleteSelfTask deleteSelfTask) {
        SelfTask selfTask = new SelfTask();
        selfTask.mPriority = deleteSelfTask.mPriority;
        selfTask.mIsSuc = deleteSelfTask.mIsSuc;
        selfTask.mIsSee = deleteSelfTask.mIsSee;
        selfTask.mTask = deleteSelfTask.mTask;
        selfTask.mTime = deleteSelfTask.mCreateTime;
        return selfTask;
    }
}
