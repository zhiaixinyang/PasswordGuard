package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.presenter.TodayPlanPresenter;

/**
 * Created by MDove on 2018/4/14.
 */

public class ItemDailyPlanHandler {
    public TodayPlanPresenter mPresenter;

    public ItemDailyPlanHandler(TodayPlanPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickDailyPlanDelete(DailyPlanModelVM vm) {
        mPresenter.onClickDailyPlanDelete(vm);
    }
}
