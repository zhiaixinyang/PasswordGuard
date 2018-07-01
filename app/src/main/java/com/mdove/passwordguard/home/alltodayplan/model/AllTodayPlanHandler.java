package com.mdove.passwordguard.home.alltodayplan.model;

import com.mdove.passwordguard.home.alltodayplan.presenter.AllTodayPlanPresenter;

/**
 * Created by MDove on 2018/6/30.
 */

public class AllTodayPlanHandler {
    private AllTodayPlanPresenter mPlanPresenter;

    public AllTodayPlanHandler(AllTodayPlanPresenter presenter) {
        mPlanPresenter = presenter;
    }

    public void onClickBack() {
        mPlanPresenter.onClickBack();
    }
}
