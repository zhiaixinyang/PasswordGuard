package com.mdove.passwordguard.collect.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.collect.model.CollectDailySelfModel;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.model.CollectPasswordModel;
import com.mdove.passwordguard.collect.model.CollectPasswordModelVM;
import com.mdove.passwordguard.collect.model.event.CollectDailySelfEvent;
import com.mdove.passwordguard.collect.model.event.CollectPasswordEvent;
import com.mdove.passwordguard.collect.presenter.contract.CollectContract;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectPresenter implements CollectContract.Presenter {
    private CollectContract.MvpView mView;
    private List<BaseMainModel> mData;
    private DailySelfDao mDailySelfDao;
    private PasswordDao mPasswordDao;

    @Override
    public void subscribe(CollectContract.MvpView view) {
        mView = view;

        mDailySelfDao = App.getDaoSession().getDailySelfDao();
        mPasswordDao = App.getDaoSession().getPasswordDao();
    }

    @Override
    public void unSubscribe() {
    }


    @Override
    public void initData() {
        mData = new ArrayList<>();

        List<CollectPasswordModel> passwordModelData = new ArrayList<>();
        List<Password> passwordData = mPasswordDao.queryBuilder().build().list();
        for (Password password : passwordData) {
            if (password.isFavorite==1){
                passwordModelData.add(new CollectPasswordModel(password));
            }
        }
        mData.addAll(passwordModelData);

        List<CollectDailySelfModel> favoriteData = new ArrayList<>();
        List<DailySelf> dailyData = mDailySelfDao.queryBuilder().list();
        for (DailySelf dailySelf : dailyData) {
            if (dailySelf.mIsFavorite == 1) {
                favoriteData.add(new CollectDailySelfModel(dailySelf));
            }
        }

        mData.addAll(favoriteData);

        mView.showData(mData);
    }


    @Override
    public void copyDailySelf(CollectDailySelfModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mContent.get());
    }

    @Override
    public void copyPasswordInPassword(CollectPasswordModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mPassword.get());
    }

    @Override
    public void copyPasswordInUserName(CollectPasswordModelVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mUserName.get());
    }

    @Override
    public void favoriteDailySelf(CollectDailySelfModelVM vm) {
        DailySelf dailySelf = vm.mDailySelf;
        CollectDailySelfModel collectDailySelfModel = vm.collectDailySelfModel;
        boolean isFavorite;

        if (vm.mIsFavorite.get()) {
            dailySelf.mIsFavorite = 0;
            collectDailySelfModel.mIsFavorite = false;
            isFavorite = false;
        } else {
            dailySelf.mIsFavorite = 1;
            collectDailySelfModel.mIsFavorite = true;
            isFavorite = true;
        }
        mDailySelfDao.update(dailySelf);

        mView.favoriteDailySelf(vm.mItemPosition);

        RxBus.get().post(new CollectDailySelfEvent(isFavorite, dailySelf.id));
    }

    @Override
    public void favoritePassword(CollectPasswordModelVM vm) {
        Password password = vm.password;
        CollectPasswordModel passwordModel = vm.mPasswordModel;
        boolean isFavorite;

        if (vm.mFavorite.get()) {
            password.isFavorite = 0;
            passwordModel.mFavorite = false;
            isFavorite = false;
        } else {
            password.isFavorite = 1;
            passwordModel.mFavorite = true;
            isFavorite = true;
        }
        mPasswordDao.update(password);

        mView.favoritePassword(vm.mItemPosition);

        RxBus.get().post(new CollectPasswordEvent(isFavorite, password.id));
    }
}
