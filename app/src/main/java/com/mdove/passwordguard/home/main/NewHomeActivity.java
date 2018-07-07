package com.mdove.passwordguard.home.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityNewHomeBinding;
import com.mdove.passwordguard.home.main.adapter.HomeScheduleAdapter;
import com.mdove.passwordguard.home.main.fragment.HomeMessFragment;
import com.mdove.passwordguard.home.main.fragment.HomeOptionsFragment;
import com.mdove.passwordguard.home.main.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.main.model.NewHomeHandler;
import com.mdove.passwordguard.home.main.presenter.NewHomePresenter;
import com.mdove.passwordguard.home.main.presenter.contract.NewHomeContract;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.todayreview.adapter.ReViewPagerAdapter;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.DensityUtil;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/26.
 */

public class NewHomeActivity extends BaseActivity implements NewHomeContract.MvpView {
    private ActivityNewHomeBinding mBinding;
    private NewHomePresenter mPresenter;
    private CountDownTimer mCountDownTimer;
    private HomeScheduleAdapter mAdapter;
    private String[] mTitle = {"全消息", "全操作"};

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_home);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));

        mPresenter = new NewHomePresenter();
        mPresenter.subscribe(this);
        mPresenter.initTime();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeMessFragment.newInstance());
        fragments.add(HomeOptionsFragment.newInstance());

        mBinding.vp.setAdapter(new ReViewPagerAdapter(getSupportFragmentManager(), fragments, mTitle));
        mBinding.tb.setupWithViewPager(mBinding.vp);
        initTabLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRemainTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    private void initTabLayout() {
        for (int i = 0; i < mTitle.length; i++) {
            //依次获取标签
            TabLayout.Tab tab = mBinding.tb.getTabAt(i);
            //为每个标签设置布局
            tab.setCustomView(R.layout.item_tablayout_text);
            ((TextView) tab.getCustomView().findViewById(R.id.tv_title)).setText(mTitle[i]);
            TextView title = (TextView) tab.getCustomView().findViewById(R.id.tv_title);
            if (i == 0) {
                title.setTextSize(DensityUtil.dip2sp(getContext(), 6));
                title.setTextColor(Color.BLACK);
            } else {
                title.setTextSize(DensityUtil.dip2sp(getContext(), 5));
                title.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
            }
        }


        mBinding.tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView textView = view.findViewById(R.id.tv_title);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(DensityUtil.dip2sp(getContext(), 6));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView textView = view.findViewById(R.id.tv_title);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));
                textView.setTextSize(DensityUtil.dip2sp(getContext(), 5));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setRemainTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        long remainTime = DateUtils.getRemainTime();
        mCountDownTimer = new CountDownTimer(remainTime, 1000) {
            @Override
            public void onTick(final long millisUntilFinished) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int leftTime = (int) (Math.ceil(millisUntilFinished / 1000.f) - 2);
                        mBinding.tvRemainTime.setText(leftTime + "");
                    }
                });
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initTime(HomeTimeModelVM timeVM) {
        mBinding.setVm(timeVM);
    }

    @Override
    public void initSchedule(List<BaseScheduleModel> data) {
        mAdapter.setData(data);
    }
}
