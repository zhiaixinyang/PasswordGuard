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
import com.mdove.passwordguard.addoralter.adapter.AddDailySelfGroupAdapter;
import com.mdove.passwordguard.addoralter.adapter.AddPasswordGroupAdapter;
import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.event.AddDailySelfActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.AddPasswordActivityEvent;
import com.mdove.passwordguard.addoralter.presenter.AddDailySelfPresenter;
import com.mdove.passwordguard.addoralter.presenter.AddPasswordPresenter;
import com.mdove.passwordguard.addoralter.presenter.contract.AddDailySelfContract;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfActivity extends BaseActivity implements AddDailySelfContract.MvpView {
    private RecyclerView mRlvGroup;
    private EditText mEtContent;
    private TextView mBtnCancel, mBtnOk, mTvGroup;
    private AddDailySelfGroupAdapter mAdapter;
    private AddDailySelfPresenter mPresenter;
    private String mDefaultTitle = AppConstant.DEFAULT_DAILY_SELF_TV_GROUP;
    private String mContent;
    private DailySelf dailySelf;


    public static void start(Context context) {
        Intent start = new Intent(context, AddDailySelfActivity.class);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("随手记");
        setContentView(R.layout.activity_add_daily_self);
        initView();
    }

    private void initView() {
        mEtContent = findViewById(R.id.et_content);
        mRlvGroup = findViewById(R.id.rlv_group);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);
        mTvGroup = findViewById(R.id.tv_group);

        mAdapter = new AddDailySelfGroupAdapter(this, new ArrayList<AddDailySelfGroupRlvModel>());
        mRlvGroup.setLayoutManager(new GridLayoutManager(this, 3));
        mRlvGroup.setAdapter(mAdapter);
        mAdapter.setOnCheckListener(new AddDailySelfGroupAdapter.OnCheckListener() {
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
                    RxBus.get().post(new AddDailySelfActivityEvent(dailySelf));
                    finish();
                    return;
                }
                ToastHelper.shortToast("请完成对应信息");
            }
        });

        mPresenter = new AddDailySelfPresenter();
        mPresenter.subscribe(this);
        mPresenter.initGroup();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showGroup(List<AddDailySelfGroupRlvModel> data) {
        mAdapter.addData(data);
    }

    private boolean isOkEnable() {
        getAllText();
        if (TextUtils.isEmpty(mContent) || TextUtils.isEmpty(mDefaultTitle)) {
            return false;
        }
        dailySelf = new DailySelf();
        dailySelf.mIsFavorite = 0;
        dailySelf.mContent = mContent;
        dailySelf.mTimeStamp = new Date().getTime();
        dailySelf.mTvGroup = mDefaultTitle;

        return true;
    }

    private void getAllText() {
        mContent = mEtContent.getText().toString().trim();
    }
}
