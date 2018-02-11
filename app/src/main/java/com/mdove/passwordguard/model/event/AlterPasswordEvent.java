package com.mdove.passwordguard.model.event;

import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/11.
 */

public class AlterPasswordEvent {
    public Password mPassword;
    public int mItemPosition;
}
