package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/25.
 */

@Entity
public class DeletedDailySelf implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTimeStamp;
    public Long mDeletedTimeStamp;
    public String mTvGroup;
    static final long serialVersionUID = 1L;

    @Generated(hash = 1033302363)
    public DeletedDailySelf(Long id, String mContent, Long mTimeStamp,
            Long mDeletedTimeStamp, String mTvGroup) {
        this.id = id;
        this.mContent = mContent;
        this.mTimeStamp = mTimeStamp;
        this.mDeletedTimeStamp = mDeletedTimeStamp;
        this.mTvGroup = mTvGroup;
    }
    @Generated(hash = 463220722)
    public DeletedDailySelf() {
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
