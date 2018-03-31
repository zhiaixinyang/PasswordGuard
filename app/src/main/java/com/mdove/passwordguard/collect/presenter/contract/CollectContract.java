package com.mdove.passwordguard.collect.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.model.CollectPasswordModelVM;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;

import java.util.List;

/**
 * Created by MDove on 2018/3/28.
 */

public interface CollectContract {
    interface Presenter extends BasePresenter<CollectContract.MvpView> {
        void initData();

        void copyDailySelf(CollectDailySelfModelVM vm);

        void copyPasswordInPassword(CollectPasswordModelVM vm);

        void copyPasswordInUserName(CollectPasswordModelVM vm);

        void favoriteDailySelf(CollectDailySelfModelVM vm);
        void favoritePassword(CollectPasswordModelVM vm);

    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseMainModel> data);

        void favoriteDailySelf(int position);
        void favoritePassword(int position);

    }
}
