package com.mdove.passwordguard.greendao.entity;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/3/25.
 */
@Entity
public class SelfTask implements Serializable, Comparable<SelfTask> {
    @Id(autoincrement = true)
    public Long id;
    public String mTask;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mPriority;//优先级，0-1（黄色）-2（红色）
    static final long serialVersionUID = 1L;

    public long mLabelId;
    public String mLabel;

    @Generated(hash = 1455626636)
    public SelfTask(Long id, String mTask, long mTime, int mIsSuc, int mIsSee,
            int mPriority, long mLabelId, String mLabel) {
        this.id = id;
        this.mTask = mTask;
        this.mTime = mTime;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mPriority = mPriority;
        this.mLabelId = mLabelId;
        this.mLabel = mLabel;
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

    public int getMPriority() {
        return this.mPriority;
    }

    public void setMPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    @Override
    public int compareTo(@NonNull SelfTask o) {
        int result = 0;
        if (this == o) {
            result = 0;
        }
        if (mPriority > o.mPriority) {
            result = -1;
        }
        if (mPriority == 2 && mPriority == o.mPriority ||
                mPriority == 1 && mPriority == o.mPriority ||
                mPriority == 0 && mPriority == o.mPriority) {
            if (mTime >= o.mTime) {
                result = -1;
            } else {
                result = 1;
            }
        }
        if (mPriority < o.mPriority) {
            result = 1;
        }
        return result;
    }

    public long getMLabelId() {
        return this.mLabelId;
    }

    public void setMLabelId(long mLabelId) {
        this.mLabelId = mLabelId;
    }

    public String getMLabel() {
        return this.mLabel;
    }

    public void setMLabel(String mLabel) {
        this.mLabel = mLabel;
    }
}
