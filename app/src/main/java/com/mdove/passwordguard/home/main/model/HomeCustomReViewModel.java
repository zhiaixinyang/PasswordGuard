package com.mdove.passwordguard.home.main.model;

import com.mdove.passwordguard.greendao.entity.CustomReView;

public class HomeCustomReViewModel extends BaseHomeMessModel {
    public String mContent;

    public HomeCustomReViewModel(CustomReView customReView) {
        mContent = customReView.mContent;
        mTime = customReView.mTime;
        id = customReView.id;
    }
}
