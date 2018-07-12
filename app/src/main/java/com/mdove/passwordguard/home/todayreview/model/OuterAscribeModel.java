package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.OuterAscribe;

public class OuterAscribeModel {
    public String mContent;
    public long mTime;

    public OuterAscribeModel(OuterAscribe outerAscribe) {
        mContent = outerAscribe.mContent;
        mTime = outerAscribe.mTime;
    }
}
