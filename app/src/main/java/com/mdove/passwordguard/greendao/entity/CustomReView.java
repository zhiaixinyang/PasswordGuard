package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CustomReView {
    @Id(autoincrement = true)
    public Long id;
    public String mContent;
    public long mTime;
    @Generated(hash = 1215295691)
    public CustomReView(Long id, String mContent, long mTime) {
        this.id = id;
        this.mContent = mContent;
        this.mTime = mTime;
    }
    @Generated(hash = 73247511)
    public CustomReView() {
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
    public long getMTime() {
        return this.mTime;
    }
    public void setMTime(long mTime) {
        this.mTime = mTime;
    }
}
