package com.mdove.passwordguard.main.newmain.options.model.handler;

import android.databinding.BaseObservable;

import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;
import com.mdove.passwordguard.main.newmain.options.model.vm.BaseMainOptionsTopVM;
import com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter;

/**
 * Created by MDove on 2018/5/27.
 */

public class MainOptionsTopHandler extends BaseObservable {
    private OptionsPresenter mPresenter;

    public MainOptionsTopHandler(OptionsPresenter presenter) {
        mPresenter=presenter;
    }

    public void onClick(BaseMainOptionsTopVM baseObservable){
        mPresenter.onClickBtnTop(baseObservable);
    }
}
