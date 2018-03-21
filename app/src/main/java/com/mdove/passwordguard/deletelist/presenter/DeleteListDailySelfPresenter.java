package com.mdove.passwordguard.deletelist.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.deletelist.model.DeleteDailySelfModel;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.deletelist.model.vm.DeleteDailySelfModelVM;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteListDailySelfPresenter implements DeleteListDailySelfContract.Presenter {
    private DeleteListDailySelfContract.MvpView mView;
    private DeletedDailySelfDao mDeleteDao;
    private List<BaseMainModel> mData;

    public DeleteListDailySelfPresenter() {
        mDeleteDao = App.getDaoSession().getDeletedDailySelfDao();
        mData = new ArrayList<>();
    }

    @Override
    public void subscribe(DeleteListDailySelfContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        initSys();

        List<DeletedDailySelf> list = mDeleteDao.queryBuilder().build().list();
        for (int i = 0; i < list.size(); i++) {
            mData.add(new DeleteDailySelfModel(list.get(i)));
        }
        mView.showData(mData);
    }

    @Override
    public void deleteReturn(DeleteDailySelfModelVM vm) {
        mDeleteDao.delete(vm.mDeletedDailySelf);
        mView.deleteReturn(vm.mItemPosition);
        RxBus.get().post(new DeleteDailySelfReturnEvent(vm.mDeletedDailySelf));
    }

    @Override
    public void warningDeleteDialog(DeleteDailySelfModelVM vm) {
        mView.warningDeleteDialog(vm);
    }

    @Override
    public void realDelete(DeleteDailySelfModelVM vm) {
        mDeleteDao.delete(vm.mDeletedDailySelf);
        mView.realDelete(vm.mItemPosition);
    }

    private void initSys() {
        DeleteTopModel deleteTopModel = new DeleteTopModel();
        deleteTopModel.mType = 1;
        deleteTopModel.mTime = new Date();
        mData.add(deleteTopModel);
    }
}
