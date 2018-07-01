package com.mdove.passwordguard.home.allplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.home.allplan.model.AllPlanModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public interface AllPlanContract {
    interface Presenter extends BasePresenter<AllPlanContract.MvpView> {
        void initData();

        void onClickBack();
        void onClickEtPlan();
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<AllPlanModel> data);

        void finish();
    }
}
