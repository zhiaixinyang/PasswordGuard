package com.mdove.passwordguard.main.newmain.dailytask.model;

import com.mdove.passwordguard.greendao.entity.SelfTaskTimer;

/**
 * Created by MDove on 2018/6/6.
 */

public class MainSelfTaskTimerModel extends BaseMainSelfTaskModel {
    public String mTask;
    public long mTime;
    public long mStopTime;
    public boolean mIsSuc;
    public boolean mIsCancel;
    public long mId;

    public MainSelfTaskTimerModel(SelfTaskTimer selfTaskTimer) {
        mTask = selfTaskTimer.mTask;
        mId = selfTaskTimer.id;
        mStopTime = selfTaskTimer.mStopTime;
        mTime = selfTaskTimer.mTime;
        mIsSuc = selfTaskTimer.mIsSuc == 1;
        mIsCancel = selfTaskTimer.mIsCancel == 1;
    }
}
