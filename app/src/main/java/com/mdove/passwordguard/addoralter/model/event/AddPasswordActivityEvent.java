package com.mdove.passwordguard.addoralter.model.event;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordActivityEvent {
    public Password mPassword;

    public AddPasswordActivityEvent(Password password) {
        mPassword = password;
    }
}
