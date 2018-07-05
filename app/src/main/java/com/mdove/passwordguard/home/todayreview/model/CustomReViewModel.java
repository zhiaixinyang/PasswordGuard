package com.mdove.passwordguard.home.todayreview.model;

import com.mdove.passwordguard.greendao.entity.CustomReView;

public class CustomReViewModel {
    public String mContent;
    public long mTime;

    public CustomReViewModel(CustomReView customReView) {
        mContent = customReView.mContent;
        mTime = customReView.mTime;
    }
}
