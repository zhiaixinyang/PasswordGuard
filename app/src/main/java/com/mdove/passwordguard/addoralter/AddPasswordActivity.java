package com.mdove.passwordguard.addoralter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.adapter.AddPasswordGroupAdapter;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.event.AddPasswordActivityEvent;
import com.mdove.passwordguard.addoralter.presenter.AddPasswordPresenter;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/19.
 */

public class AddPasswordActivity extends BaseActivity implements AddPasswordContract.MvpView {
    private RecyclerView mRlvGroup;
    private EditText mEtUserName, mEtPassword, mEtTitle;
    private TextView mBtnCancel, mBtnOk, mTvGroup;
    private AddPasswordGroupAdapter mAdapter;
    private AddPasswordPresenter mPresenter;
    private String mDefaultTitle = AppConstant.DEFAULT_CHECK_GROUP_TITLE;
    private String mTitle, mUserName, mPassword;
    private Password password;


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
        mTvGroup = findViewById(R.id.tv_group);

        mAdapter = new AddPasswordGroupAdapter(this, new ArrayList<AddPasswordGroupRlvModel>());
        mRlvGroup.setLayoutManager(new GridLayoutManager(this, 3));
        mRlvGroup.setAdapter(mAdapter);
        mAdapter.setOnCheckListener(new AddPasswordGroupAdapter.OnCheckListener() {
            @Override
            public void onCheck(boolean isCheck, String selectTitle) {
                if (!isCheck) {
                    mTvGroup.setText("");
                    mDefaultTitle = null;
                    return;
                }
                mTvGroup.setText(selectTitle);
                mDefaultTitle = selectTitle;
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOkEnable()) {
                    RxBus.get().post(new AddPasswordActivityEvent(password));
                    finish();
                    return;
                }
                ToastHelper.shortToast("请完成对应信息");
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
    public void showGroup(List<AddPasswordGroupRlvModel> data) {
        mAdapter.addData(data);
    }

    private boolean isOkEnable() {
        getAllText();
        if (TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mUserName)
                || TextUtils.isEmpty(mPassword)|| TextUtils.isEmpty(mDefaultTitle)) {
            return false;
        }
        password = new Password();
        password.mPassword = mPassword;
        password.mUserName = mUserName;
        password.mTitle = mTitle;
        password.mTimeStamp = new Date().getTime();
        password.isNew = 1;
        password.mTvGroup = mDefaultTitle;

        return true;
    }

    private void getAllText() {
        mTitle = mEtTitle.getText().toString().trim();
        mUserName = mEtUserName.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
    }
}
