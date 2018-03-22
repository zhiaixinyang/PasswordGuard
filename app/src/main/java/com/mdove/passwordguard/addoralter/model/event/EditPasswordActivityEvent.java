package com.mdove.passwordguard.addoralter.model.event;

import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/2/21.
 */

public class EditPasswordActivityEvent {
    public AlterPasswordModel alterPasswordModel;
    public int mEditItemPosition;

    public EditPasswordActivityEvent(Password tempPassword, Password needEditPassword, int editPostion) {
        alterPasswordModel = new AlterPasswordModel(needEditPassword, tempPassword);
        mEditItemPosition = editPostion;
    }

}
