package com.mdove.passwordguard.search.model;

import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/2/15.
 */

public class SearchRlvModel extends BaseMainModel {
    public Long mPasswordId;
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public Long mTimeStamp;
    public boolean mIsNew = false;
    public Password password;

    public SearchRlvModel(Password password) {
        mPasswordId = password.id;
        mTitle = password.mTitle;
        mPassword = password.mPassword;
        mUserName = password.mUserName;
        mTimeStamp = password.mTimeStamp;
        mType = 1;
        mIsNew = password.isNew != 0;
        this.password = password;
    }
}
