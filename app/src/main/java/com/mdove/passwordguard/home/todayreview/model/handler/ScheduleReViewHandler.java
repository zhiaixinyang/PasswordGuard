package com.mdove.passwordguard.home.todayreview.model.handler;

import com.mdove.passwordguard.home.schedule.model.vm.ScheduleModelVM;
import com.mdove.passwordguard.home.schedule.presenter.SchedulePresenter;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;

/**
 * Created by MDove on 2018/7/2.
 */

public class ScheduleReViewHandler {
    private TodayReViewPresenter mPresenter;

    public ScheduleReViewHandler(TodayReViewPresenter planPresenter) {
        mPresenter = planPresenter;
    }

    public void onClickTodayReViewSuc(BaseTodayReViewVM vm) {
        mPresenter.onClickTodayReViewSuc(vm);
    }
}
