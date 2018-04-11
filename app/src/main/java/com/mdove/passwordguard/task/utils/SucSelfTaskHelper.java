package com.mdove.passwordguard.task.utils;

import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskHelper {
    public static SucSelfTask getSucSelfTask(SelfTask selfTask) {
        SucSelfTask sucSelfTask = new SucSelfTask();
        sucSelfTask.mTime = selfTask.mTime;
        sucSelfTask.mIsSee = selfTask.mIsSee;
        sucSelfTask.mIsSuc = selfTask.mIsSuc;
        sucSelfTask.mPriority = selfTask.mPriority;
        sucSelfTask.mTask = selfTask.mTask;
        sucSelfTask.mBelongId = selfTask.id;
        return sucSelfTask;
    }

    public static SelfTask getSelfTask(SucSelfTask sucSelfTask) {
        SelfTask selfTask = new SelfTask();
        selfTask.mPriority = sucSelfTask.mPriority;
        selfTask.mIsSuc = sucSelfTask.mIsSuc;
        selfTask.mIsSee = sucSelfTask.mIsSee;
        selfTask.mTask = sucSelfTask.mTask;
        selfTask.mTime = sucSelfTask.mTime;
        sucSelfTask.id = sucSelfTask.mBelongId;
        return selfTask;
    }
}
