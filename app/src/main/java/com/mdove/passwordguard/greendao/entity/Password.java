package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/2/10.
 */

@Entity
public class Password {
    @Id
    public Long id;
    public String mUserName;
    public String mPassword;
    public String mTitle;
    public Long mTimeStamp;
    public int isNew;
    public int getIsNew() {
        return this.isNew;
    }
    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }
    public Long getMTimeStamp() {
        return this.mTimeStamp;
    }
    public void setMTimeStamp(Long mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMPassword() {
        return this.mPassword;
    }
    public void setMPassword(String mPassword) {
        this.mPassword = mPassword;
    }
    public String getMUserName() {
        return this.mUserName;
    }
    public void setMUserName(String mUserName) {
        this.mUserName = mUserName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 441318947)
    public Password(Long id, String mUserName, String mPassword, String mTitle,
            Long mTimeStamp, int isNew) {
        this.id = id;
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mTitle = mTitle;
        this.mTimeStamp = mTimeStamp;
        this.isNew = isNew;
    }
    @Generated(hash = 565943725)
    public Password() {
    }
    
}
