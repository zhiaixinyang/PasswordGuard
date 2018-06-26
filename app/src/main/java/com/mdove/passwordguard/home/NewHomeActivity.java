package com.mdove.passwordguard.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityNewHomeBinding;
import com.mdove.passwordguard.home.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.model.NewHomeHandler;
import com.mdove.passwordguard.home.presenter.NewHomePresenter;
import com.mdove.passwordguard.home.presenter.contract.NewHomeContract;
import com.mdove.passwordguard.utils.StatusBarUtils;

/**
 * Created by MDove on 2018/6/26.
 */

public class NewHomeActivity extends BaseActivity implements NewHomeContract.MvpView {
    private ActivityNewHomeBinding mBinding;
    private NewHomePresenter mPresenter;

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_home);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this,R.color.gray_new_home));

        mPresenter = new NewHomePresenter();
        mPresenter.subscribe(this);
        mPresenter.initTime();

        mBinding.setHandler(new NewHomeHandler(mPresenter));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initTime(HomeTimeModelVM timeVM) {
        mBinding.setVm(timeVM);
    }
}
