package com.mdove.passwordguard.home.todayreview.model.handler;

import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

/**
 * Created by MDove on 2018/6/29.
 */

public class TodayReViewHandler {
    private TodayReViewPresenter mPresenter;

    public TodayReViewHandler(TodayReViewPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTodayReViewSuc(BaseTodayReViewVM vm) {
        mPresenter.onClickTodayReViewSuc(vm);
    }

    public void onClickBack() {
        mPresenter.onClickBack();
    }

    public void onClickTime() {
        mPresenter.onClickTime();
    }
}
