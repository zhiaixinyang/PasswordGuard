package com.mdove.passwordguard.home.main.model;

import com.mdove.passwordguard.home.main.presenter.HomeOptionsPresenter;

/**
 * Created by MDove on 2018/7/76.
 */

public class HomeOptionsHandler {
    private HomeOptionsPresenter mPresenter;

    public HomeOptionsHandler(HomeOptionsPresenter presenter) {
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
