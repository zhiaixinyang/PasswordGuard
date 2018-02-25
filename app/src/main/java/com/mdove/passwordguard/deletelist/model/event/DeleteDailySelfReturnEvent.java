package com.mdove.passwordguard.deletelist.model.event;

import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteDailySelfReturnEvent {
    public DeletedDailySelf mDeletedDailySelf;

    public DeleteDailySelfReturnEvent(DeletedDailySelf deletedDailySelf) {
        mDeletedDailySelf = deletedDailySelf;
    }
}
