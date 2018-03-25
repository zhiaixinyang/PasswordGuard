package com.mdove.passwordguard.main.model.handler;

import com.mdove.passwordguard.main.adapter.MainSelfTaskAdapter;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskHandler {
    private MainSelfTaskAdapter mAdapter;
    private MainPresenter mPresenter;

    public MainSelfTaskHandler(MainPresenter presenter, MainSelfTaskAdapter adapter) {
        mAdapter = adapter;
        mPresenter = presenter;
    }

    public void onClickTaskSuc(SelfTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm,mAdapter);
    }
}
