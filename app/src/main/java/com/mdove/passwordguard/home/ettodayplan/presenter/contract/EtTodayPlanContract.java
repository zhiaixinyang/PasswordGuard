package com.mdove.passwordguard.home.ettodayplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.model.HomeTimeModelVM;

/**
 * Created by MDove on 2018/6/24.
 */

public interface EtTodayPlanContract {
    interface Presenter extends BasePresenter<EtTodayPlanContract.MvpView> {
        void onClickBack();

        void onClickTime();
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();
    }
}
