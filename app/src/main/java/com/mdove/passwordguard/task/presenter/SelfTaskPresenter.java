package com.mdove.passwordguard.task.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.contract.SelfTaskContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskPresenter implements SelfTaskContract.Presenter {
    private SelfTaskContract.MvpView mView;
    private List<SelfTaskModel> mData;
    private SelfTaskDao mSelfTaskDao;

    @Override
    public void subscribe(SelfTaskContract.MvpView view) {
        mView = view;

        mData = new ArrayList<>();

        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        List<SelfTask> data = mSelfTaskDao.queryBuilder().build().list();
        for (SelfTask selfTask : data) {
            mData.add(new SelfTaskModel(selfTask));
        }
        mView.initData(mData);
    }

    @Override
    public void insertSelfTask(String content) {
        SelfTask selfTask = new SelfTask();
        selfTask.mTask = content;
        selfTask.mTime = new Date().getTime();
        selfTask.mIsSuc = 0;
        mSelfTaskDao.insert(selfTask);
        mData.add(new SelfTaskModel(selfTask));
        mView.insertSelfTask(mData.size());
    }

    @Override
    public void onClickTaskSuc(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        if (vm.mSelfTaskModel.mIsSuc) {
            selfTask.mIsSuc = 0;
            vm.mSelfTaskModel.mIsSuc = false;
            mSelfTaskDao.update(selfTask);
        } else {
            selfTask.mIsSuc = 1;
            vm.mSelfTaskModel.mIsSuc = true;
            mSelfTaskDao.update(selfTask);
        }
        mView.notifySelfTaskIsSuc(vm.mPosition);
    }

    @Override
    public void onClickSee(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        if (vm.mSelfTaskModel.mIsSee) {
            selfTask.mIsSee = 0;
            vm.mSelfTaskModel.mIsSee = false;
            mSelfTaskDao.update(selfTask);
        } else {
            selfTask.mIsSee = 1;
            vm.mSelfTaskModel.mIsSee = true;
            mSelfTaskDao.update(selfTask);
        }
        mView.notifySelfSee(vm.mPosition);
    }
}
