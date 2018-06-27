package com.mdove.passwordguard.home.ettodayplan.model.handler;

import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.singleplan.model.EtSinglePlanHandler;

/**
 * Created by MDove on 2018/6/26.
 */

public class EtTodayPlanHandler {
    private EtTodayPlanPresenter mPresenter;

    public EtTodayPlanHandler(EtTodayPlanPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBack() {
        mPresenter.onClickBack();
    }

    public void onClickTime() {
        mPresenter.onClickTime();
    }
}
