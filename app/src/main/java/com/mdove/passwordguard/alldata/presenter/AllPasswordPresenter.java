package com.mdove.passwordguard.alldata.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
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

    }

    @Override
    public void btnHidePworDs(ItemAllPasswordVM vm) {
        ItemAllPasswordVM passwordVM = (ItemAllPasswordVM) vm;
        Password password = passwordVM.mAllPasswordModel.password;
        password.isHide = 0;
        mPasswordDao.update(password);

        passwordVM.mAllPasswordModel.mHide = false;
        mView.notifyBtnHide(passwordVM.mItemPosition);
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
