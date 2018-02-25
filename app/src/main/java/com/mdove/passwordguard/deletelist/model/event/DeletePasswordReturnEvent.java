package com.mdove.passwordguard.deletelist.model.event;

import com.mdove.passwordguard.greendao.entity.DeletedPassword;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeletePasswordReturnEvent {
    public DeletedPassword mDeletedPassword;

    public DeletePasswordReturnEvent(DeletedPassword deletedPassword) {
        mDeletedPassword = deletedPassword;
    }
}
