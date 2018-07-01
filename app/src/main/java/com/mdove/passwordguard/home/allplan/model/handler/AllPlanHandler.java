package com.mdove.passwordguard.home.allplan.model.handler;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.home.allplan.presenter.AllPlanPresenter;
import com.mdove.passwordguard.singleplan.presenter.SinglePlanPresenter;

/**
 * Created by MDove on 2018/6/30.
 */

public class AllPlanHandler {
    private AllPlanPresenter mPresenter;

    public AllPlanHandler(AllPlanPresenter planPresenter) {
        mPresenter = planPresenter;
    }

    public void onClickBack() {
        mPresenter.onClickBack();
    }

    public void onClickEtPlan(){
        mPresenter.onClickEtPlan();
    }
}
