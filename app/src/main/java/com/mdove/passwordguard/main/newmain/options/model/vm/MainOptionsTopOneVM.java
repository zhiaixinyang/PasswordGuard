package com.mdove.passwordguard.main.newmain.options.model.vm;

import android.databinding.BaseObservable;

import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;

/**
 * Created by MDove on 2018/5/18.
 */

public class MainOptionsTopOneVM extends BaseObservable {
    public String mTitle;
    public String mHint;
    public int mBgDrawable;
    public int mIconId;
    public int mType;

    public MainOptionsTopOneVM(MainOptionNewInfo model) {
        mTitle = model.mTitle;
        mHint = model.mHint;
        mBgDrawable = model.mBgDrawable;
        mIconId = model.mIconId;
        mType = model.mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
        notifyChange();
    }

    public String getHint() {
        return mHint;
    }

    public void setHint(String hint) {
        mHint = hint;
        notifyChange();
    }

    public int getBgDrawable() {
        return mBgDrawable;
    }

    public void setBgDrawable(int bgDrawable) {
        mBgDrawable = bgDrawable;
        notifyChange();
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
        notifyChange();
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
        notifyChange();
    }
}
