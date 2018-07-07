package com.mdove.passwordguard.home.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.main.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;

import java.util.List;

/**
 * Created by MDove on 2018/7/7.
 */

public interface HomeMessContract {
    interface Presenter extends BasePresenter<HomeMessContract.MvpView> {
        void initData();
    }

    interface MvpView extends BaseView<Presenter> {
        void initData();
    }
}
