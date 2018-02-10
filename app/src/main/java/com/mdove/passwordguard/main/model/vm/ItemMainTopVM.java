package com.mdove.passwordguard.main.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/2/9.
 */

public class ItemMainTopVM {
    public ObservableField<String> mTimeAll = new ObservableField<>();
    public ObservableField<String> mTimeWeek = new ObservableField<>();

    public ItemMainTopVM(MainTopModel model) {
        mTimeAll.set(DateUtil.getDateChinese(model.mTime));
        mTimeWeek.set(DateUtil.getSimpleWeek(true));
    }
}
