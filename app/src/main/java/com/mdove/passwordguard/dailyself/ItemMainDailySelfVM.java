package com.mdove.passwordguard.dailyself;

import android.databinding.ObservableField;

import com.mdove.passwordguard.base.IDailySelfFavoriteVM;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.DateUtil;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/22.
 */

public class ItemMainDailySelfVM implements Serializable, IDailySelfFavoriteVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<Boolean> mIsFavorite = new ObservableField<>();
    public DailySelf mDailySelf;
    public int mItemPosition;

    public ItemMainDailySelfVM(MainDailySelfModel model, int position) {
        mTime.set(DateUtil.getDateChinese(model.mTime));
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);

        mDailySelf = model.mDailySelf;
        mItemPosition = position;
        mIsFavorite.set(model.mIsFavorite);
    }

    @Override
    public boolean isFavorite() {
        return mIsFavorite.get();
    }
}
