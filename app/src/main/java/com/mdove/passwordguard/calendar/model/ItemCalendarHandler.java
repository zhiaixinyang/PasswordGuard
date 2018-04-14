package com.mdove.passwordguard.calendar.model;

import com.mdove.passwordguard.calendar.presenter.CalendarPresenter;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.presenter.TodayPlanPresenter;

/**
 * Created by MDove on 2018/4/14.
 */

public class ItemCalendarHandler {
    public CalendarPresenter mPresenter;

    public ItemCalendarHandler(CalendarPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickDailyPlanDelete(DailyPlanModelVM vm) {
        mPresenter.onClickDailyPlanDelete(vm);
    }
}
