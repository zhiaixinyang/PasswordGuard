package com.mdove.passwordguard.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.DailyPlanOptionInfo;

import java.util.List;

/**
 * Created by MDove on 2018/4/11.
 */

public interface DailyPlanOptionContract {
    interface Presenter extends BasePresenter<DailyPlanOptionContract.MvpView> {
        void initDailyPlan();

        void onClickCalender();
    }

    interface MvpView extends BaseView<Presenter> {
        void initDailyPlan(List<DailyPlanOptionInfo> data);
    }
}
