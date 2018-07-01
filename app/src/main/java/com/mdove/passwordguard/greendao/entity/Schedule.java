package com.mdove.passwordguard.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by MDove on 2018/7/1.
 */
@Entity
public class Schedule implements Serializable{
    @Id(autoincrement = true)
    public Long id;
    public String mSchedule;
    public int mIsSuc;//0表示没有点击完成,1表示按时，2表示未完成(提前)，3表示未完成(延迟)
    public int mIsSee;//0表示不在首页展示
    public long mTime;
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
    public String mTips;

    public int startHour,startMin;
    public int endHour,endMin;
    public int sucStartHour,sucStartMin;
    public int sucEndHour,sucEndMin;

    static final long serialVersionUID = 1L;

    @Generated(hash = 1533014208)
    public Schedule(Long id, String mSchedule, int mIsSuc, int mIsSee, long mTime,
            int mUrgent, int mImportant, long mLabelId, String mLabel, String mTips,
            int startHour, int startMin, int endHour, int endMin, int sucStartHour,
            int sucStartMin, int sucEndHour, int sucEndMin) {
        this.id = id;
        this.mSchedule = mSchedule;
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
        this.sucStartHour = sucStartHour;
        this.sucStartMin = sucStartMin;
        this.sucEndHour = sucEndHour;
        this.sucEndMin = sucEndMin;
    }

    @Generated(hash = 729319394)
    public Schedule() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMSchedule() {
        return this.mSchedule;
    }

    public void setMSchedule(String mSchedule) {
        this.mSchedule = mSchedule;
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

    public int getSucStartHour() {
        return this.sucStartHour;
    }

    public void setSucStartHour(int sucStartHour) {
        this.sucStartHour = sucStartHour;
    }

    public int getSucStartMin() {
        return this.sucStartMin;
    }

    public void setSucStartMin(int sucStartMin) {
        this.sucStartMin = sucStartMin;
    }

    public int getSucEndHour() {
        return this.sucEndHour;
    }

    public void setSucEndHour(int sucEndHour) {
        this.sucEndHour = sucEndHour;
    }

    public int getSucEndMin() {
        return this.sucEndMin;
    }

    public void setSucEndMin(int sucEndMin) {
        this.sucEndMin = sucEndMin;
    }
    
}
