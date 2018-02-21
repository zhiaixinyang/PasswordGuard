package com.mdove.passwordguard.addoralter.model.event;

import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/21.
 */

public class EditPasswordActivityEvent {
    public AlterPasswordModel alterPasswordModel;
    public int mEditItemPosition;

    public EditPasswordActivityEvent(Password password, Password oldEditPassword, int editPostion) {
        alterPasswordModel = new AlterPasswordModel(oldEditPassword, password);
        mEditItemPosition = editPostion;
    }

}
