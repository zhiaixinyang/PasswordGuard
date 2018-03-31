package com.mdove.passwordguard.collect.model;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/3/31.
 */

public class CollectPasswordModel extends BaseMainModel {
    public Long mPasswordId;
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public String mTvGroup;
    public Long mTimeStamp;
    public boolean mIsNew = false;
    public boolean mFavorite = false;
    public Password password;

    public CollectPasswordModel(Password password) {
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
}
