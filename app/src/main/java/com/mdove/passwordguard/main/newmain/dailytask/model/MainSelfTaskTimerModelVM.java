package com.mdove.passwordguard.main.newmain.dailytask.model;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.mdove.passwordguard.base.SimpleTextWatcher;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/6/6.
 */

public class MainSelfTaskTimerModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mStopTime = new ObservableField<>();
    public ObservableField<String> mTask = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
    public ObservableField<Boolean> mIsCancel = new ObservableField<>();
    public MainSelfTaskTimerModel mSelfTaskTimerModel;

    public MainSelfTaskTimerModelVM(MainSelfTaskTimerModel selfTaskModel) {
        mTask.set(selfTaskModel.mTask);
        mTime.set("创建："+DateUtils.getDateChinese(selfTaskModel.mTime));
        mIsSuc.set(selfTaskModel.mIsSuc);
        mSelfTaskTimerModel = selfTaskModel;
        mIsCancel.set(selfTaskModel.mIsCancel);
        mStopTime.set("时限："+DateUtils.getDateChinese(selfTaskModel.mStopTime));
    }

    public TextWatcher textWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mTask.set(s.toString());
            mSelfTaskTimerModel.mTask = s.toString();
        }
    };
}
