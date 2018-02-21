package com.mdove.passwordguard.group.model;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/21.
 */

public class GroupSettingModel {
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
    public boolean mIsCheck = false;
    public GroupInfo mGroupInfo = null;

    public GroupSettingModel(GroupInfo groupInfo) {
        mBgColor = groupInfo.mBgColor;
        mTimeStamp = groupInfo.mTimeStamp;
        mTvGroup = groupInfo.mTvGroup;
        mIsCheck = false;
        mGroupInfo = groupInfo;
    }

    public GroupSettingModel(String tvGroup, String bgColor, Long timeStamp) {
        mTvGroup = tvGroup;
        mBgColor = bgColor;
        mTimeStamp = timeStamp;
        mIsCheck = true;
        mGroupInfo = null;
    }
}
