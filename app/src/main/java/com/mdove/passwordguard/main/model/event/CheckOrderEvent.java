package com.mdove.passwordguard.main.model.event;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/19.
 */

public class CheckOrderEvent {
    public boolean mIsCheck;
    public String mDefaultTvGroup;
    public GroupInfo mGroupInfo;

    public CheckOrderEvent(boolean isCheck, GroupInfo groupInfo, String defaultTvGroup) {
        mIsCheck = isCheck;
        mGroupInfo = groupInfo;
        mDefaultTvGroup = defaultTvGroup;
    }
}
