package com.mdove.passwordguard.addoralter.model;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 18/2/12.
 */

public class AlterPasswordModel {
    public Password mOldPassword;
    public Password mNewPassword;

    public AlterPasswordModel(Password oldPassword, Password newPassword) {
        mOldPassword = oldPassword;
        mNewPassword = newPassword;
    }
}
