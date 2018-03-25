package com.mdove.passwordguard.task.model.handle;

import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskHandler {
    private SelfTaskPresenter mPresenter;

    public SelfTaskHandler(SelfTaskPresenter presenter) {
        mPresenter = presenter;
    }

    public void onClickTaskSuc(SelfTaskModelVM vm) {
        mPresenter.onClickTaskSuc(vm);
    }

}
