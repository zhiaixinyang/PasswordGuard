package com.mdove.passwordguard.home.longplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityLongPlanBinding;
import com.mdove.passwordguard.home.longplan.adapter.LongPlanAdapter;
import com.mdove.passwordguard.home.longplan.model.BaseLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.handler.LongPlanHandler;
import com.mdove.passwordguard.home.longplan.presenter.LongPlanPresenter;
import com.mdove.passwordguard.home.longplan.presenter.contract.LongPlanContract;
import com.mdove.passwordguard.singleplan.layoutmanager.ViewPagerLayoutManager;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/1.
 */

public class LongPlanActivity extends BaseActivity implements LongPlanContract.MvpView {
    private ActivityLongPlanBinding mBinding;
    private LongPlanPresenter mPresenter;
    private LongPlanAdapter mAdapter;
    private RecyclerView mRlv;

    public static void start(Context context) {
        Intent start = new Intent(context, LongPlanActivity.class);
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_long_plan);
        StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.gray_new_home));

        mPresenter = new LongPlanPresenter();
        mPresenter.subscribe(this);
        mBinding.setHandler(new LongPlanHandler(mPresenter));

        mRlv = mBinding.rlv;
        mAdapter = new LongPlanAdapter(this, mPresenter);
        mRlv.setLayoutManager(new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL));
        mRlv.setAdapter(mAdapter);

        mPresenter.initData();
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
    public void initData(List<BaseLongPlanModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void deleteLongPlan(int position) {
        mAdapter.notifyDelete(position);
    }
}
