package com.mdove.passwordguard.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.task.adapter.SelfTaskPagerAdapter;
import com.mdove.passwordguard.task.fragment.AllSelfTaskFragment;
import com.mdove.passwordguard.task.fragment.DeleteSelfTaskFragment;
import com.mdove.passwordguard.task.fragment.SucSelfTaskFragment;
import com.mdove.passwordguard.task.model.event.SelfTaskFinishEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskLabelInsertEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/3/27.
 */

public class NewSelfTaskActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public static void start(Context context) {
        Intent start = new Intent(context, NewSelfTaskActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的随手计划");
        setContentView(R.layout.activity_self_task_new);
        initView();
        setupTabLayout();
    }

    private void initView() {
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    private void setupTabLayout() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(AllSelfTaskFragment.newInstance());
        fragmentList.add(SucSelfTaskFragment.newInstance());
        fragmentList.add(DeleteSelfTaskFragment.newInstance());

        SelfTaskPagerAdapter adapter = new SelfTaskPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setText(R.string.tab_str_all_self_task);
        mTabLayout.getTabAt(1).setText(R.string.tab_str_suc_self_task);
        mTabLayout.getTabAt(2).setText(R.string.tab_str_delete_self_task);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        RxBus.get().post(new SelfTaskFinishEvent());
    }
}
