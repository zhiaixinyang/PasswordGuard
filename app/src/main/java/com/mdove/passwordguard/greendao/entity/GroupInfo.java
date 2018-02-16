package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/2/16.
 */
@Entity
public class GroupInfo {
    @Id(autoincrement = true)
    public Long id;
    public String mTvGroup;
    public String mBgColor;
    public Long mTimeStamp;
    @Generated(hash = 2127567190)
    public GroupInfo(Long id, String mTvGroup, String mBgColor, Long mTimeStamp) {
        this.id = id;
        this.mTvGroup = mTvGroup;
        this.mBgColor = mBgColor;
        this.mTimeStamp = mTimeStamp;
    }
    @Generated(hash = 1250265142)
    public GroupInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMTvGroup() {
        return this.mTvGroup;
    }
    public void setMTvGroup(String mTvGroup) {
        this.mTvGroup = mTvGroup;
    }
    public String getMBgColor() {
        return this.mBgColor;
    }
    public void setMBgColor(String mBgColor) {
        this.mBgColor = mBgColor;
    }
    public Long getMTimeStamp() {
        return this.mTimeStamp;
    }
    public void setMTimeStamp(Long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }
}
