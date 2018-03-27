package com.mdove.passwordguard.search.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.search.presenter.contract.SearchResultContract;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/24.
 */

public class SearchResultPresenter implements SearchResultContract.Presenter {
    private SearchResultContract.MvpView mView;
    private List<BaseMainModel> mData;
    private DailySelfDao mDailySelfDao;

    @Override
    public void subscribe(SearchResultContract.MvpView view) {
        mView = view;
        mDailySelfDao = App.getDaoSession().getDailySelfDao();
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initData(List<BaseMainModel> data) {
        mData = data;
    }

    @Override
    public void favoriteDailySelf(ItemMainDailySelfVM vm) {
        if (vm.mDailySelf.mIsFavorite == 0) {
            vm.mDailySelf.mIsFavorite = 1;
            mDailySelfDao.update(vm.mDailySelf);

            MainDailySelfModel model = (MainDailySelfModel) mData.get(vm.mItemPosition);

            model.mIsFavorite = true;
            mView.notifyDailySelfData(vm.mItemPosition);
        } else {
            vm.mDailySelf.mIsFavorite = 0;
            mDailySelfDao.update(vm.mDailySelf);

            MainDailySelfModel model = (MainDailySelfModel) mData.get(vm.mItemPosition);
            model.mIsFavorite = false;
            mView.notifyDailySelfData(vm.mItemPosition);
        }
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
