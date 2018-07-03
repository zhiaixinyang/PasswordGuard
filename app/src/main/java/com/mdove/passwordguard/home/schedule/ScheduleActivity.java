package com.mdove.passwordguard.home.schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityScheduleBinding;
import com.mdove.passwordguard.home.schedule.adapter.ScheduleAdapter;
import com.mdove.passwordguard.home.schedule.adapter.ScheduleShortAdapter;
import com.mdove.passwordguard.home.schedule.model.AddScheduleModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.schedule.model.handler.ScheduleHandler;
import com.mdove.passwordguard.home.schedule.presenter.SchedulePresenter;
import com.mdove.passwordguard.home.schedule.presenter.contract.ScheduleContract;
import com.mdove.passwordguard.singleplan.layoutmanager.ViewPagerLayoutManager;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/7/1.
 */

public class ScheduleActivity extends BaseActivity implements ScheduleContract.MvpView {
    private ActivityScheduleBinding mBinding;
    private SchedulePresenter mPresenter;
    private ScheduleAdapter mAdapter;
    private List<BaseScheduleModel> mData;

    public static void start(Context context) {
        Intent start = new Intent(context, ScheduleActivity.class);
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.gray_new_home));

        mPresenter = new SchedulePresenter();
        mPresenter.subscribe(this);
        mBinding.setHandler(new ScheduleHandler(mPresenter));

        mAdapter = new ScheduleAdapter(this, mPresenter);
        mBinding.rlv.setLayoutManager(new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL));
        mBinding.rlv.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<BaseScheduleModel> data) {
        mData = data;
        mAdapter.setData(data);
    }

    @Override
    public void deleteSchedule(int position) {

    }

    @Override
    public void showShort(boolean isShort) {
        ScheduleShortAdapter adapterShort = new ScheduleShortAdapter(this, mPresenter);
        ScheduleAdapter adapter = new ScheduleAdapter(this, mPresenter);
        if (isShort) {
            if (mData.size() != 1) {
                mData.add(0, new AddScheduleModel());
            }
            mBinding.rlv.setAdapter(adapterShort);
            adapterShort.setData(mData);
        } else {
            if (mData.size() > 1 && mData.get(0) instanceof AddScheduleModel) {
                mData.remove(0);
            }
            mBinding.rlv.setAdapter(adapter);
            adapter.setData(mData);
        }
    }
}
