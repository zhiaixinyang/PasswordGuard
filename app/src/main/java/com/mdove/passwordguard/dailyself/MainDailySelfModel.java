package com.mdove.passwordguard.dailyself;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/2/22.
 */

public class MainDailySelfModel extends BaseMainModel {
    public String mContent;
    public Long mTime;
    public String mTvGroup;
    public boolean mIsFavorite;
    public DailySelf mDailySelf;

    public MainDailySelfModel(DailySelf dailySelf) {
        mContent = dailySelf.mContent;
        mTime = dailySelf.mTimeStamp;
        mTvGroup = dailySelf.mTvGroup;
        mType = 1;
        mDailySelf = dailySelf;
        mIsFavorite = dailySelf.mIsFavorite == 1;
    }

    public void setDailySelf(DailySelf dailySelf){
        mContent = dailySelf.mContent;
        mTime = dailySelf.mTimeStamp;
        mTvGroup = dailySelf.mTvGroup;
        mType = 1;
        mDailySelf = dailySelf;
        mIsFavorite = dailySelf.mIsFavorite == 1;
    }
}
