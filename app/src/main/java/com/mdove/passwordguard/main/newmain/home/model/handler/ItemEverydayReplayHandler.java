package com.mdove.passwordguard.main.newmain.home.model.handler;

import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModelVM;
import com.mdove.passwordguard.main.newmain.home.presenter.EverydayReplayPresenter;

/**
 * Created by MDove on 2018/5/4.
 */

public class ItemEverydayReplayHandler {
    public EverydayReplayPresenter mPresenter;

    public ItemEverydayReplayHandler(EverydayReplayPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickDailyPlanDelete(EverydayReplayModelVM vm) {
        mPresenter.onClickDailyPlanDelete(vm);
    }
}
