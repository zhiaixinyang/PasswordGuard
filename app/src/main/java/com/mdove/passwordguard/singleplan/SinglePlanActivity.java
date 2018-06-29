package com.mdove.passwordguard.singleplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivitySinglePlanBinding;
import com.mdove.passwordguard.singleplan.adapter.SinglePlanAdapter;
import com.mdove.passwordguard.singleplan.layoutmanager.ViewPagerLayoutManager;
import com.mdove.passwordguard.singleplan.model.SinglePlanHandler;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.presenter.SinglePlanPresenter;
import com.mdove.passwordguard.singleplan.presenter.contract.SinglePlanContract;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanActivity extends BaseActivity implements SinglePlanContract.MvpView {
    private ActivitySinglePlanBinding mBinding;
    private SinglePlanPresenter mPresenter;
    private SinglePlanAdapter mAdapter;
    private RecyclerView mRlv;

    public static void start(Context context) {
        Intent start = new Intent(context, SinglePlanActivity.class);
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
//        fullScreen();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_plan);
        StatusBarUtils.setColorNoTranslucent(this, Color.BLACK);

        mPresenter = new SinglePlanPresenter();
        mPresenter.subscribe(this);

        mBinding.setHandler(new SinglePlanHandler(mPresenter));

        mRlv = mBinding.rlv;
        mAdapter = new SinglePlanAdapter(this, mPresenter);
        mRlv.setLayoutManager(new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL));
        mRlv.setAdapter(mAdapter);

        mPresenter.initData();
    }

    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.initData();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<SinglePlanModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void deleteSinglePlan(int position) {
        mAdapter.notifyDelete(position);
    }
}
