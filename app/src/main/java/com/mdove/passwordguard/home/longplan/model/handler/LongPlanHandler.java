package com.mdove.passwordguard.home.longplan.model.handler;

import com.mdove.passwordguard.home.longplan.model.vm.LongPlanModelVM;
import com.mdove.passwordguard.home.longplan.presenter.LongPlanPresenter;

/**
 * Created by MDove on 2018/7/1.
 */

public class LongPlanHandler {
    private LongPlanPresenter mPlanPresenter;

    public LongPlanHandler(LongPlanPresenter planPresenter) {
        mPlanPresenter = planPresenter;
    }

    public void onClickBack() {
        mPlanPresenter.onClickBack();
    }

    public void onClickAddLongPlan() {
        mPlanPresenter.onClickAddLongPlan();
    }

    public void onClickEditLongPlan(LongPlanModelVM vm) {
        mPlanPresenter.onClickEditLongPlan(vm.mId.get());
    }
}
