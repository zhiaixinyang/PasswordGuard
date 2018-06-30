package com.mdove.passwordguard.home.allplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public interface AllTodayPlanContract {
    interface Presenter extends BasePresenter<AllTodayPlanContract.MvpView> {
        void initData(long id);

        void onClickBack();
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();

        void initData(List<BaseTodayPlanModel> data);
    }
}
