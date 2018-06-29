package com.mdove.passwordguard.home.model;

import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.presenter.NewHomePresenter;

/**
 * Created by MDove on 2018/6/26.
 */

public class NewHomeHandler {
    private NewHomePresenter mPresenter;

    public NewHomeHandler(NewHomePresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickEtTodayPlan() {
        mPresenter.onClickEtTodayPlan();
    }

    public void onClickTodayPlanReView() {
        mPresenter.onClickTodayPlanReView();
    }

}
