package com.mdove.passwordguard.deletelist.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/2/14.
 */

public class ItemDeleteListTopVM {
    public ObservableField<String> mTimeAll = new ObservableField<>();

    public ItemDeleteListTopVM(DeleteTopModel model) {
        mTimeAll.set(DateUtil.getDateChinese(model.mTime));
    }
}
