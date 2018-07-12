package com.mdove.passwordguard.home.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.main.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface HomeOptionsContract {
    interface Presenter extends BasePresenter<HomeOptionsContract.MvpView> {
        void onClickSchedule();

        void onClickTodayPlanReView();

        void onClickAllPlan();

        void onClickEtLongPlan();
        void onClickEtSchedule();
        void onClickEtReView();

        void onClickLongPlan();
    }

    interface MvpView extends BaseView<Presenter> {
    }
}
