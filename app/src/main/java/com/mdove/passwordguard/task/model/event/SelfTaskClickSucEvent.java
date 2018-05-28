package com.mdove.passwordguard.task.model.event;

import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;

/**
 * Created by MDove on 2018/3/26.
 */

public class SelfTaskClickSucEvent {
    public long mId;
    public SelfTaskModel mSelfTaskModel;

    public SelfTaskClickSucEvent(long id, SelfTaskModel selfTaskModel) {
        mId = id;
        mSelfTaskModel = selfTaskModel;
    }
}
