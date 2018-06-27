package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.singleplan.presenter.EtSinglePlanPresenter;
import com.mdove.passwordguard.singleplan.presenter.SinglePlanPresenter;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanHandler {
    private SinglePlanPresenter mPresenter;

    public SinglePlanHandler(SinglePlanPresenter planPresenter) {
        mPresenter = planPresenter;
    }

    public void onClickInEtSinglePlan() {
        mPresenter.onClickInEtSinglePlan();
    }

//    public void onClickDeleteSinglePlan(SinglePlanModelVM vm) {
//        mPresenter.onClickDeleteSinglePlan(vm.mSinglePlanId.get());
//    }
}
