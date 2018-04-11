package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.calendar.CalendarSmoothActivity;

/**
 * Created by MDove on 2018/4/10.
 */

public class MainDailyPlanHandler {
    private MainPresenter mPresenter;

    public MainDailyPlanHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnHideDailyPlan() {
        mPresenter.onClickBtnHideDailyPlan();
    }
}
