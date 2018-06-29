package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/6/29.
 */

public class BaseTodayReViewVM {
    public ObservableField<Integer> mSucEnable = new ObservableField<>();
    public ObservableField<Long> mId = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
}
