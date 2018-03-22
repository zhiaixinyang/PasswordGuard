package com.mdove.passwordguard.addoralter.model;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 18/2/12.
 */

public class AlterPasswordModel {
    public Password mNeedEditPassword;
    public Password mTempPassword;

    public AlterPasswordModel(Password needEditPassword, Password tempPassword) {
        mNeedEditPassword = needEditPassword;
        mTempPassword = tempPassword;
    }
}
