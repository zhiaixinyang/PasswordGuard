package com.mdove.passwordguard.deletelist.utils;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.Password;

import java.util.Date;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteDailySelfHelper {
    public static DeletedDailySelf getDeletedDailySelf(DailySelf dailySelf) {
        DeletedDailySelf deletedDailySelf = new DeletedDailySelf();
        deletedDailySelf.mContent = dailySelf.mContent;
        deletedDailySelf.mTimeStamp = dailySelf.mTimeStamp;
        deletedDailySelf.mTvGroup = dailySelf.mTvGroup;
        deletedDailySelf.mDeletedTimeStamp = new Date().getTime();
        return deletedDailySelf;
    }

    public static DailySelf getDailySelf(DeletedDailySelf deletedDailySelf) {
        DailySelf dailySelf = new DailySelf();
        dailySelf.mContent = deletedDailySelf.mContent;
        dailySelf.mTimeStamp = deletedDailySelf.mTimeStamp;
        dailySelf.mTvGroup = deletedDailySelf.mTvGroup;
        return dailySelf;
    }
}
