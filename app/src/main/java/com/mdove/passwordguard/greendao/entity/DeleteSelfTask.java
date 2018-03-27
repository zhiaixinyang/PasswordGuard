package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/3/27.
 */
@Entity
public class DeleteSelfTask implements Serializable {
    @Id(autoincrement = true)
    public Long id;
    public String mTask;
    public long mCreateTime;
    public long mDeleteTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mPriority;//优先级，0-1（黄色）-2（红色）
    static final long serialVersionUID = 1L;
    @Generated(hash = 198217245)
    public DeleteSelfTask(Long id, String mTask, long mCreateTime, long mDeleteTime,
            int mIsSuc, int mIsSee, int mPriority) {
        this.id = id;
        this.mTask = mTask;
        this.mCreateTime = mCreateTime;
        this.mDeleteTime = mDeleteTime;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mPriority = mPriority;
    }
    @Generated(hash = 1764302656)
    public DeleteSelfTask() {
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
    public long getMCreateTime() {
        return this.mCreateTime;
    }
    public void setMCreateTime(long mCreateTime) {
        this.mCreateTime = mCreateTime;
    }
    public long getMDeleteTime() {
        return this.mDeleteTime;
    }
    public void setMDeleteTime(long mDeleteTime) {
        this.mDeleteTime = mDeleteTime;
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
}
