package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.singleplan.presenter.EtSinglePlanPresenter;
import com.mdove.passwordguard.task.LabelSettingActivity;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtSinglePlanHandler {
    private EtSinglePlanPresenter mPresenter;

    public EtSinglePlanHandler(EtSinglePlanPresenter planPresenter) {
        mPresenter = planPresenter;
    }

    public void onClickLabelSetting() {
        mPresenter.onClickLabelSetting();
    }
}
