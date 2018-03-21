package com.mdove.passwordguard.addoralter.model;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfGroupRlvModel {
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
    public boolean mIsCheck = false;

    public AddDailySelfGroupRlvModel(GroupInfo groupInfo) {
        mBgColor = groupInfo.mBgColor;
        mTimeStamp = groupInfo.mTimeStamp;
        mTvGroup = groupInfo.mTvGroup;
        mIsCheck = false;
    }

    public AddDailySelfGroupRlvModel(String tvGroup, String bgColor, Long timeStamp) {
        mTvGroup = tvGroup;
        mBgColor = bgColor;
        mTimeStamp = timeStamp;
        mIsCheck = true;
    }
}
