package com.mdove.passwordguard.task.model;

import android.databinding.ObservableField;

import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskModelVM {
    public ObservableField<String> mCreateTime = new ObservableField<>();
    public ObservableField<String> mDeleteTime = new ObservableField<>();
    public ObservableField<String> mTask = new ObservableField<>();
    public ObservableField<Boolean> mIsSuc = new ObservableField<>();
    public ObservableField<Boolean> mIsSee = new ObservableField<>();
    public ObservableField<Integer> mPriority = new ObservableField<>();
    public DeleteSelfTaskModel mDeleteSelfTaskModel;
    public int mPosition;

    public DeleteSelfTaskModelVM(DeleteSelfTaskModel deleteSelfTaskModel, int position) {
        mTask.set(deleteSelfTaskModel.mTask);
        mCreateTime.set(DateUtil.getDateChinese(deleteSelfTaskModel.mCreateTime));
        mDeleteTime.set(DateUtil.getDateChinese(deleteSelfTaskModel.mDeleteTime));
        mIsSuc.set(deleteSelfTaskModel.mIsSuc);
        mIsSee.set(deleteSelfTaskModel.mIsSee);
        mPriority.set(deleteSelfTaskModel.mPriority);
        mDeleteSelfTaskModel = deleteSelfTaskModel;
        mPosition = position;
    }
}
