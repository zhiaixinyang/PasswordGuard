package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/4/9.
 */

@Entity
public class DailyPlan implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTimeStamp;
    public Integer mStatus=0;//代表得失->0为默认，1为得，2为失
    static final long serialVersionUID = 12L;
    @Generated(hash = 963805892)
    public DailyPlan(Long id, String mContent, Long mTimeStamp, Integer mStatus) {
        this.id = id;
        this.mContent = mContent;
        this.mTimeStamp = mTimeStamp;
        this.mStatus = mStatus;
    }
    @Generated(hash = 617231992)
    public DailyPlan() {
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
    public Integer getMStatus() {
        return this.mStatus;
    }
    public void setMStatus(Integer mStatus) {
        this.mStatus = mStatus;
    }


}
