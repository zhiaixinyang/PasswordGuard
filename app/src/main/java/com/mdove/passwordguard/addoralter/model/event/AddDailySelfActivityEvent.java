package com.mdove.passwordguard.addoralter.model.event;

import com.mdove.passwordguard.greendao.entity.DailySelf;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfActivityEvent {
    public DailySelf mDailySelf;

    public AddDailySelfActivityEvent(DailySelf dailySelf) {
        mDailySelf = dailySelf;
    }
}
