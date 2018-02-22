package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/2/22.
 */

@Entity
public class DailySelf {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTimeStamp;
    public String mTvGroup;
    @Generated(hash = 111221049)
    public DailySelf(Long id, String mContent, Long mTimeStamp, String mTvGroup) {
        this.id = id;
        this.mContent = mContent;
        this.mTimeStamp = mTimeStamp;
        this.mTvGroup = mTvGroup;
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
}
