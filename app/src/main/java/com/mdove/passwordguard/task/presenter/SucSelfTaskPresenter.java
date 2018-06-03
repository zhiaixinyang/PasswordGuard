package com.mdove.passwordguard.task.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;
import com.mdove.passwordguard.task.presenter.contract.SucSelfTaskContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class SucSelfTaskPresenter implements SucSelfTaskContract.Presenter {
    private SucSelfTaskContract.MvpView mView;
    private SucSelfTaskDao mSucSelfTaskDao;
    private SelfTaskDao mSelfTaskDao;
    private List<SucSelfTaskModel> mData;

    public SucSelfTaskPresenter() {
        mSucSelfTaskDao = App.getDaoSession().getSucSelfTaskDao();
        mSelfTaskDao = App.getDaoSession().getSelfTaskDao();
        mData = new ArrayList<>();
    }

    @Override
    public void subscribe(SucSelfTaskContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData() {
        List<SucSelfTask> selfTaskList = mSucSelfTaskDao.queryBuilder().build().list();
        for (SucSelfTask selfTask : selfTaskList) {
            if (selfTask.mIsSuc == 1) {
                SucSelfTaskModel model=new SucSelfTaskModel(selfTask);
                model.mSelfTask=mSelfTaskDao.queryBuilder().where(SelfTaskDao.Properties.Id.eq(selfTask.getMBelongId())).unique();
                mData.add(model);
            }
        }
//        List<SelfTask> selfTaskList = mSelfTaskDao.queryBuilder().build().list();
//        for (SelfTask selfTask : selfTaskList) {
//            if (selfTask.mIsSuc == 1) {
//                mData.add(new SucSelfTaskModel(selfTask));
//            }
//        }
        mView.initData(mData);
    }

    @Override
    public void onClickSuc(SelfTaskModel model) {
        if (model.mIsSuc) {
            mData.add(new SucSelfTaskModel(model.mSelfTask));
            mView.onClickSuc(mData.size(), model.mIsSuc);
        } else {
            SucSelfTaskModel sucSelfTaskModel = null;
            for (SucSelfTaskModel model1 : mData) {
                if (model1.mBelongId == model.mId) {
                    sucSelfTaskModel = model1;
                    sucSelfTaskModel.mSelfTask=model1.mSelfTask;
                }
            }
            if (sucSelfTaskModel == null) {
                return;
            }
            int position = mData.indexOf(sucSelfTaskModel);
            mView.onClickSuc(position, model.mIsSuc);
        }
    }

    @Override
    public void onClickDelete(long id) {
        SucSelfTaskModel sucSelfTaskModel = null;
        for (SucSelfTaskModel model1 : mData) {
            if (model1.mBelongId == id) {
                sucSelfTaskModel = model1;
            }
        }
        if (sucSelfTaskModel == null) {
            return;
        }
        int position = mData.indexOf(sucSelfTaskModel);
        mView.onClickDelete(position);
    }

    @Override
    public void onClickPriority(SelfTaskModel model) {
        SucSelfTaskModel sucSelfTaskModel = null;
        for (SucSelfTaskModel model1 : mData) {
            if (model1.mBelongId == model.mId) {
                sucSelfTaskModel = model1;
            }
        }
        if (sucSelfTaskModel == null) {
            return;
        }
        sucSelfTaskModel.mPriority = model.mPriority;
        int position = mData.indexOf(sucSelfTaskModel);
        mView.onClickPriority(position);
    }
}
