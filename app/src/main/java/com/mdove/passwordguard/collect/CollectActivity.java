package com.mdove.passwordguard.collect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.collect.adapter.CollectAdapter;
import com.mdove.passwordguard.collect.presenter.CollectPresenter;
import com.mdove.passwordguard.collect.presenter.contract.CollectContract;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectActivity extends BaseActivity implements CollectContract.MvpView {
    private RecyclerView mRlvCollect;
    private CollectPresenter mPresenter;
    private CollectAdapter mAdapter;

    public static void start(Context context) {
        Intent start = new Intent(context, CollectActivity.class);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的收藏");
        setContentView(R.layout.activity_collect);
        mRlvCollect = findViewById(R.id.rlv_collect);

        mPresenter = new CollectPresenter();
        mPresenter.subscribe(this);

        mAdapter = new CollectAdapter(this, mPresenter);
        mAdapter.setOnChangeDataSizeListener(new OnChangeDataSizeListener() {
            @Override
            public void dataIsEmpty(boolean isEmpty) {
                setDataIsEmpty(isEmpty);
            }
        });

        mRlvCollect.setLayoutManager(new LinearLayoutManager(this));
        mRlvCollect.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<BaseMainModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void favoriteDailySelf(int position) {
        mAdapter.notifyItemChanged(position);
    }
}
