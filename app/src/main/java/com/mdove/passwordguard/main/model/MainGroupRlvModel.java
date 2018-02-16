package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.GroupInfo;

/**
 * Created by MDove on 2018/2/16.
 */

public class MainGroupRlvModel {
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;

    public MainGroupRlvModel(GroupInfo groupInfo) {
        mBgColor = groupInfo.mBgColor;
        mTimeStamp = groupInfo.mTimeStamp;
        mTvGroup = groupInfo.mTvGroup;
    }

    public MainGroupRlvModel(String tvGroup, String bgCOlor, Long timeStamp) {
        mTvGroup = tvGroup;
        mBgColor = bgCOlor;
        mTimeStamp = timeStamp;
    }
}
