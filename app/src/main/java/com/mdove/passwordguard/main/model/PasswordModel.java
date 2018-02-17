package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/9.
 */

public class PasswordModel extends BaseMainModel {
    public Long mPasswordId;
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public String mTvGroup;
    public Long mTimeStamp;
    public boolean mIsNew = false;
    public Password password;

    public PasswordModel(Password password) {
        mPasswordId = password.id;
        mTitle = password.mTitle;
        mPassword = password.mPassword;
        mUserName = password.mUserName;
        mTimeStamp = password.mTimeStamp;
        mTvGroup = password.mTvGroup;
        mType = 1;
        mIsNew = password.isNew != 0;
        this.password = password;
    }
}
