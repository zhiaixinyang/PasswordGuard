package com.mdove.passwordguard.home.todayreview.model.handler;

import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.presenter.ActivityTodayReViewPresenter;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;

/**
 * Created by MDove on 2018/7/2.
 */

public class ActivityTodayReViewHandler {
    private ActivityTodayReViewPresenter mPresenter;

    public ActivityTodayReViewHandler(ActivityTodayReViewPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBack() {
        mPresenter.onClickBack();
    }

}
