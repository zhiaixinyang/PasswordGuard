package com.mdove.passwordguard.collect.model;

import android.databinding.ObservableField;

import com.mdove.passwordguard.base.IFavoriteVM;
import com.mdove.passwordguard.greendao.entity.Password;

import java.io.Serializable;

/**
 * Created by MDove on 2018/3/31.
 */

public class CollectPasswordModelVM implements Serializable, IFavoriteVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();
    public ObservableField<Boolean> mFavorite = new ObservableField<>();
    public CollectPasswordModel mPasswordModel;
    public Password password;
    public int mItemPosition;

    public CollectPasswordModelVM(CollectPasswordModel model, int itemPosition) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
        mTvGroup.set(model.mTvGroup);
        mFavorite.set(model.mFavorite);
        mPasswordModel = model;
        mItemPosition = itemPosition;
        password = model.password;
    }

    @Override
    public boolean isFavorite() {
        return mFavorite.get();
    }
}
