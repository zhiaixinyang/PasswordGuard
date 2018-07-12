package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/7/12.
 */
@Entity
public class InnerAscribe {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTime;
    public Long mBelongTitleId;

    @Generated(hash = 2139025384)
    public InnerAscribe(Long id, String mContent, Long mTime, Long mBelongTitleId) {
        this.id = id;
        this.mContent = mContent;
        this.mTime = mTime;
        this.mBelongTitleId = mBelongTitleId;
    }

    @Generated(hash = 1095008121)
    public InnerAscribe() {
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

    public Long getMTime() {
        return this.mTime;
    }

    public void setMTime(Long mTime) {
        this.mTime = mTime;
    }

    public Long getMBelongTitleId() {
        return this.mBelongTitleId;
    }

    public void setMBelongTitleId(Long mBelongTitleId) {
        this.mBelongTitleId = mBelongTitleId;
    }
}
