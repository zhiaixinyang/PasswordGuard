package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.OuterAscribeModel;
import com.mdove.passwordguard.utils.DateUtils;

public class OuterAscribeModelVM {
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public OuterAscribeModelVM(OuterAscribeModel model) {
        mContent.set(model.mContent);
        mTime.set(DateUtils.getDateChinese(model.mTime));
    }
}
