package com.mdove.passwordguard.main.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

import java.util.List;

/**
 * Created by MDove on 2018/4/9.
 */

public interface TodayPlanContract {
    interface Presenter extends BasePresenter<TodayPlanContract.MvpView> {
        void initData();

        void addDailyPlan(String string);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<DailyPlanModel> data);
        void addDailyPlan(int position);

    }
}
