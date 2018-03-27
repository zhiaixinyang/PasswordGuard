package com.mdove.passwordguard.deletelist.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;

/**
 * Created by MDove on 2018/2/14.
 */
public class DeletePasswordModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();
    public DeletedPassword mDeletedPassword;
    public int mItemPosition;

    public DeletePasswordModelVM(DeletePasswordModel model, int position) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
        mDeletedPassword = model.mDeletedPassword;
        mItemPosition = position;
        mTvGroup.set(model.mTvGroup);
    }
}
