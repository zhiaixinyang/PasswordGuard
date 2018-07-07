package com.mdove.passwordguard.home.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.main.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface NewHomeContract {
    interface Presenter extends BasePresenter<NewHomeContract.MvpView> {
        void initTime();

        void initSchedule();

        void onClickSchedule();

        void onClickTodayPlanReView();

        void onClickAllPlan();

        void onClickEtLongPlan();

        void onClickLongPlan();
    }

    interface MvpView extends BaseView<Presenter> {
        void initTime(HomeTimeModelVM timeVM);

        void initSchedule(List<BaseScheduleModel> data);
    }
}
