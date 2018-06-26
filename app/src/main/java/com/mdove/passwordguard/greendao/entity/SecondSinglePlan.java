package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by MDove on 2018/6/25.
 */
@Entity
public class SecondSinglePlan {
    @Id(autoincrement = true)
    public Long id;
    public String mSinglePlan;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mUrgent;
    public int mImportant;
    static final long serialVersionUID = 1L;

    public long mLabelId;
    public String mLabel;
    @Generated(hash = 190407092)
    public SecondSinglePlan(Long id, String mSinglePlan, long mTime, int mIsSuc,
            int mIsSee, int mUrgent, int mImportant, long mLabelId, String mLabel) {
        this.id = id;
        this.mSinglePlan = mSinglePlan;
        this.mTime = mTime;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mUrgent = mUrgent;
        this.mImportant = mImportant;
        this.mLabelId = mLabelId;
        this.mLabel = mLabel;
    }
    @Generated(hash = 1582336188)
    public SecondSinglePlan() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMSinglePlan() {
        return this.mSinglePlan;
    }
    public void setMSinglePlan(String mSinglePlan) {
        this.mSinglePlan = mSinglePlan;
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
    
}
