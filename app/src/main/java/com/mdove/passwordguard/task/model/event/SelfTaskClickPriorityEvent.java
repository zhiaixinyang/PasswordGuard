package com.mdove.passwordguard.task.model.event;

import com.mdove.passwordguard.task.model.SelfTaskModel;

/**
 * Created by MDove on 2018/3/27.
 */

public class SelfTaskClickPriorityEvent {
    public long mId;
    public SelfTaskModel mSelfTaskModel;

    public SelfTaskClickPriorityEvent(long id, SelfTaskModel selfTaskModel) {
        mId = id;
        mSelfTaskModel = selfTaskModel;
    }
}
