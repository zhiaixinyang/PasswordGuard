package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.InnerAscribe;

public class InnerAscribeModel {
    public String mContent;
    public long mTime;

    public InnerAscribeModel(InnerAscribe innerAscribe) {
        mContent = innerAscribe.mContent;
        mTime = innerAscribe.mTime;
    }
}
