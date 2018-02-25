package com.mdove.passwordguard.main.model;

import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/16.
 */

public class MainGroupRlvModel {
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
    public boolean mIsCheck = false;
    public GroupInfo mGroupInfo = null;

    public MainGroupRlvModel(GroupInfo groupInfo) {
        mBgColor = groupInfo.mBgColor;
        mTimeStamp = groupInfo.mTimeStamp;
        mTvGroup = groupInfo.mTvGroup;
        mIsCheck = false;
        mGroupInfo = groupInfo;
    }

    public MainGroupRlvModel(String tvGroup, String bgColor, Long timeStamp) {
        mTvGroup = tvGroup;
        mBgColor = bgColor;
        mTimeStamp = timeStamp;
        mIsCheck = TextUtils.equals(tvGroup, AppConstant.DEFAULT_CHECK_GROUP_TITLE);
        mGroupInfo = null;
    }
}
