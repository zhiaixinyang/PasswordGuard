package com.mdove.passwordguard.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.PasswordModel;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/9.
 */

public class ItemMainPasswordVM implements Serializable {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();
    public PasswordModel mPasswordModel;
    public int mItemPosition;

    public ItemMainPasswordVM(PasswordModel model, int itemPosition) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
        mTvGroup.set(model.mTvGroup);
        mPasswordModel = model;
        mItemPosition = itemPosition;
    }
}
