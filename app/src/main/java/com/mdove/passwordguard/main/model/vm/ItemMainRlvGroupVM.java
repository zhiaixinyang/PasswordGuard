package com.mdove.passwordguard.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.MainGroupRlvModel;

/**
 * Created by MDove on 2018/2/16.
 */

public class ItemMainRlvGroupVM {
    public ObservableField<String> mTvGroup = new ObservableField<>();
    public ObservableField<String> mBgColor = new ObservableField<>();
    public ObservableField<Long> mTimeStamp = new ObservableField<>();

    public ItemMainRlvGroupVM(String tvGroup) {
        mTvGroup.set(tvGroup);
    }

    public ItemMainRlvGroupVM(MainGroupRlvModel model) {
        mBgColor.set(model.mBgColor);
        mTimeStamp.set(model.mTimeStamp);
        mTvGroup.set(model.mTvGroup);
    }
}
