package com.mdove.passwordguard.home.ettodayplan.model.event;

import com.mdove.passwordguard.home.ettodayplan.model.SetTimeModel;

/**
 * Created by MDove on 2018/6/27.
 */

public class SetTimeEvent {
    public SetTimeModel mModel;

    public SetTimeEvent(SetTimeModel model) {
        mModel = model;
    }
}
