package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/10.
 */

@Entity
public class Password implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mUserName;
    public String mPassword;
    public String mTitle;
    public Long mTimeStamp;
    public int isNew;
    public int isFavorite;//0表示没收藏
    public String mTvGroup;
    static final long serialVersionUID = 42L;

    @Generated(hash = 565943725)
    public Password() {
    }

    @Generated(hash = 1217362040)
    public Password(Long id, String mUserName, String mPassword, String mTitle,
            Long mTimeStamp, int isNew, int isFavorite, String mTvGroup) {
        this.id = id;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mTitle = mTitle;
        this.mTimeStamp = mTimeStamp;
        this.isNew = isNew;
        this.isFavorite = isFavorite;
        this.mTvGroup = mTvGroup;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMUserName() {
        return this.mUserName;
    }

    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getMPassword() {
        return this.mPassword;
    }

    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getMTitle() {
        return this.mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Long getMTimeStamp() {
        return this.mTimeStamp;
    }

    public void setMTimeStamp(Long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public int getIsNew() {
        return this.isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public String getMTvGroup() {
        return this.mTvGroup;
    }

    public void setMTvGroup(String mTvGroup) {
        this.mTvGroup = mTvGroup;
    }

    public int getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }
}
