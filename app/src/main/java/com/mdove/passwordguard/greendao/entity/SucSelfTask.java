package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/3/27.
 */
@Entity
public class SucSelfTask implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mTask;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mPriority;//优先级，0-1（黄色）-2（红色）
    public long mBelongId;
    static final long serialVersionUID = 1L;

    @Generated(hash = 217486455)
    public SucSelfTask(Long id, String mTask, long mTime, int mIsSuc, int mIsSee,
            int mPriority, long mBelongId) {
        this.id = id;
        this.mTask = mTask;
        this.mTime = mTime;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mPriority = mPriority;
        this.mBelongId = mBelongId;
    }

    @Generated(hash = 1365009134)
    public SucSelfTask() {
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

    public int getMPriority() {
        return this.mPriority;
    }

    public void setMPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    public void setMBelongId(int mBelongId) {
        this.mBelongId = mBelongId;
    }

    public long getMBelongId() {
        return this.mBelongId;
    }

    public void setMBelongId(long mBelongId) {
        this.mBelongId = mBelongId;
    }

}
