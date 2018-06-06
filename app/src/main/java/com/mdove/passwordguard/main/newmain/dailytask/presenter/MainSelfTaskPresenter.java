package com.mdove.passwordguard.main.newmain.dailytask.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.contract.MainSelfTaskContract;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickEditEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.task.utils.DeleteSelfTaskHelper;
import com.mdove.passwordguard.utils.ClipboardUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class MainSelfTaskPresenter implements MainSelfTaskContract.Presenter {
    private MainSelfTaskContract.MvpView mView;
    private List<SelfTaskModel> mData;
    private SelfTaskDao mSelfTaskDao;
    private SucSelfTaskDao mSucSelfTaskDao;
    private DeleteSelfTaskDao mDeleteSelfTaskDao;

    @Override
    public void subscribe(MainSelfTaskContract.MvpView view) {
        mView = view;

        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
        mDeleteSelfTaskDao = App.getDaoSession().getDeleteSelfTaskDao();
        mSucSelfTaskDao = App.getDaoSession().getSucSelfTaskDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<SelfTask> data = mSelfTaskDao.queryBuilder().build().list();
        Comparator<SelfTask> comparator = new Comparator<SelfTask>() {
            @Override
            public int compare(SelfTask o1, SelfTask o2) {
                return o1.compareTo(o2);
            }
        };
        Collections.sort(data, comparator);

        for (SelfTask selfTask : data) {
            if (selfTask.mIsSee == 1) {
                mData.add(new SelfTaskModel(selfTask));
            }
        }

        mView.initData(mData);
    }

    @Override
    public void insertSelfTask(String content, LabelTempModel tempModel) {
        SelfTask selfTask = new SelfTask();
        selfTask.mTask = content;
        selfTask.mTime = new Date().getTime();
        selfTask.mIsSuc = 0;
        selfTask.mIsSee = 1;
        selfTask.mPriority = 0;
        selfTask.mLabel = tempModel.mLabel;
        selfTask.mLabelId = tempModel.mSelectId;
        mSelfTaskDao.insert(selfTask);
        mData.add(new SelfTaskModel(selfTask));
        mView.insertSelfTask(mData.size());
    }

    @Override
    public void onClickTaskSuc(MainSelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;

        int position = -1;
        SelfTaskModel selfTaskModel = null;
        for (SelfTaskModel model : mData) {
            if (model.mId == vm.mSelfTaskModel.mSelfTask.id) {
                selfTaskModel = model;
            }
        }
        if (selfTaskModel == null) {
            return;
        }
        if (selfTaskModel.mIsSuc) {
            selfTask.mIsSuc = 0;
            selfTaskModel.mIsSuc = false;
            mSelfTaskDao.update(selfTask);

            List<SucSelfTask> sucSelfTasks = mSucSelfTaskDao.queryBuilder()
                    .where(SucSelfTaskDao.Properties.MBelongId.eq(selfTask.id)).list();
            if (sucSelfTasks != null && sucSelfTasks.size() > 0) {
                mSucSelfTaskDao.delete(sucSelfTasks.get(0));
            }
        } else {
            selfTask.mIsSuc = 1;
            selfTaskModel.mIsSuc = true;
            mSelfTaskDao.update(selfTask);

            SucSelfTask sucSelfTask = new SucSelfTask();
            sucSelfTask.mBelongId = selfTask.id;
            sucSelfTask.mIsSee = selfTask.mIsSee;
            sucSelfTask.mIsSuc = 1;
            sucSelfTask.mPriority = selfTask.mPriority;
            sucSelfTask.mTask = selfTask.mTask;
            sucSelfTask.mTime = new Date().getTime();
            mSucSelfTaskDao.insert(sucSelfTask);
        }

        position = mData.indexOf(selfTaskModel);
        mView.notifySelfTaskIsSuc(position);
        RxBus.get().post(new SelfTaskClickSucEvent(vm.mSelfTaskModel.mId, vm.mSelfTaskModel));
    }

    @Override
    public void onClickTaskSuc(MainSelfTaskTimerModelVM vm) {
        ToastHelper.shortToast("点击完成");
    }

    @Override
    public void onClickSee(MainSelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        SelfTaskModel selfTaskModel = null;
        int position = -1;
        for (SelfTaskModel model : mData) {
            if (model.mId == selfTask.id) {
                selfTaskModel = model;
            }
        }
        selfTaskModel.mSelfTask.mIsSee = 0;
        mSelfTaskDao.update(selfTask);

        position = mData.indexOf(selfTaskModel);

        mView.notifySelfSee(position);
    }

    @Override
    public void onClickDelete(MainSelfTaskModelVM vm) {
        mSelfTaskDao.delete(vm.mSelfTaskModel.mSelfTask);
        int position = -1;
        SelfTaskModel selfTaskModel = null;
        for (SelfTaskModel model : mData) {
            if (model.mId == vm.mSelfTaskModel.mSelfTask.id) {
                selfTaskModel = model;
            }
        }
        position = mData.indexOf(selfTaskModel);
        if (position != -1) {
            mView.onClickDelete(position);
            mDeleteSelfTaskDao.insert(DeleteSelfTaskHelper.getDeletedSelfTask(vm.mSelfTaskModel.mSelfTask));
            RxBus.get().post(new SelfTaskClickDeleteEvent(vm.mSelfTaskModel.mId));
        }
    }

    @Override
    public void onClickDelete(MainSelfTaskTimerModelVM vm) {
        ToastHelper.shortToast("点击删除");
    }

    @Override
    public void onClickPriority(MainSelfTaskModelVM vm) {
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;
        int curPriority = selfTask.mPriority;
        curPriority++;
        if (curPriority >= 3) {
            curPriority = 0;
        }
        selfTask.mPriority = curPriority;
        mSelfTaskDao.update(selfTask);


        int position = -1;
        SelfTaskModel selfTaskModel = null;
        for (SelfTaskModel model : mData) {
            if (model.mId == selfTask.id) {
                selfTaskModel = model;
            }
        }
        position = mData.indexOf(selfTaskModel);

        vm.mSelfTaskModel.mPriority = curPriority;
        if (position != -1) {
            mView.notifySelfTaskPriority(position);
            RxBus.get().post(new SelfTaskClickPriorityEvent(vm.mSelfTaskModel.mId, vm.mSelfTaskModel));
        }
    }

    @Override
    public void onClickCopy(MainSelfTaskModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mTask.get());
    }

    @Override
    public void onClickBtnEdit(MainSelfTaskModelVM vm, boolean isChange) {
        if (!isChange) {
            ToastHelper.shortToast(mView.getContext().getResources().getString(R.string.string_self_task_edit_error));
            return;
        }
        SelfTask selfTask = vm.mSelfTaskModel.mSelfTask;

        int position = -1;
        SelfTaskModel selfTaskModel = null;
        for (SelfTaskModel model : mData) {
            if (model.mId == selfTask.id) {
                selfTaskModel = model;
            }
        }
        position = mData.indexOf(selfTaskModel);
        selfTask.mTask = vm.mTask.get();
        mSelfTaskDao.update(selfTask);

        vm.mSelfTaskModel.mTask = vm.mTask.get();

        mView.onClickBtnEdit(position);

        ToastHelper.shortToast(mView.getContext().getResources().getString(R.string.string_self_task_edit_suc));
        RxBus.get().post(new SelfTaskClickEditEvent(vm.mSelfTaskModel.mId, vm.mSelfTaskModel));
    }
}
