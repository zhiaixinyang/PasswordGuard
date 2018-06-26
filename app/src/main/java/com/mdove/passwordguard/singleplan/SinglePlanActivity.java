package com.mdove.passwordguard.singleplan;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_single_plan);
        StatusBarUtils.setTranslucent(this);

        mPresenter = new SinglePlanPresenter();
        mPresenter.subscribe(this);

        mBinding.setHandler(new SinglePlanHandler(mPresenter));

        mRlv = mBinding.rlv;
        mAdapter = new SinglePlanAdapter(this,mPresenter);
        mRlv.setLayoutManager(new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL));
        mRlv.setAdapter(mAdapter);

        mPresenter.initSinglePlan();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initSinglePlan(List<SinglePlanModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void deleteSinglePlan(int position) {
        mAdapter.notifyDelete(position);
    }
}
