package com.mdove.passwordguard.home.todayreview.model.handler;

import com.mdove.passwordguard.home.schedule.model.vm.ScheduleModelVM;
import com.mdove.passwordguard.home.schedule.presenter.SchedulePresenter;

/**
 * Created by MDove on 2018/7/2.
 */

public class ScheduleReViewHandler {
    private SchedulePresenter mPlanPresenter;

    public ScheduleReViewHandler(SchedulePresenter planPresenter) {
        mPlanPresenter = planPresenter;
    }

    public void onClickBack() {
        mPlanPresenter.onClickBack();
    }

    public void onClickEtSchedule() {
        mPlanPresenter.onClickEtSchedule();
    }

    public void onClickEditSchedule(ScheduleModelVM vm) {
        mPlanPresenter.onClickEditSchedule(vm.mId.get());
    }
}
