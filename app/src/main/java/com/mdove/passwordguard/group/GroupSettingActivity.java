package com.mdove.passwordguard.group;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.group.adapter.GroupSettingAdapter;
import com.mdove.passwordguard.group.model.event.GroupDeleteEvent;
import com.mdove.passwordguard.group.presenter.GroupSettingPresenter;
import com.mdove.passwordguard.group.presenter.contract.GroupSettingContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/2/21.
 */

public class GroupSettingActivity extends BaseActivity implements GroupSettingContract.MvpView {
    private RecyclerView mRlvGroup;
    private GroupSettingAdapter mAdapter;
    private GroupSettingPresenter mPresenter;
    private List<GroupInfo> mDate;

    public static void start(Context context) {
        Intent start = new Intent(context, GroupSettingActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("设置分组信息");
        setContentView(R.layout.activity_group_setting);

        mRlvGroup = findViewById(R.id.rlv_group);

        mDate = new ArrayList<>();
        mPresenter = new GroupSettingPresenter();
        mPresenter.subscribe(this);
        mAdapter = new GroupSettingAdapter(mPresenter, mDate);
        mRlvGroup.setLayoutManager(new LinearLayoutManager(this));
        mRlvGroup.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<GroupInfo> data) {
        if (data == null || data.size() == 0) {
            setDataIsEmpty(true);
        } else {
            mAdapter.setData(data);
            setDataIsEmpty(false);
        }
    }

    @Override
    public void deleteSuc(int position) {
        mAdapter.notifyDeleteGroup(position);
        RxBus.get().post(new GroupDeleteEvent());
    }
}
