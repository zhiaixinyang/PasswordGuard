package com.mdove.passwordguard.deletelist.model;

import android.text.TextUtils;

import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteDailySelfModel extends BaseMainModel {
    public String mContent;
    public String mTvGroup;
    public Long mTimeStamp;
    public Long mDeleteTime;
    public DeletedDailySelf mDeletedDailySelf;

    public DeleteDailySelfModel(DeletedDailySelf deletedDailySelf) {
        mContent = deletedDailySelf.mContent;
        mTimeStamp = deletedDailySelf.mTimeStamp;
        mDeleteTime = deletedDailySelf.mDeletedTimeStamp;
        mType = 1;
        mDeletedDailySelf = deletedDailySelf;
        mTvGroup = TextUtils.isEmpty(deletedDailySelf.mTvGroup) ? AppConstant.DEFAULT_DAILY_SELF_TV_GROUP : deletedDailySelf.mTvGroup;
    }
}
