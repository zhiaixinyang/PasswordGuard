package com.mdove.passwordguard.alldata.model.event;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfHideEvent {
    public long mId;
    public boolean mIsHide;

    public AllDailySelfHideEvent(long id, boolean isHide) {
        mId = id;
        mIsHide = isHide;
    }
}
