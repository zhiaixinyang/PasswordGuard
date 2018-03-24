package com.mdove.passwordguard.deletelist.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.deletelist.model.DeletePasswordModel;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.deletelist.model.vm.DeletePasswordModelVM;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeleteListPasswordPresenter implements DeleteListPasswordContract.Presenter {
    private DeleteListPasswordContract.MvpView mView;
    private DeletedPasswordDao mDeleteDao;
    private List<BaseMainModel> mData;

    public DeleteListPasswordPresenter() {
        mDeleteDao = App.getDaoSession().getDeletedPasswordDao();
        mData = new ArrayList<>();
    }

    @Override
    public void subscribe(DeleteListPasswordContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        initSys();

        List<DeletedPassword> list = mDeleteDao.queryBuilder().build().list();
        for (int i = 0; i < list.size(); i++) {
            mData.add(new DeletePasswordModel(list.get(i), ++i));
        }
        mView.showData(mData);
    }

    @Override
    public void deleteReturn(DeletePasswordModelVM vm) {
        mDeleteDao.delete(vm.mDeletedPassword);
        mView.deleteReturn(vm.mItemPosition);
        RxBus.get().post(new DeletePasswordReturnEvent(vm.mDeletedPassword));
    }

    @Override
    public void realDelete(DeletePasswordModelVM vm) {
        mDeleteDao.delete(vm.mDeletedPassword);
        mView.realDelete(vm.mItemPosition);
    }

    private void initSys() {
        DeleteTopModel deleteTopModel = new DeleteTopModel();
        deleteTopModel.mType = 1;
        deleteTopModel.mTime = new Date();
        mData.add(deleteTopModel);
    }
}
