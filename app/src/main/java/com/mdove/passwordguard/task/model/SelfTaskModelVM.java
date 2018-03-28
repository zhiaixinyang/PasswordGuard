package com.mdove.passwordguard.task.model;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.mdove.passwordguard.base.SimpleTextWatcher;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mTask = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
    public ObservableField<Boolean> mIsSee = new ObservableField<>();
    public ObservableField<Integer> mPriority = new ObservableField<>();
    public SelfTaskModel mSelfTaskModel;
    public int mPosition;

    public SelfTaskModelVM(SelfTaskModel selfTaskModel, int position) {
        mTask.set(selfTaskModel.mTask);
        mTime.set(DateUtil.getDateChinese(selfTaskModel.mTime));
        mIsSuc.set(selfTaskModel.mIsSuc);
        mIsSee.set(selfTaskModel.mIsSee);
        mPriority.set(selfTaskModel.mPriority);
        mSelfTaskModel = selfTaskModel;
        mPosition = position;
    }

    public TextWatcher textWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mTask.set(s.toString());
            mSelfTaskModel.mTask = s.toString();
        }
    };
}
