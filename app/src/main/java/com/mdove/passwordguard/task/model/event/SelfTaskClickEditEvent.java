package com.mdove.passwordguard.task.model.event;

import com.mdove.passwordguard.task.model.SelfTaskModel;

/**
 * Created by MDove on 2018/3/28.
 */

public class SelfTaskClickEditEvent {
    public long mId;
    public SelfTaskModel mSelfTaskModel;

    public SelfTaskClickEditEvent(long id, SelfTaskModel selfTaskModel) {
        mId = id;
        mSelfTaskModel = selfTaskModel;
    }
}
