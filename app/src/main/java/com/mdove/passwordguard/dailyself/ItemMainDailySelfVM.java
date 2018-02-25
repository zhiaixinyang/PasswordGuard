package com.mdove.passwordguard.dailyself;

import android.databinding.ObservableField;

import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/2/22.
 */

public class ItemMainDailySelfVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public DailySelf mDailySelf;
    public int mItemPosition;

    public ItemMainDailySelfVM(MainDailySelfModel model, int position) {
        mTime.set(DateUtil.getDateChinese(model.mTime));
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);
        mDailySelf = model.mDailySelf;
        mItemPosition = position;
    }
}
