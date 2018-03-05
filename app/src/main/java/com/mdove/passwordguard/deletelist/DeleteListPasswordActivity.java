package com.mdove.passwordguard.deletelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.deletelist.adapter.DeleteListPasswordAdapter;
import com.mdove.passwordguard.deletelist.presenter.DeleteListPasswordContract;
import com.mdove.passwordguard.deletelist.presenter.DeleteListPasswordPresenter;
import com.mdove.passwordguard.main.model.BaseMainModel;

import java.util.List;

/**
 * Created by MDove on 2018/2/14.
 */

public class DeleteListPasswordActivity extends BaseActivity implements DeleteListPasswordContract.MvpView {
    private RecyclerView mRlvDeleteList;
    private DeleteListPasswordPresenter mPresenter;
    private DeleteListPasswordAdapter mAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, DeleteListPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_title_delete_list_password);
        setContentView(R.layout.activity_delete_list_password);

        mRlvDeleteList = findViewById(R.id.rlv_delete_list);

        mPresenter = new DeleteListPasswordPresenter();
        mPresenter.subscribe(this);

        mAdapter = new DeleteListPasswordAdapter(mPresenter);
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
        if (data == null || data.size() == 0) {
            setDataIsEmpty(true);
        } else {
            mAdapter.setData(data);
            setDataIsEmpty(false);
        }
    }

    @Override
    public void deleteReturn(int position) {
        mAdapter.notifyDeleteReturn(position);
    }
}
