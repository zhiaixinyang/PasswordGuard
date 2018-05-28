package com.mdove.passwordguard.main.newmain.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityHomeBinding;
import com.mdove.passwordguard.main.newmain.dailytask.MainSelfTaskFragment;
import com.mdove.passwordguard.main.newmain.options.OptionsFragment;
import com.mdove.passwordguard.task.NewSelfTaskActivity;
import com.mdove.passwordguard.ui.tablayout.TabLayoutExt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDOve on 2018/5/3.
 */

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding mBinding;
    private ViewPager mViewPager;
    private TabLayoutExt mTabLayoutExt;
    private EverydayReplayFragment mEverydayReplayFragment;

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        mTabLayoutExt = mBinding.tabLayout;
        mViewPager = mBinding.viewPager;

        mBinding.btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewSelfTaskActivity.start(HomeActivity.this);
            }
        });

        initTabLayout();
    }

    private void initTabLayout() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(OptionsFragment.newInstance());
        mEverydayReplayFragment = EverydayReplayFragment.newInstance();
        fragmentList.add(mEverydayReplayFragment);
        fragmentList.add(MainSelfTaskFragment.newInstance());

        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayoutExt.setupWithViewPager(mViewPager);

        mTabLayoutExt.getTabAt(0).setText(R.string.home_tab_options).setIcon(R.mipmap.ic_tab_options);
        mTabLayoutExt.getTabAt(1).setText(R.string.home_tab_everyday_replay).setIcon(R.mipmap.ic_tab_everyday_replay);
        mTabLayoutExt.getTabAt(2).setText(R.string.home_tab_daily_task).setIcon(R.mipmap.ic_tab_daily_task);


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        setTitleAndTime(true,
                                getString(R.string.fragment_title_options), null);
                        mBinding.btnIn.setVisibility(View.GONE);
                        break;
                    }
                    case 1: {
                        if (mEverydayReplayFragment != null) {
                            mEverydayReplayFragment.initToolBar();
                        }
                        mBinding.btnIn.setVisibility(View.GONE);
                        break;
                    }
                    case 2: {
                        setTitleAndTime(true,
                                getString(R.string.fragment_title_daily_task), null);
                        mBinding.btnIn.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    public void setToolbarTitle(String title) {
        mBinding.toolbar.setTitle(title);
    }

    public void setToolbarTime(String time) {
        mBinding.tvTime.setVisibility(View.VISIBLE);
        mBinding.tvTime.setText(time);
    }

    public void setTitleAndTime(boolean isHide, String title, String time) {
        setToolbarTitle(title);
        if (isHide) {
            mBinding.tvTime.setVisibility(View.GONE);
        } else {
            mBinding.tvTime.setVisibility(View.VISIBLE);
            mBinding.tvTime.setText(time);
        }
    }
}
