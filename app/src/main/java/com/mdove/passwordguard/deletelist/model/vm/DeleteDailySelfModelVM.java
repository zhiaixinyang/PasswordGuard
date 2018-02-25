package com.mdove.passwordguard.deletelist.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.deletelist.model.DeleteDailySelfModel;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/2/25.
 */
public class DeleteDailySelfModelVM {
    public ObservableField<Long> mDeleteTime = new ObservableField<>();
    public ObservableField<String> mTimeStamp = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public DeletedDailySelf mDeletedDailySelf;
    public int mItemPosition;

    public DeleteDailySelfModelVM(DeleteDailySelfModel model) {
        mContent.set(model.mContent);
        mTvGroup.set(model.mTvGroup);
        mDeleteTime.set(model.mDeleteTime);
        mTimeStamp.set(DateUtil.getDateChinese(model.mTimeStamp));
        mDeletedDailySelf = model.mDeletedDailySelf;
        mItemPosition = model.mItemPosition;
    }
}
