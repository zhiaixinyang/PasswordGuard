package com.mdove.passwordguard.greendao.utils;

import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.Password;

import java.util.Date;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeletedPasswordHelper {
    public static DeletedPassword getDeletedPassword(Password password) {
        DeletedPassword deletedPassword = new DeletedPassword();
        deletedPassword.isNew = 0;
        deletedPassword.mTitle = password.mTitle;
        deletedPassword.mUserName = password.mUserName;
        deletedPassword.mPassword = password.mPassword;
        deletedPassword.mTimeStamp = password.mTimeStamp;
        deletedPassword.mTvGroup = password.mTvGroup;
        deletedPassword.mDeletedTimeStamp = new Date().getTime();
        return deletedPassword;
    }

    public static Password getPassword(DeletedPassword deletedPassword) {
        Password password = new Password();
        password.mTvGroup = deletedPassword.mTvGroup;
        password.mTitle = deletedPassword.mTitle;
        password.mPassword = deletedPassword.mPassword;
        password.mUserName = deletedPassword.mUserName;
        password.isNew = 0;
        password.mTimeStamp = deletedPassword.mTimeStamp;
        return password;
    }
}
