package com.mdove.passwordguard.home.richeditor.model.handler;

import com.mdove.passwordguard.home.richeditor.model.vm.RichEditorBtnModelVM;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;

public class RichEditorBtnHandler {
    private RichEditorPresenter mPresenter;

    public RichEditorBtnHandler(RichEditorPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickRichEditorBtn(RichEditorBtnModelVM vm) {
        mPresenter.onClickRichEditorBtn(vm.mModelType.get());
    }
}
