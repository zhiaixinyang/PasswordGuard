package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/3/25.
 */
@Entity
public class SelfTask implements Serializable{
    @Id(autoincrement = true)
    public Long id;
    public String mTask;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    static final long serialVersionUID = 1L;
    @Generated(hash = 1178034780)
    public SelfTask(Long id, String mTask, long mTime, int mIsSuc, int mIsSee) {
        this.id = id;
        this.mTask = mTask;
        this.mTime = mTime;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
    }
    @Generated(hash = 1540708757)
    public SelfTask() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMTask() {
        return this.mTask;
    }
    public void setMTask(String mTask) {
        this.mTask = mTask;
    }
    public long getMTime() {
        return this.mTime;
    }
    public void setMTime(long mTime) {
        this.mTime = mTime;
    }
    public int getMIsSuc() {
        return this.mIsSuc;
    }
    public void setMIsSuc(int mIsSuc) {
        this.mIsSuc = mIsSuc;
    }
    public int getMIsSee() {
        return this.mIsSee;
    }
    public void setMIsSee(int mIsSee) {
        this.mIsSee = mIsSee;
    }

}
