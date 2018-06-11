package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/6/6.
 */
@Entity
public class SelfTaskTimer {
    @Id(autoincrement = true)
    public Long id;
    public String mTask;
    public long mTime;
    public long mStopTime;
    public long mNotificationId;
    public long mIsCancel;//0表示不取消
    public int mIsSuc;//0表示没有点击完成

    @Generated(hash = 1796684151)
    public SelfTaskTimer(Long id, String mTask, long mTime, long mStopTime,
            long mNotificationId, long mIsCancel, int mIsSuc) {
        this.id = id;
        this.mTask = mTask;
        this.mTime = mTime;
        this.mStopTime = mStopTime;
        this.mNotificationId = mNotificationId;
        this.mIsCancel = mIsCancel;
        this.mIsSuc = mIsSuc;
    }

    @Generated(hash = 1912737467)
    public SelfTaskTimer() {
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

    public long getMNotificationId() {
        return this.mNotificationId;
    }

    public void setMNotificationId(long mNotificationId) {
        this.mNotificationId = mNotificationId;
    }

    public long getMIsCancel() {
        return this.mIsCancel;
    }

    public void setMIsCancel(long mIsCancel) {
        this.mIsCancel = mIsCancel;
    }

    public long getMStopTime() {
        return this.mStopTime;
    }

    public void setMStopTime(long mStopTime) {
        this.mStopTime = mStopTime;
    }
}
