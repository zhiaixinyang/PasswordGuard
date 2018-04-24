package com.mdove.passwordguard.dailyself;

import android.databinding.ObservableField;

import com.mdove.passwordguard.base.IFavoriteVM;
import com.mdove.passwordguard.base.IHideVM;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.DateUtils;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/22.
 */

public class ItemMainDailySelfVM implements Serializable, IFavoriteVM, IHideVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsFavorite = new ObservableField<>();
    public ObservableField<Boolean> mIsHide = new ObservableField<>();
    public DailySelf mDailySelf;
    public MainDailySelfModel mMainDailySelfModel;
    public int mItemPosition;

    public ItemMainDailySelfVM(MainDailySelfModel model, int position) {
        mTime.set(DateUtils.getDateChinese(model.mTime));
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);

        mDailySelf = model.mDailySelf;
        mItemPosition = position;
        mIsFavorite.set(model.mIsFavorite);
        mIsHide.set(model.mIsHide);
        mMainDailySelfModel = model;
    }

    @Override
    public boolean isFavorite() {
        return mIsFavorite.get();
    }

    @Override
    public boolean isHide() {
        return mIsHide.get();
    }
}
