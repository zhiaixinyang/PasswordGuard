package com.mdove.passwordguard.base.listlayout.model;

import android.databinding.ObservableField;
import android.widget.TextView;

public class TestModelVM extends BaseModelVM{
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();

    public TestModelVM(String time, String content) {
        mTime.set(time);
        mContent.set(content);
    }
}
