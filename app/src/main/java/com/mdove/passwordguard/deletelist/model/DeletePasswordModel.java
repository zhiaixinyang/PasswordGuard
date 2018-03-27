package com.mdove.passwordguard.deletelist.model;

import android.text.TextUtils;

import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeletePasswordModel extends BaseMainModel {
    public Long mPasswordId;
    public String mTitle;
    public String mPassword;
    public String mUserName;
    public String mTvGroup;
    public Long mTimeStamp;
    public boolean mIsNew = false;
    public DeletedPassword mDeletedPassword;

    public DeletePasswordModel(DeletedPassword deletedPassword) {
        mPasswordId = deletedPassword.id;
        mTitle = deletedPassword.mTitle;
        mPassword = deletedPassword.mPassword;
        mUserName = deletedPassword.mUserName;
        mTimeStamp = deletedPassword.mTimeStamp;
        mType = 1;
        mIsNew = deletedPassword.isNew != 0;
        mDeletedPassword = deletedPassword;
        mTvGroup = TextUtils.isEmpty(deletedPassword.mTvGroup) ? AppConstant.DEFAULT_CHECK_GROUP_TITLE : deletedPassword.mTvGroup;
    }
}
