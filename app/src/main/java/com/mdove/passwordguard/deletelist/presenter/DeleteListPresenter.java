package com.mdove.passwordguard.deletelist.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeleteListPresenter implements DeleteListContract.Presenter {
    private DeleteListContract.MvpView mView;
    private DeletedPasswordDao mDeleteDao;
    private List<BaseMainModel> mData;

    public DeleteListPresenter() {
        mDeleteDao = App.getDaoSession().getDeletedPasswordDao();
        mData = new ArrayList<>();
    }

    @Override
    public void subscribe(DeleteListContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        initSys();

        List<DeletedPassword> list = mDeleteDao.queryBuilder().build().list();
        for (DeletedPassword deletedPassword : list) {
            mData.add(new DeletePasswordModel(deletedPassword));
        }
        mView.showData(mData);
    }

    private void initSys() {
        DeleteTopModel deleteTopModel = new DeleteTopModel();
        deleteTopModel.mType = 1;
        deleteTopModel.mTime=new Date();
        mData.add(deleteTopModel);
    }
}
