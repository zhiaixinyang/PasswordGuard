package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/3/15.
 */

public class MainOptionInfo {
    public int mType;
    public String mTitle;
    public String mHint;
    public int mBgDrawable;
    public int mIconId;

    public MainOptionInfo(@MainPresenter.MainOpenInfoType int type, String title, String hint, int bgDrawable, int iconId) {
        mType = type;
        mTitle = title;
        mHint = hint;
        mBgDrawable = bgDrawable;
        mIconId = iconId;
    }
}
