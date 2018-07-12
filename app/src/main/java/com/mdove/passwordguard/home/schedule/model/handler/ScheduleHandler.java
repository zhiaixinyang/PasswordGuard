package com.mdove.passwordguard.home.schedule.model.handler;

import com.mdove.passwordguard.home.schedule.model.vm.ScheduleModelVM;
import com.mdove.passwordguard.home.schedule.presenter.SchedulePresenter;

/**
 * Created by MDove on 2018/7/1.
 */

public class ScheduleHandler {
    private SchedulePresenter mPlanPresenter;

    public ScheduleHandler(SchedulePresenter planPresenter) {
        mPlanPresenter = planPresenter;
    }

    public void onClickBack() {
        mPlanPresenter.onClickBack();
    }

    public void onClickReViewSchedule() {
        mPlanPresenter.onClickReViewSchedule();
    }
    public void onClickEtSchedule() {
        mPlanPresenter.onClickEtSchedule();
    }

    public void onClickShowShort() {
        mPlanPresenter.onClickShowShort();
    }

    public void onClickEditSchedule(ScheduleModelVM vm) {
        mPlanPresenter.onClickEditSchedule(vm.mId.get());
    }
}
