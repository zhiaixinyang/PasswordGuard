package com.mdove.passwordguard.home.todayreview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by MDove on 2018/6/30.
 */

public class ReViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;
    private String[] mTitles;

    public ReViewPagerAdapter(FragmentManager fm, List<Fragment> data, String[] titles) {
        super(fm);
        mData = data;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
