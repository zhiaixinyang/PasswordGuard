package com.mdove.passwordguard.main.newmain.model.handler;

import com.mdove.passwordguard.main.newmain.model.EverydayReplayModelVM;
import com.mdove.passwordguard.main.newmain.presenter.EverydayReplayPresenter;

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
