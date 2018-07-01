package com.mdove.passwordguard.home.schedule.model.handler;

import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.home.schedule.presenter.EtSchedulePresenter;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtScheduleHandler {
    private EtSchedulePresenter mPresenter;

    public EtScheduleHandler(EtSchedulePresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBack() {
        mPresenter.onClickBack();
    }

    public void onClickTime() {
        mPresenter.onClickTimePicker();
    }
}
