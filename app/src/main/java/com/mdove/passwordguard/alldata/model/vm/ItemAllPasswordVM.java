package com.mdove.passwordguard.alldata.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.base.IFavoriteVM;
import com.mdove.passwordguard.main.model.MainPasswordModel;
import com.mdove.passwordguard.main.model.impl.IHideVm;

import java.io.Serializable;

/**
 * Created by MDove on 2018/4/7.
 */

public class ItemAllPasswordVM implements Serializable ,IFavoriteVM,IHideVm{
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mPassword = new ObservableField<>();
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsNew = new ObservableField<>();
    public ObservableField<Boolean> mFavorite = new ObservableField<>();
    public ObservableField<Boolean> mIsHide = new ObservableField<>();
    public AllPasswordModel mAllPasswordModel;
    public int mItemPosition;

    public ItemAllPasswordVM(AllPasswordModel model, int itemPosition) {
        mTime.set(model.mTime);
        mUserName.set(model.mUserName);
        mPassword.set(model.mPassword);
        mTitle.set(model.mTitle);
        mIsNew.set(model.mIsNew);
        mTvGroup.set(model.mTvGroup);
        mFavorite.set(model.mFavorite);
        mIsHide.set(model.mHide);
        mAllPasswordModel = model;
        mItemPosition = itemPosition;
    }

    @Override
    public boolean isFavorite() {
        return mFavorite.get();
    }
}
