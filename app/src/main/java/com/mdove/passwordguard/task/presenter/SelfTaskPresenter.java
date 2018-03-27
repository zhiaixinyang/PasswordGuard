package com.mdove.passwordguard.task.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.deletelist.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSeeEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.presenter.contract.SelfTaskContract;
import com.mdove.passwordguard.task.utils.DeleteSelfTaskHelper;
import com.mdove.passwordguard.utils.ClipboardUtils;

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
    private DeleteSelfTaskDao mDeleteSelfTaskDao;

    @Override
    public void subscribe(SelfTaskContract.MvpView view) {
        mView = view;

        mData = new ArrayList<>();

        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
        mDeleteSelfTaskDao = App.getDaoSession().getDeleteSelfTaskDao();
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
        selfTask.mPriority = 0;
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
        RxBus.get().post(new SelfTaskClickSucEvent(vm.mSelfTaskModel.mId, vm.mSelfTaskModel));
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

        RxBus.get().post(new SelfTaskClickSeeEvent(vm.mSelfTaskModel));
    }

    @Override
    public void onClickDelete(SelfTaskModelVM vm) {
        mSelfTaskDao.delete(vm.mSelfTaskModel.mSelfTask);
        mView.onClickDelete(vm.mPosition);
        mDeleteSelfTaskDao.insert(DeleteSelfTaskHelper.getDeletedSelfTask(vm.mSelfTaskModel.mSelfTask));

        RxBus.get().post(new SelfTaskClickDeleteEvent(vm.mSelfTaskModel.mId));
    }

    @Override
    public void onClickPriority(SelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        int curPriority = selfTask.mPriority;
        curPriority++;
        if (curPriority >= 3) {
            curPriority = 0;
        }
        selfTask.mPriority = curPriority;
        mSelfTaskDao.update(selfTask);

        vm.mSelfTaskModel.mPriority = curPriority;
        mView.notifySelfTaskPriority(vm.mPosition);

        RxBus.get().post(new SelfTaskClickPriorityEvent(vm.mSelfTaskModel.mId, vm.mSelfTaskModel));
    }

    @Override
    public void onClickCopy(SelfTaskModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mTask.get());
    }
}
