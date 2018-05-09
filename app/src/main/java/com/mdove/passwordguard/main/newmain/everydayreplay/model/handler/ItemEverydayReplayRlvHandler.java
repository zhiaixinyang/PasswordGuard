package com.mdove.passwordguard.main.newmain.everydayreplay.model.handler;

import com.mdove.passwordguard.calendar.presenter.CalendarPresenter;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.presenter.EtEverydayReplayPresenter;

/**
 * Created by MDove on 2018/5/9.
 */

public class ItemEverydayReplayRlvHandler {
    public EtEverydayReplayPresenter mPresenter;

    public ItemEverydayReplayRlvHandler(EtEverydayReplayPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickDailyPlanDelete(EverydayReplayRlvModelVM vm) {
        mPresenter.onClickDailyPlanDelete(vm);
    }
}
