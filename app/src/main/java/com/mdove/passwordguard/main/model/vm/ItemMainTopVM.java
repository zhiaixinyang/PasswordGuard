package com.mdove.passwordguard.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/2/9.
 */

public class ItemMainTopVM {
    public ObservableField<String> mTimeAll = new ObservableField<>();
    public ObservableField<String> mTimeWeek = new ObservableField<>();

    public ItemMainTopVM(MainTopModel model) {
        mTimeAll.set(DateUtils.getDateChinese(model.mTime));
        mTimeWeek.set(DateUtils.getSimpleWeek(true));
    }
}
