package com.mdove.passwordguard.home.richeditor.model.handler;

import com.mdove.passwordguard.home.richeditor.model.vm.RichEditorBtnModelVM;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;

public class RichEditorHandler {
    private RichEditorPresenter mPresenter;

    public RichEditorHandler(RichEditorPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBack(){
        mPresenter.onClickBack();
    }

    public void onClickReturn(){
        mPresenter.onClickReturn();
    }
}
