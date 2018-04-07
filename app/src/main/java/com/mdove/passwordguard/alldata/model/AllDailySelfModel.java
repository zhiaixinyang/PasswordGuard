package com.mdove.passwordguard.alldata.model;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.main.model.BaseMainModel;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfModel extends BaseMainModel {
    public String mContent;
    public Long mTime;
    public String mTvGroup;
    public boolean mIsFavorite;
    public boolean mIsHide;
    public DailySelf mDailySelf;
    public long mId;

    public AllDailySelfModel(DailySelf dailySelf) {
        mContent = dailySelf.mContent;
        mTime = dailySelf.mTimeStamp;
        mTvGroup = dailySelf.mTvGroup;
        mType = 1;
        mDailySelf = dailySelf;
        mIsFavorite = dailySelf.mIsFavorite == 1;
        if (dailySelf.mIsHide == null) {
            mIsHide = false;
        } else {
            mIsHide = dailySelf.mIsHide == 1;
        }
        mId = dailySelf.id;
    }

    public void setDailySelf(DailySelf dailySelf) {
        mContent = dailySelf.mContent;
        mId = dailySelf.id;
        mTime = dailySelf.mTimeStamp;
        mTvGroup = dailySelf.mTvGroup;
        mType = 1;
        if (dailySelf.mIsHide == null) {
            mIsHide = false;
        } else {
            mIsHide = dailySelf.mIsHide == 1;
        }
        mDailySelf = dailySelf;
        mIsFavorite = dailySelf.mIsFavorite == 1;
    }
}
