package com.mdove.passwordguard.addoralter.model;

import com.mdove.passwordguard.greendao.entity.DailySelf;

/**
 * Created by MDove on 18/3/21.
 */

public class AlterDailySelfModel {
    public DailySelf mOldDailySelf;
    public DailySelf mNewDailySelf;

    public AlterDailySelfModel(DailySelf oldDailySelf, DailySelf newDailySelf) {
        mOldDailySelf = oldDailySelf;
        mNewDailySelf = newDailySelf;
    }
}
