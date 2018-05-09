package com.mdove.passwordguard.main.newmain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityHomeBinding;
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

        initTabLayout();
    }

    private void initTabLayout() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(OptionsFragment.newInstance());
        fragmentList.add(EverydayReplayFragment.newInstance());
        fragmentList.add(DailyTaskFragment.newInstance());

        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayoutExt.setupWithViewPager(mViewPager);

        mTabLayoutExt.getTabAt(0).setText(R.string.home_tab_options).setIcon(R.mipmap.ic_btn_lock);
        mTabLayoutExt.getTabAt(1).setText(R.string.home_tab_everyday_replay).setIcon(R.mipmap.ic_btn_lock);
        mTabLayoutExt.getTabAt(2).setText(R.string.home_tab_daily_task).setIcon(R.mipmap.ic_btn_lock);
    }
}
