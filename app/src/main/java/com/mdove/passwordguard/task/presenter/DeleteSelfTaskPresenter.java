package com.mdove.passwordguard.task.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.DeleteSelfTaskDao;
import com.mdove.passwordguard.greendao.entity.DeleteSelfTask;
import com.mdove.passwordguard.task.model.DeleteSelfTaskModel;
import com.mdove.passwordguard.task.presenter.contract.DeleteSelfTaskContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskPresenter implements DeleteSelfTaskContract.Presenter {
    private DeleteSelfTaskContract.MvpView mView;
    private DeleteSelfTaskDao mDeleteSelfTaskDao;
    private List<DeleteSelfTaskModel> mData;

    public DeleteSelfTaskPresenter() {
        mDeleteSelfTaskDao = App.getDaoSession().getDeleteSelfTaskDao();
        mData = new ArrayList<>();
    }

    @Override
    public void subscribe(DeleteSelfTaskContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        List<DeleteSelfTask> deleteSelfTasks = mDeleteSelfTaskDao.queryBuilder().build().list();
        for (DeleteSelfTask deleteSelfTask : deleteSelfTasks) {
            mData.add(new DeleteSelfTaskModel(deleteSelfTask));
        }
        mView.initData(mData);
    }

    @Override
    public void onClickDelete(long id) {
        DeleteSelfTaskModel deleteSelfTaskModel = null;
        for (DeleteSelfTaskModel model1 : mData) {
            if (model1.mId == id) {
                deleteSelfTaskModel = model1;
            }
        }
        if (deleteSelfTaskModel == null) {
            return;
        }
        int position = mData.indexOf(deleteSelfTaskModel);
        mView.onClickDelete(position);
    }
}
