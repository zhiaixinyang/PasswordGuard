package com.mdove.passwordguard.search.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.search.model.SearchRlvModel;

/**
 * Created by MDove on 2018/2/15.
 */
public class SearchRlvModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();

    public SearchRlvModelVM(SearchRlvModel model) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
    }
}
