package com.mdove.passwordguard.alldata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.alldata.adapter.AllPasswordAdapter;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;
import com.mdove.passwordguard.alldata.presenter.contract.AllPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/6.
 */

public class AllPasswordActivity extends BaseActivity implements AllPasswordContract.MvpView {
    private RecyclerView mRlv;
    private AllPasswordAdapter mAdapter;
    private AllPasswordPresenter mPresenter;
    private List<AllPasswordModel> mData;

    public static void start(Context context) {
        Intent intent = new Intent(context, AllPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("所有账号记录");
        setContentView(R.layout.activity_all_password);

        mRlv = findViewById(R.id.rlv_all_password);

        mPresenter = new AllPasswordPresenter();
        mPresenter.subscribe(this);
        mData = new ArrayList<>();
        mAdapter = new AllPasswordAdapter(this, mData, mPresenter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<AllPasswordModel> data) {
        mData = data;
        mAdapter.setData(data);
    }

    @Override
    public void notifyBtnHide(int position) {
        mAdapter.notifyPosition(position);
    }
}
