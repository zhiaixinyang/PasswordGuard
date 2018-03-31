package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/9.
 */

public class MainPasswordModel extends BaseMainModel {
    public Long mPasswordId;
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public String mTvGroup;
    public Long mTimeStamp;
    public boolean mIsNew = false;
    public boolean mFavorite = false;
    public Password password;

    public MainPasswordModel(Password password) {
        mPasswordId = password.id;
        mTitle = password.mTitle;
        mPassword = password.mPassword;
        mUserName = password.mUserName;
        mTimeStamp = password.mTimeStamp;
        mTvGroup = password.mTvGroup;
        mType = 1;
        mIsNew = password.isNew != 0;
        mFavorite = password.isFavorite != 0;
        this.password = password;
    }

    public void setPassword(Password password) {
        mPasswordId = password.id;
        mTitle = password.mTitle;
        mPassword = password.mPassword;
        mUserName = password.mUserName;
        mTimeStamp = password.mTimeStamp;
        mTvGroup = password.mTvGroup;
        mType = 1;
        mFavorite = password.isFavorite != 0;
        mIsNew = password.isNew != 0;
        this.password = password;
    }
}
