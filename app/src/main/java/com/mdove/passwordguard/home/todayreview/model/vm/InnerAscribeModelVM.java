package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.utils.DateUtils;

public class InnerAscribeModelVM {
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public InnerAscribeModelVM(InnerAscribeModel model) {
        mContent.set(model.mContent);
        mTime.set(DateUtils.getDateChinese(model.mTime));
    }
}
