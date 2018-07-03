package com.mdove.passwordguard.home.longplan.model.handler;

import com.mdove.passwordguard.home.longplan.presenter.EtLongPlanPresenter;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtLongPlanHandler {
    private EtLongPlanPresenter mPresenter;

    public EtLongPlanHandler(EtLongPlanPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBack(){
        mPresenter.onClickBack();
    }

    public void onClickRichEditor(){
        mPresenter.onClickRichEditor();
    }

    public void onClickStartTimePicker() {
        mPresenter.onClickStartTimePicker();
    }

    public void onClickEndTimePicker() {
        mPresenter.onClickEndTimePicker();
    }
}
