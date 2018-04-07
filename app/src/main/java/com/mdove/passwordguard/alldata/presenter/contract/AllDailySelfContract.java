package com.mdove.passwordguard.alldata.presenter.contract;

import com.mdove.passwordguard.alldata.model.AllDailySelfModel;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.model.vm.ItemAllDailySelfVM;
import com.mdove.passwordguard.alldata.model.vm.ItemAllPasswordVM;
import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;

import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public interface AllDailySelfContract {
    interface Presenter extends BasePresenter<AllDailySelfContract.MvpView> {
        void initData();

        void favoriteDailySelf(ItemAllDailySelfVM vm);

        void btnHidePworDs(ItemAllDailySelfVM vm);

        void copyDailySelf(ItemAllDailySelfVM vm);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<AllDailySelfModel> data);

        void notifyBtnHide(int position);
        void notifyBtnFavorite(int position);
    }
}
