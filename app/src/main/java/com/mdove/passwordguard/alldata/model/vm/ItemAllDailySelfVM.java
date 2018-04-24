package com.mdove.passwordguard.alldata.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.alldata.model.AllDailySelfModel;
import com.mdove.passwordguard.base.IFavoriteVM;
import com.mdove.passwordguard.base.IHideVM;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.DateUtils;

import java.io.Serializable;

/**
 * Created by MDove on 2018/4/7.
 */

public class ItemAllDailySelfVM implements Serializable, IFavoriteVM, IHideVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsFavorite = new ObservableField<>();
    public ObservableField<Boolean> mIsHide = new ObservableField<>();
    public DailySelf mDailySelf;
    public AllDailySelfModel mAllDailySelfModel;
    public int mItemPosition;

    public ItemAllDailySelfVM(AllDailySelfModel model, int position) {
        mTime.set(DateUtils.getDateChinese(model.mTime));
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);

        mDailySelf = model.mDailySelf;
        mItemPosition = position;
        mIsFavorite.set(model.mIsFavorite);
        mIsHide.set(model.mIsHide);
        mAllDailySelfModel = model;
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
