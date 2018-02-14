package com.mdove.passwordguard.deletelist.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;

/**
 * Created by MDove on 2018/2/14.
 */
public class DeletePasswordModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();

    public DeletePasswordModelVM(DeletePasswordModel model) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
    }
}
