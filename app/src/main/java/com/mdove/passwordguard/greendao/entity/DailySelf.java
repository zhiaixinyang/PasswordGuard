package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/22.
 */

@Entity
public class DailySelf implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTimeStamp;
    public String mTvGroup;
    public Long mLabelId;
    public String mLabelName;
    public int mIsFavorite;
    public Integer mIsHide = 1;
    static final long serialVersionUID = 12L;
    @Generated(hash = 692993157)
    public DailySelf(Long id, String mContent, Long mTimeStamp, String mTvGroup,
            Long mLabelId, String mLabelName, int mIsFavorite, Integer mIsHide) {
        this.id = id;
        this.mContent = mContent;
        this.mTimeStamp = mTimeStamp;
        this.mTvGroup = mTvGroup;
        this.mLabelId = mLabelId;
        this.mLabelName = mLabelName;
        this.mIsFavorite = mIsFavorite;
        this.mIsHide = mIsHide;
    }
    @Generated(hash = 2097784888)
    public DailySelf() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMContent() {
        return this.mContent;
    }
    public void setMContent(String mContent) {
        this.mContent = mContent;
    }
    public Long getMTimeStamp() {
        return this.mTimeStamp;
    }
    public void setMTimeStamp(Long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }
    public String getMTvGroup() {
        return this.mTvGroup;
    }
    public void setMTvGroup(String mTvGroup) {
        this.mTvGroup = mTvGroup;
    }
    public Long getMLabelId() {
        return this.mLabelId;
    }
    public void setMLabelId(Long mLabelId) {
        this.mLabelId = mLabelId;
    }
    public String getMLabelName() {
        return this.mLabelName;
    }
    public void setMLabelName(String mLabelName) {
        this.mLabelName = mLabelName;
    }
    public int getMIsFavorite() {
        return this.mIsFavorite;
    }
    public void setMIsFavorite(int mIsFavorite) {
        this.mIsFavorite = mIsFavorite;
    }
    public Integer getMIsHide() {
        return this.mIsHide;
    }
    public void setMIsHide(Integer mIsHide) {
        this.mIsHide = mIsHide;
    }


}
