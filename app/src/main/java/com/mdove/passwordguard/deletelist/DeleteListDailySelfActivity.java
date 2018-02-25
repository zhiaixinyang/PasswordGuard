package com.mdove.passwordguard.deletelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.deletelist.adapter.DeleteListDailySelfAdapter;
import com.mdove.passwordguard.deletelist.adapter.DeleteListPasswordAdapter;
import com.mdove.passwordguard.deletelist.presenter.DeleteListDailySelfContract;
import com.mdove.passwordguard.deletelist.presenter.DeleteListDailySelfPresenter;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/25.
 */

public class DeleteListDailySelfActivity extends BaseActivity implements DeleteListDailySelfContract.MvpView {
    private RecyclerView mRlvDeleteList;
    private DeleteListDailySelfPresenter mPresenter;
    private DeleteListDailySelfAdapter mAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, DeleteListDailySelfActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_title_delete_list_daily_self);
        setContentView(R.layout.activity_delete_list_dailyself);

        mRlvDeleteList = findViewById(R.id.rlv_delete_list);
        mPresenter = new DeleteListDailySelfPresenter();
        mPresenter.subscribe(this);

        mAdapter = new DeleteListDailySelfAdapter(mPresenter);
        mRlvDeleteList.setLayoutManager(new LinearLayoutManager(this));
        mRlvDeleteList.setAdapter(mAdapter);

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
    public void deleteReturn(int position) {
        mAdapter.notifyDeleteReturn(position);
    }
}
