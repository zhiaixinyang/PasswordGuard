package com.mdove.passwordguard.search.presenter;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.AddDailySelfActivity;
import com.mdove.passwordguard.addoralter.AddPasswordActivity;
import com.mdove.passwordguard.addoralter.EditPasswordActivity;
import com.mdove.passwordguard.addoralter.model.AlterDailySelfModel;
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.deletelist.DeleteListDailySelfActivity;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.DeletedDailySelfDao;
import com.mdove.passwordguard.greendao.DeletedPasswordDao;
import com.mdove.passwordguard.greendao.GroupInfoDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedDailySelf;
import com.mdove.passwordguard.greendao.entity.DeletedPassword;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.greendao.utils.DeleteDailySelfHelper;
import com.mdove.passwordguard.greendao.utils.DeletedPasswordHelper;
import com.mdove.passwordguard.group.GroupSettingActivity;
import com.mdove.passwordguard.main.AddGroupDialog;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.manager.UpdateStatusManager;
import com.mdove.passwordguard.model.net.RealUpdate;
import com.mdove.passwordguard.net.ApiServerImpl;
import com.mdove.passwordguard.search.presenter.contract.SearchResultContract;
import com.mdove.passwordguard.update.UpdateDialog;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

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
