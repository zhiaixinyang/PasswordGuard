package com.mdove.passwordguard.addoralter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.adapter.AddPasswordGroupAdapter;
import com.mdove.passwordguard.addoralter.presenter.AddPasswordPresenter;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.ui.SelectorFactory;
import com.mdove.passwordguard.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordActivity extends BaseActivity implements AddPasswordContract.MvpView {
    private RecyclerView mRlvGroup;
    private EditText mEtUserName, mEtPassword, mEtTitle;
    private TextView mBtnCancel, mBtnOk;
    private AddPasswordGroupAdapter mAdapter;
    private AddPasswordPresenter mPresenter;

    public static void start(Context context) {
        Intent start = new Intent(context, AddPasswordActivity.class);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("添加账号信息");
        setContentView(R.layout.activity_add_password);
        initView();
    }

    private void initView() {
        mEtPassword = findViewById(R.id.et_password);
        mEtTitle = findViewById(R.id.et_title);
        mEtUserName = findViewById(R.id.et_username);
        mRlvGroup = findViewById(R.id.rlv_group);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);

        mEtUserName.setBackground(SelectorFactory.newShapeSelector()
                .setCornerRadius(DensityUtil.dip2px(this, 5))
                .setDefaultBgColor(ContextCompat.getColor(this, R.color.white)).create());
        mEtTitle.setBackground(SelectorFactory.newShapeSelector()
                .setCornerRadius(DensityUtil.dip2px(this, 5))
                .setDefaultBgColor(ContextCompat.getColor(this, R.color.white)).create());
        mEtPassword.setBackground(SelectorFactory.newShapeSelector()
                .setCornerRadius(DensityUtil.dip2px(this, 5))
                .setDefaultBgColor(ContextCompat.getColor(this, R.color.white)).create());

        mAdapter = new AddPasswordGroupAdapter(this, new ArrayList<GroupInfo>());
        mRlvGroup.setLayoutManager(new GridLayoutManager(this,3));
        mRlvGroup.setAdapter(mAdapter);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPresenter = new AddPasswordPresenter();
        mPresenter.subscribe(this);
        mPresenter.initGroup();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showGroup(List<GroupInfo> data) {
        mAdapter.addData(data);
    }
}
