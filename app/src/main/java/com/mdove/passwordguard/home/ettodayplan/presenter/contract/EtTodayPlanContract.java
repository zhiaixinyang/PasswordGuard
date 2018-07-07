package com.mdove.passwordguard.home.ettodayplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface EtTodayPlanContract {
    interface Presenter extends BasePresenter<EtTodayPlanContract.MvpView> {
        void addMainTodayPlan(MainTodayPlan todayPlan);

        void addSecondTodayPlan(SecondTodayPlan todayPlan);

        void onClickBack();

        void onClickTime();

        void initEditData(long id);
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();

        void addMainTodayPlanReturn(MainTodayPlanModel model);

        void addMainTodayPlanReturn(SecondTodayPlanModel model);

        void initEditData(List<BaseTodayPlanModel> data);
    }
}
