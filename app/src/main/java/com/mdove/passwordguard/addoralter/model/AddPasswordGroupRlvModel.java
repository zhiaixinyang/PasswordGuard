package com.mdove.passwordguard.addoralter.model;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordGroupRlvModel {
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
    public boolean mIsCheck = false;

    public AddPasswordGroupRlvModel(GroupInfo groupInfo) {
        mBgColor = groupInfo.mBgColor;
        mTimeStamp = groupInfo.mTimeStamp;
        mTvGroup = groupInfo.mTvGroup;
        mIsCheck = false;
    }

    public AddPasswordGroupRlvModel(String tvGroup, String bgColor, Long timeStamp) {
        mTvGroup = tvGroup;
        mBgColor = bgColor;
        mTimeStamp = timeStamp;
        mIsCheck = true;
    }
}
