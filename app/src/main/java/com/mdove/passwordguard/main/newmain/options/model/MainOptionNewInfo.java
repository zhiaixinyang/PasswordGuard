package com.mdove.passwordguard.main.newmain.options.model;

import com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter;
import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/5/18.
 */

public class MainOptionNewInfo {
    public int mType;
    public String mTitle;
    public String mHint;
    public int mBgDrawable;
    public int mIconId;

    public MainOptionNewInfo(@OptionsPresenter.MainOptionsInfoType int type, String title, String hint, int bgDrawable, int iconId) {
        mType = type;
        mTitle = title;
        mHint = hint;
        mBgDrawable = bgDrawable;
        mIconId = iconId;
    }
}
