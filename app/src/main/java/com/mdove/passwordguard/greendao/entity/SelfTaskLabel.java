package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/5/27.
 */
@Entity
public class SelfTaskLabel implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mTvLabel;
    public String mBgColor;
    public Long mTimeStamp;
    static final long serialVersionUID = 1L;
    @Generated(hash = 2027180992)
    public SelfTaskLabel(Long id, String mTvLabel, String mBgColor,
            Long mTimeStamp) {
        this.id = id;
        this.mTvLabel = mTvLabel;
        this.mBgColor = mBgColor;
        this.mTimeStamp = mTimeStamp;
    }
    @Generated(hash = 1201483618)
    public SelfTaskLabel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMTvLabel() {
        return this.mTvLabel;
    }
    public void setMTvLabel(String mTvLabel) {
        this.mTvLabel = mTvLabel;
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
