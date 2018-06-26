package com.mdove.passwordguard.home.ettodayplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityEtTodayPlanBinding;
import com.mdove.passwordguard.home.ettodayplan.model.EtTodayPlanHandler;
import com.mdove.passwordguard.home.ettodayplan.presenter.EtTodayPlanPresenter;
import com.mdove.passwordguard.home.ettodayplan.presenter.contract.EtTodayPlanContract;
import com.mdove.passwordguard.home.model.NewHomeHandler;
import com.mdove.passwordguard.singleplan.presenter.contract.EtSinglePlanContract;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;

/**
 * Created by MDove on 2018/6/26.
 */

public class EtTodayPlanActivity extends BaseActivity implements EtTodayPlanContract.MvpView {
    private EtTodayPlanPresenter mPresenter;
    private ActivityEtTodayPlanBinding mBinding;

    public static void start(Context context) {
        Intent start = new Intent(context, EtTodayPlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_today_plan);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));

        mPresenter = new EtTodayPlanPresenter();
        mPresenter.subscribe(this);

        mBinding.setHandler(new EtTodayPlanHandler(mPresenter));
    }

    @Override
    public Context getContext() {
        return this;
    }
}
