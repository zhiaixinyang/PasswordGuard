package com.mdove.passwordguard.alldata.presenter.contract;

import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;

import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public interface AllPasswordContract {
    interface Presenter extends BasePresenter<AllPasswordContract.MvpView> {
        void initData();

        void favoritePassword(ItemAllPasswordVM vm);

        void btnHidePworDs(ItemAllPasswordVM vm);

        void copyPasswordInUserName(ItemAllPasswordVM vm);

        void copyPasswordInPassword(ItemAllPasswordVM vm);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<AllPasswordModel> data);

        void notifyBtnHide(int position);
        void notifyBtnFavorite(int position);
    }
}
