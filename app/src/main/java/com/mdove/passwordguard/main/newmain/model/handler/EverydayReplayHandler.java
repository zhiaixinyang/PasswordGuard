package com.mdove.passwordguard.main.newmain.model.handler;

import com.mdove.passwordguard.main.newmain.presenter.EverydayReplayPresenter;

/**
 * Created by MDove on 2018/5/9.
 */

public class EverydayReplayHandler {
    public EverydayReplayPresenter mPresenter;

    public EverydayReplayHandler(EverydayReplayPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickEt() {
        mPresenter.onClickEt();
    }
}
