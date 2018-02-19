package com.mdove.passwordguard.main.model.event;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/19.
 */

public class CheckOrderEvent {
    public boolean mIsCheck;
    public GroupInfo mGroupInfo;

    public CheckOrderEvent(boolean isCheck, GroupInfo groupInfo) {
        mIsCheck = isCheck;
        mGroupInfo = groupInfo;
    }
}
