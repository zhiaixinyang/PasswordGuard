package com.mdove.passwordguard.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityLabelSettingBinding;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.task.adapter.AllSelfTaskLabelAdapter;
import com.mdove.passwordguard.task.adapter.LabelSettingAdapter;
import com.mdove.passwordguard.task.presenter.LabelSettingPresenter;
import com.mdove.passwordguard.task.presenter.contract.LabelSettingContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDOve on 2018/5/28.
 */

public class LabelSettingActivity extends BaseActivity implements LabelSettingContract.MvpView {
    private LabelSettingPresenter mPresenter;
    private LabelSettingAdapter mAdapter;
    private RecyclerView mRlv;
    private TextView mBtnSend;
    private EditText mEt;

    public static void start(Context context) {
        Intent start = new Intent(context, LabelSettingActivity.class);
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
        setTitle(getString(R.string.activity_title_label_setting));
        setContentView(R.layout.activity_label_setting);
        mRlv = findViewById(R.id.rlv_label);
        mBtnSend = findViewById(R.id.btn_send);
        mEt = findViewById(R.id.et_label);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEt.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.insertLabel(content);
                } else {
                    ToastHelper.shortToast("标签不能为空");
                }
            }
        });
        mPresenter = new LabelSettingPresenter();
        mPresenter.subscribe(this);

        mAdapter = new LabelSettingAdapter(this, mPresenter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData(List<DailyTaskLabelModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void deleteLabel(int position) {
        mAdapter.notifyDelete(position);
    }

    @Override
    public void insertLabel(int position) {
        mAdapter.notify(position);
    }
}
