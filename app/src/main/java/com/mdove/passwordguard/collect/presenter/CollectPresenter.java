package com.mdove.passwordguard.collect.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.collect.presenter.contract.CollectContract;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
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

    @Override
    public void subscribe(CollectContract.MvpView view) {
        mView = view;

        mDailySelfDao = App.getDaoSession().getDailySelfDao();
    }

    @Override
    public void unSubscribe() {
    }


    @Override
    public void initData() {
        mData = new ArrayList<>();

//        List<Password> data = mPasswordDao.queryBuilder().build().list();
//        for (Password password : data) {
//            mData.add(new PasswordModel(password));
//        }

        List<MainDailySelfModel> favoriteData = new ArrayList<>();
        List<DailySelf> dailyData = mDailySelfDao.queryBuilder().list();
        for (DailySelf dailySelf : dailyData) {
            MainDailySelfModel favoriteModel = new MainDailySelfModel(dailySelf);
            if (dailySelf.mIsFavorite == 1) {
                favoriteData.add(favoriteModel);
            }
        }
        mData.addAll(favoriteData);

        mView.showData(mData);
    }


    @Override
    public void copyDailySelf(ItemMainDailySelfVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mContent.get());
    }

    @Override
    public void copyPasswordInPassword(ItemMainPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mPassword.get());
    }

    @Override
    public void copyPasswordInUserName(ItemMainPasswordVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mUserName.get());
    }
}
