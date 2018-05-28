package com.mdove.passwordguard.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.mdove.passwordguard.task.adapter.SelfTaskAdapter;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.presenter.SelfTaskPresenter;
import com.mdove.passwordguard.task.presenter.contract.SelfTaskContract;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskActivity extends BaseActivity implements SelfTaskContract.MvpView {
    private EditText mEtContent;
    private TextView mBtnSend;
    private RecyclerView mRlv;
    private SelfTaskAdapter mAdapter;
    private SelfTaskPresenter mPresenter;
    private List<SelfTaskModel> mData;

    public static void start(Context context) {
        Intent start = new Intent(context, SelfTaskActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的工作线");
        setContentView(R.layout.activity_self_task);
        mEtContent = findViewById(R.id.et_content);
        mBtnSend = findViewById(R.id.btn_send);
        mRlv = findViewById(R.id.rlv_self_task);

        mPresenter = new SelfTaskPresenter();
        initRlv();
        mPresenter.subscribe(this);
        mPresenter.initData();

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEtContent.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.insertSelfTask(content);
                    mEtContent.setText("");
                    return;
                }
                ToastHelper.shortToast("怎么能添加一个空的工作内容");
            }
        });
    }

    private void initRlv() {
        mAdapter = new SelfTaskAdapter(this, mPresenter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void insertSelfTask(int position) {
        mAdapter.notifyInsertPosition(position);
    }

    @Override
    public void notifySelfTaskIsSuc(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifySelfTaskPriority(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifySelfSee(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void initData(List<SelfTaskModel> data) {
        mData = data;
        mAdapter.setData(data);
    }

    @Override
    public void onClickDelete(int position) {
        mAdapter.notifyDeleteSelfTask(position);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }
}
