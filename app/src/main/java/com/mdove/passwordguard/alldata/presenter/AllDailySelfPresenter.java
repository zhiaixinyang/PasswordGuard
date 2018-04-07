package com.mdove.passwordguard.alldata.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.alldata.model.AllDailySelfModel;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.vm.ItemAllDailySelfVM;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.alldata.presenter.contract.AllDailySelfContract;
import com.mdove.passwordguard.alldata.presenter.contract.AllPasswordContract;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfPresenter implements AllDailySelfContract.Presenter {
    private AllDailySelfContract.MvpView mView;
    private DailySelfDao mDailySelfDao;

    public AllDailySelfPresenter() {
        mDailySelfDao = App.getDaoSession().getDailySelfDao();
    }

    @Override
    public void subscribe(AllDailySelfContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initData() {
        List<AllDailySelfModel> modelData = new ArrayList<>();
        List<DailySelf> data = mDailySelfDao.queryBuilder().list();
        for (DailySelf info : data) {
            modelData.add(new AllDailySelfModel(info));
        }
        mView.showData(modelData);
    }

    @Override
    public void favoriteDailySelf(ItemAllDailySelfVM vm) {
        DailySelf dailySelf = vm.mDailySelf;
        if (vm.mIsFavorite.get()) {
            dailySelf.mIsFavorite = 0;
            vm.mAllDailySelfModel.mIsFavorite = false;
        } else {
            dailySelf.mIsFavorite = 1;
            vm.mAllDailySelfModel.mIsFavorite = true;
        }

        mDailySelfDao.update(dailySelf);
        mView.notifyBtnFavorite(vm.mItemPosition);
    }

    @Override
    public void btnHidePworDs(ItemAllDailySelfVM vm) {
        DailySelf dailySelf = vm.mDailySelf;
        if (vm.mIsHide.get()) {
            dailySelf.mIsHide = 0;
            vm.mAllDailySelfModel.mIsHide = false;
        } else {
            dailySelf.mIsHide = 1;
            vm.mAllDailySelfModel.mIsHide = true;
        }

        mDailySelfDao.update(dailySelf);
        mView.notifyBtnHide(vm.mItemPosition);
    }

    @Override
    public void copyDailySelf(ItemAllDailySelfVM vm) {
        ClipboardUtils.copyToClipboard(mView.getContext(), vm.mContent.get());
    }
}
