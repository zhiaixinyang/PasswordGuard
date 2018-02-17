package com.mdove.passwordguard.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.PasswordModel;

/**
 * Created by MDove on 2018/2/9.
 */

public class ItemMainPasswordVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();

    public ItemMainPasswordVM(PasswordModel model) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
        mTvGroup.set(model.mTvGroup);
    }
}
