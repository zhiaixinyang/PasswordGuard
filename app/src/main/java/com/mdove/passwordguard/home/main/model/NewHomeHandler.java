package com.mdove.passwordguard.home.main.model;

import com.mdove.passwordguard.home.main.presenter.NewHomePresenter;

/**
 * Created by MDove on 2018/6/26.
 */

public class NewHomeHandler {
    private NewHomePresenter mPresenter;

    public NewHomeHandler(NewHomePresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickSchedule() {
        mPresenter.onClickSchedule();
    }

    public void onClickLongPlan() {
        mPresenter.onClickLongPlan();
    }

    public void onClickEtLongPlan() {
        mPresenter.onClickEtLongPlan();
    }

    public void onClickTodayPlanReView() {
        mPresenter.onClickTodayPlanReView();
    }

    public void onClickAllPlan() {
        mPresenter.onClickAllPlan();
    }
}
