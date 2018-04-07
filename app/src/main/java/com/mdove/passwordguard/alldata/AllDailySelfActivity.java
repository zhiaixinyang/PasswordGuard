package com.mdove.passwordguard.alldata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.alldata.adapter.AllDailySelfAdapter;
import com.mdove.passwordguard.alldata.adapter.AllPasswordAdapter;
import com.mdove.passwordguard.alldata.model.AllDailySelfModel;
import com.mdove.passwordguard.alldata.model.AllPasswordModel;
import com.mdove.passwordguard.alldata.presenter.AllDailySelfPresenter;
import com.mdove.passwordguard.alldata.presenter.AllPasswordPresenter;
import com.mdove.passwordguard.alldata.presenter.contract.AllDailySelfContract;
import com.mdove.passwordguard.alldata.presenter.contract.AllPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/7.
 */

public class AllDailySelfActivity extends BaseActivity implements AllDailySelfContract.MvpView {
    private RecyclerView mRlv;
    private AllDailySelfAdapter mAdapter;
    private AllDailySelfPresenter mPresenter;
    private List<AllDailySelfModel> mData;

    public static void start(Context context) {
        Intent intent = new Intent(context, AllDailySelfActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("所有随手记");
        setContentView(R.layout.activity_all_password);

        mRlv = findViewById(R.id.rlv_all_password);

        mPresenter = new AllDailySelfPresenter();
        mPresenter.subscribe(this);
        mData = new ArrayList<>();
        mAdapter = new AllDailySelfAdapter(this, mData, mPresenter);
        mAdapter.setOnChangeDataSizeListener(new OnChangeDataSizeListener() {
            @Override
            public void dataIsEmpty(boolean isEmpty) {
                setDataIsEmpty(isEmpty);
            }
        });
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<AllDailySelfModel> data) {
        mData = data;
        mAdapter.setData(data);
    }

    @Override
    public void notifyBtnHide(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifyBtnFavorite(int position) {
        mAdapter.notifyPosition(position);
    }
}
