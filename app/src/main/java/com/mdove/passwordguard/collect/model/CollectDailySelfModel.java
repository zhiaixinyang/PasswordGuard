package com.mdove.passwordguard.collect.model;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectDailySelfModel extends BaseMainModel {
    public String mContent;
    public Long mTime;
    public String mTvGroup;
    public boolean mIsFavorite;
    public DailySelf mDailySelf;

    public CollectDailySelfModel(DailySelf dailySelf) {
        mContent = dailySelf.mContent;
        mTime = dailySelf.mTimeStamp;
        mTvGroup = dailySelf.mTvGroup;
        mType = 1;
        mDailySelf = dailySelf;
        mIsFavorite = dailySelf.mIsFavorite == 1;
    }
}
