package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.main.presenter.DailyPlanOptionPresenter;

/**
 * Created by MDove on 2018/4/11.
 */

public class DailyPlanOptionInfo {
    public int mType;
    public String mTitle;
    public String mHint;
    public int mBgDrawable;
    public int mIconId;


    public DailyPlanOptionInfo(@DailyPlanOptionPresenter.DailyOpenInfoType int type, String title, String hint, int bgDrawable, int iconId) {
        mType = type;
        mTitle = title;
        mHint = hint;
        mBgDrawable = bgDrawable;
        mIconId = iconId;
    }
}
