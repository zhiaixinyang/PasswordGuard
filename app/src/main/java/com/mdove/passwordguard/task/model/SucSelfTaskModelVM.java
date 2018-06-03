package com.mdove.passwordguard.task.model;

import android.databinding.ObservableField;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mUsedTime = new ObservableField<>();
    public ObservableField<String> mCreateTime = new ObservableField<>();
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
        mCreateTime.set(getCreateTime(sucSelfTaskModel));
        mUsedTime.set(getUsedTime(sucSelfTaskModel));
    }

    private String getUsedTime(SucSelfTaskModel model) {
        long createTime = model.mTime;
        long sucTime = 0L;
        SelfTask selfTask;
        if (model.mSelfTask == null) {
            selfTask = App.getDaoSession().getSelfTaskDao().queryBuilder().where(SelfTaskDao.Properties.Id.eq(model.mBelongId)).unique();
            if (selfTask != null) {
                model.mSelfTask = selfTask;
                createTime = selfTask.mTime;
            } else {
                createTime = 0L;
            }
        }
        long usedTime = sucTime - createTime;
        return DateUtils.getHourMChinese(usedTime);
    }

    private String getCreateTime(SucSelfTaskModel model) {
        SelfTask selfTask = model.mSelfTask;
        long belongId = model.mBelongId;
        if (selfTask == null) {
            if (selfTask != null) {
                model.mSelfTask = selfTask;
                return DateUtils.getDateChinese(selfTask.mTime);
            } else {
                return "获取创建时间失败...";
            }
        } else {
            return DateUtils.getDateChinese(selfTask.mTime);
        }
    }
}
