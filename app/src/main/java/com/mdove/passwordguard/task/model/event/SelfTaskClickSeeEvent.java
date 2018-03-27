package com.mdove.passwordguard.task.model.event;

import com.mdove.passwordguard.task.model.SelfTaskModel;

/**
 * Created by MDove on 2018/3/26.
 */

public class SelfTaskClickSeeEvent {
    public SelfTaskModel mSelfTaskModel;

    public SelfTaskClickSeeEvent(SelfTaskModel selfTaskModel) {
        mSelfTaskModel = selfTaskModel;
    }
}
