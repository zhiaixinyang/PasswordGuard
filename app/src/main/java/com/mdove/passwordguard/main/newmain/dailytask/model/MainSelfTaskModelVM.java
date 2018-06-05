package com.mdove.passwordguard.main.newmain.dailytask.model;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.mdove.passwordguard.base.SimpleTextWatcher;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelConstant;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mTask = new ObservableField<>();
    public ObservableField<String> mLabel = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
    public ObservableField<Boolean> mIsSee = new ObservableField<>();
    public ObservableField<Integer> mPriority = new ObservableField<>();
    public SelfTaskModel mSelfTaskModel;

    public MainSelfTaskModelVM(SelfTaskModel selfTaskModel) {
        mTask.set(selfTaskModel.mTask);
        mTime.set(DateUtils.getDateChinese(selfTaskModel.mTime));
        mIsSuc.set(selfTaskModel.mIsSuc);
        mIsSee.set(selfTaskModel.mIsSee);
        mPriority.set(selfTaskModel.mPriority);
        mSelfTaskModel = selfTaskModel;
        String label = mSelfTaskModel.mSelfTask.getMLabel();
        mLabel.set(TextUtils.isEmpty(label) ? LabelConstant.DEFAULT_LABEL : label);
    }

    public TextWatcher textWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            mTask.set(s.toString());
            mSelfTaskModel.mTask = s.toString();
        }
    };
}
