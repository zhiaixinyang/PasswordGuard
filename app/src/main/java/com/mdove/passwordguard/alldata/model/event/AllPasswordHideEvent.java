package com.mdove.passwordguard.alldata.model.event;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllPasswordHideEvent {
    public long mId;
    public boolean mIsHide;

    public AllPasswordHideEvent(long id, boolean isHide) {
        mIsHide = isHide;
        mId = id;
    }
}
