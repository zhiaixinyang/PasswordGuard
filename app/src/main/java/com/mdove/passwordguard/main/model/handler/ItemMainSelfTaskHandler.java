package com.mdove.passwordguard.main.model.handler;

import android.widget.EditText;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.main.model.event.DailyTaskScrollEvent;
import com.mdove.passwordguard.main.presenter.MainPresenter;

/**
 * Created by MDove on 2018/4/6.
 */

public class ItemMainSelfTaskHandler {
    private MainPresenter mPresenter;

    public ItemMainSelfTaskHandler(MainPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickBtnMainSelfTaskIn() {
        mPresenter.onClickBtnMainSelfTaskIn();
    }

    public void onClickBtnLayoutEt(){
    }

    //没用
    public void onClickBtnSend(EditText editText) {
        mPresenter.onClickBtnMainSelfTaskSend(editText.getText().toString());
    }
}
