package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/9.
 */

public class PasswordModel extends BaseMainModel {
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public Long mTimeStamp;

    public PasswordModel(Password password) {
        mTitle = password.mTitle;
        mPassword = password.mPassword;
        mUserName = password.mUserName;
        mTimeStamp = password.mTimeStamp;
        mType = 1;
    }
}
