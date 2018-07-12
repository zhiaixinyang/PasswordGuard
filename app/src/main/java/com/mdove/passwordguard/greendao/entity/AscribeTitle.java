package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by MDove on 2018/7/12.
 */
@Entity
public class AscribeTitle {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public Long mTime;
    @Generated(hash = 50682458)
    public AscribeTitle(Long id, String mContent, Long mTime) {
        this.id = id;
        this.mContent = mContent;
        this.mTime = mTime;
    }
    @Generated(hash = 1270047033)
    public AscribeTitle() {
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
}
