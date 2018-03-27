package com.mdove.passwordguard.task.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.SelfTaskDao;
import com.mdove.passwordguard.greendao.SucSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;
import com.mdove.passwordguard.greendao.entity.SelfTask;
import com.mdove.passwordguard.greendao.entity.SucSelfTask;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SucSelfTaskModel;
import com.mdove.passwordguard.task.presenter.contract.DeleteSelfTaskContract;
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
//        mSucSelfTaskDao = App.getDaoSession().getSucSelfTaskDao();
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
//        List<SucSelfTask> selfTaskList = mSucSelfTaskDao.queryBuilder().build().list();
//        for (SucSelfTask selfTask : selfTaskList) {
//            if (selfTask.mIsSuc == 1) {
//                mData.add(new SucSelfTaskModel(selfTask));
//            }
//        }
        List<SelfTask> selfTaskList = mSelfTaskDao.queryBuilder().build().list();
        for (SelfTask selfTask : selfTaskList) {
            if (selfTask.mIsSuc == 1) {
                mData.add(new SucSelfTaskModel(selfTask));
            }
        }
        mView.initData(mData);
    }
}
