package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MDove on 2018/7/1.
 */
@Entity
public class LongPlan {
    @Id(autoincrement = true)
    public Long id;
    public String mLongPlan;
    public int mIsSuc;//0表示没有点击完成,1表示按时，2表示未完成(提前)，3表示未完成(延迟)
    public int mIsSee;//0表示不在首页展示
    public long mStartTime, mEndTime;
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    static final long serialVersionUID = 1L;

    @Generated(hash = 1296991972)
    public LongPlan(Long id, String mLongPlan, int mIsSuc, int mIsSee,
            long mStartTime, long mEndTime, long mTime, int mUrgent, int mImportant,
            long mLabelId, String mLabel, String mTips) {
        this.id = id;
        this.mLongPlan = mLongPlan;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mTime = mTime;
        this.mUrgent = mUrgent;
        this.mImportant = mImportant;
        this.mLabelId = mLabelId;
        this.mLabel = mLabel;
        this.mTips = mTips;
    }

    @Generated(hash = 1578271109)
    public LongPlan() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMLongPlan() {
        return this.mLongPlan;
    }

    public void setMLongPlan(String mLongPlan) {
        this.mLongPlan = mLongPlan;
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

    public long getMStartTime() {
        return this.mStartTime;
    }

    public void setMStartTime(long mStartTime) {
        this.mStartTime = mStartTime;
    }

    public long getMEndTime() {
        return this.mEndTime;
    }

    public void setMEndTime(long mEndTime) {
        this.mEndTime = mEndTime;
    }

    public int getMUrgent() {
        return this.mUrgent;
    }

    public void setMUrgent(int mUrgent) {
        this.mUrgent = mUrgent;
    }

    public int getMImportant() {
        return this.mImportant;
    }

    public void setMImportant(int mImportant) {
        this.mImportant = mImportant;
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

    public String getMTips() {
        return this.mTips;
    }

    public void setMTips(String mTips) {
        this.mTips = mTips;
    }

    public long getMTime() {
        return this.mTime;
    }

    public void setMTime(long mTime) {
        this.mTime = mTime;
    }
}
