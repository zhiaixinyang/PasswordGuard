package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Id;

/**
 * Created by MDove on 2018/2/16.
 */

public class GroupInfo {
    @Id(autoincrement = true)
    public Long id;
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
}
