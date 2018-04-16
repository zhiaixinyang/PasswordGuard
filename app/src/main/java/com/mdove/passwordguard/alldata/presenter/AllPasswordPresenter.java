package com.mdove.passwordguard.alldata.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.event.AllPasswordFavoriteEvent;
import com.mdove.passwordguard.alldata.model.event.AllPasswordHideEvent;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.contract.AllPasswordContract;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllPasswordPresenter implements AllPasswordContract.Presenter {
    private AllPasswordContract.MvpView mView;
    private PasswordDao mPasswordDao;

    public AllPasswordPresenter() {
        mPasswordDao = App.getDaoSession().getPasswordDao();
    }

    @Override
    public void subscribe(AllPasswordContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        List<AllPasswordModel> modelData = new ArrayList<>();
        List<Password> data = mPasswordDao.queryBuilder().list();
        for (Password info : data) {
            modelData.add(new AllPasswordModel(info));
        }
        mView.showData(modelData);
    }

    @Override
    public void favoritePassword(ItemAllPasswordVM vm) {
        Password password = vm.mAllPasswordModel.password;
        if (vm.mFavorite.get()) {
            password.isFavorite = 0;
            vm.mAllPasswordModel.mFavorite = false;
        } else {
            password.isFavorite = 1;
            vm.mAllPasswordModel.mFavorite = true;
        }

        mPasswordDao.update(password);
        mView.notifyBtnFavorite(vm.mItemPosition);

        RxBus.get().post(new AllPasswordFavoriteEvent(password.id, vm.mAllPasswordModel.mFavorite));
    }

    @Override
    public void btnHidePworDs(ItemAllPasswordVM vm) {
        Password password = vm.mAllPasswordModel.password;
        if (vm.mIsHide.get()) {
            password.isHide = 0;
            vm.mAllPasswordModel.mHide = false;
        } else {
            password.isHide = 1;
            vm.mAllPasswordModel.mHide = true;
        }

        mPasswordDao.update(password);
        mView.notifyBtnHide(vm.mItemPosition);

        RxBus.get().post(new AllPasswordHideEvent(password.id, !vm.mIsHide.get()));
    }

    @Override
    public void copyPasswordInUserName(ItemAllPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mUserName.get());
    }

    @Override
    public void copyPasswordInPassword(ItemAllPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mPassword.get());
    }
}
