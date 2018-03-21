package com.mdove.passwordguard.addoralter.model.event;

import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;

/**
 * Created by MDove on 2018/3/21.
 */

public class EditDailySelfActivityEvent {
    public AlterDailySelfModel mAlterDailySelfModel;
    public int mEditItemPosition;

    public EditDailySelfActivityEvent(DailySelf oldEditDailySelf, int editPosition) {
        mAlterDailySelfModel = new AlterDailySelfModel(oldEditDailySelf);
        mEditItemPosition = editPosition;
    }
}
