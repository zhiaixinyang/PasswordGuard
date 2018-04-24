package com.mdove.passwordguard.task.model;

import android.databinding.ObservableField;

import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mTask = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
    public ObservableField<Boolean> mIsSee = new ObservableField<>();
    public ObservableField<Integer> mPriority = new ObservableField<>();
    public SucSelfTaskModel mSucSelfTaskModel;
    public int mPosition;

    public SucSelfTaskModelVM(SucSelfTaskModel sucSelfTaskModel, int position) {
        mTask.set(sucSelfTaskModel.mTask);
        mTime.set(DateUtils.getDateChinese(sucSelfTaskModel.mTime));
        mIsSuc.set(sucSelfTaskModel.mIsSuc);
        mIsSee.set(sucSelfTaskModel.mIsSee);
        mPriority.set(sucSelfTaskModel.mPriority);
        this.mSucSelfTaskModel = sucSelfTaskModel;
        mPosition = position;
    }
}
