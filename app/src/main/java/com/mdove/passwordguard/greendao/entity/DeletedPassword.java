package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/14.
 */

@Entity
public class DeletedPassword implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mUserName;
    public String mPassword;
    public String mTitle;
    public Long mTimeStamp;
    public int isNew;
    public Long mDeletedTimeStamp;
    public String mTvGroup;
    static final long serialVersionUID = 1L;

    @Generated(hash = 819626397)
    public DeletedPassword(Long id, String mUserName, String mPassword,
            String mTitle, Long mTimeStamp, int isNew, Long mDeletedTimeStamp,
            String mTvGroup) {
        this.id = id;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mTitle = mTitle;
        this.mTimeStamp = mTimeStamp;
        this.isNew = isNew;
        this.mDeletedTimeStamp = mDeletedTimeStamp;
        this.mTvGroup = mTvGroup;
    }
    @Generated(hash = 1518974356)
    public DeletedPassword() {
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
    public Long getMDeletedTimeStamp() {
        return this.mDeletedTimeStamp;
    }
    public void setMDeletedTimeStamp(Long mDeletedTimeStamp) {
        this.mDeletedTimeStamp = mDeletedTimeStamp;
    }
    public String getMTvGroup() {
        return this.mTvGroup;
    }
    public void setMTvGroup(String mTvGroup) {
        this.mTvGroup = mTvGroup;
    }

}
