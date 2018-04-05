package com.mdove.passwordguard.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.setting.adapter.SettingAdapter;
import com.mdove.passwordguard.setting.presenter.SettingPresenter;
import com.mdove.passwordguard.setting.presenter.contract.SettingContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/5.
 */

public class SettingActivity extends BaseActivity implements SettingContract.MvpView {
    private RecyclerView mHideRlv;
    private SettingPresenter mPresenter;
    private SettingAdapter mAdapter;
    private List<BaseMainModel> mData;

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("系统设置");
        setContentView(R.layout.activity_setting);

        mHideRlv = findViewById(R.id.rlv_hide_setting);

        mData = new ArrayList<>();
        mPresenter = new SettingPresenter();
        mPresenter.subscribe(this);
        mAdapter = new SettingAdapter(this, mData, mPresenter);
        mHideRlv.setLayoutManager(new LinearLayoutManager(this));
        mHideRlv.setAdapter(mAdapter);
        mPresenter.initHideSysItem();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showShowHideSysItem(List<BaseMainModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickBtnHideGroup(int position) {
        mAdapter.notifyDeleteByPosition(position);
    }

    @Override
    public void onClickBtnHideSearch(int position) {
        mAdapter.notifyDeleteByPosition(position);
    }

    @Override
    public void onClickBtnHideTimeTop(int position) {
        mAdapter.notifyDeleteByPosition(position);
    }

    @Override
    public void onClickBtnHideOption(int position) {
        mAdapter.notifyDeleteByPosition(position);
    }
}
