package com.mdove.passwordguard.collect.model;

import android.databinding.ObservableField;

import com.mdove.passwordguard.base.IDailySelfFavoriteVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.DateUtil;

import java.io.Serializable;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectDailySelfModelVM implements Serializable, IDailySelfFavoriteVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsFavorite = new ObservableField<>();
    public DailySelf mDailySelf;
    public CollectDailySelfModel collectDailySelfModel;
    public int mItemPosition;

    public CollectDailySelfModelVM(CollectDailySelfModel model, int position) {
        mTime.set(DateUtil.getDateChinese(model.mTime));
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);
        mIsFavorite.set(model.mIsFavorite);

        collectDailySelfModel = model;
        mDailySelf = model.mDailySelf;
        mItemPosition = position;
    }

    @Override
    public boolean isFavorite() {
        return mIsFavorite.get();
    }
}
