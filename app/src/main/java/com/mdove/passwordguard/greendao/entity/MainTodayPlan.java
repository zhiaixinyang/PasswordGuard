package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/6/27.
 */
@Entity
public class MainTodayPlan implements Serializable{
    @Id(autoincrement = true)
    public Long id;
    public String mTodayPlan;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;
    public int startHour,startMin;
    public int endHour,endMin;

    static final long serialVersionUID = 1L;

    @Generated(hash = 1833831679)
    public MainTodayPlan(Long id, String mTodayPlan, int mIsSuc, int mIsSee,
            long mTime, int mUrgent, int mImportant, long mLabelId, String mLabel,
            String mTips, int startHour, int startMin, int endHour, int endMin) {
        this.id = id;
        this.mTodayPlan = mTodayPlan;
        this.mIsSuc = mIsSuc;
        this.mIsSee = mIsSee;
        this.mTime = mTime;
        this.mUrgent = mUrgent;
        this.mImportant = mImportant;
        this.mLabelId = mLabelId;
        this.mLabel = mLabel;
        this.mTips = mTips;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
    }
    @Generated(hash = 419990215)
    public MainTodayPlan() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMTodayPlan() {
        return this.mTodayPlan;
    }
    public void setMTodayPlan(String mTodayPlan) {
        this.mTodayPlan = mTodayPlan;
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
    public long getMTime() {
        return this.mTime;
    }
    public void setMTime(long mTime) {
        this.mTime = mTime;
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
    public int getStartHour() {
        return this.startHour;
    }
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    public int getStartMin() {
        return this.startMin;
    }
    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }
    public int getEndHour() {
        return this.endHour;
    }
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    public int getEndMin() {
        return this.endMin;
    }
    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

}
