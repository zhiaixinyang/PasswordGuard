package com.mdove.passwordguard.home.allplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityAllPlanBinding;
import com.mdove.passwordguard.home.allplan.adapter.AllPlanAdapter;
import com.mdove.passwordguard.home.allplan.model.AllPlanModel;
import com.mdove.passwordguard.home.allplan.model.handler.AllPlanHandler;
import com.mdove.passwordguard.home.allplan.presenter.AllPlanPresenter;
import com.mdove.passwordguard.home.allplan.presenter.contract.AllPlanContract;
import com.mdove.passwordguard.singleplan.layoutmanager.ViewPagerLayoutManager;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class AllPlanActivity extends BaseActivity implements AllPlanContract.MvpView {
    private ActivityAllPlanBinding mBinding;
    private AllPlanPresenter mPresenter;
    private AllPlanAdapter mAdapter;

    public static void start(Context context) {
        Intent start = new Intent(context, AllPlanActivity.class);
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_all_plan);
        StatusBarUtils.setColorNoTranslucent(this, Color.BLACK);

        mPresenter = new AllPlanPresenter();
        mPresenter.subscribe(this);

        mAdapter = new AllPlanAdapter(this, mPresenter);
        mBinding.setHandler(new AllPlanHandler(mPresenter));
        mBinding.rlv.setLayoutManager(new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL));
        mBinding.rlv.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<AllPlanModel> data) {
        mAdapter.setData(data);
    }
}
