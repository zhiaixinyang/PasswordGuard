package com.mdove.passwordguard.mainoption;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.mainoption.adapter.AllMainOptionAdapter;
import com.mdove.passwordguard.mainoption.presenter.AllMainOptionPresenter;
import com.mdove.passwordguard.mainoption.presenter.contract.AllMainOptionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/2.
 */

public class AllMainOptionActivity extends BaseActivity implements AllMainOptionContract.MvpView {
    private AllMainOptionPresenter mPresenter;
    private AllMainOptionAdapter mAdapter;
    private List<MainOptionInfo> mData;
    private RecyclerView mRlvAllMainOption;

    public static void start(Context context) {
        Intent start = new Intent(context, AllMainOptionActivity.class);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_main_option);
        setTitle("更多操作");
        mRlvAllMainOption = findViewById(R.id.rlv_all_main_option);

        mData = new ArrayList<>();

        mPresenter = new AllMainOptionPresenter();
        mPresenter.subscribe(this);

        mAdapter = new AllMainOptionAdapter(this, mData, mPresenter);
        mRlvAllMainOption.setLayoutManager(new LinearLayoutManager(this));
        mRlvAllMainOption.setAdapter(mAdapter);

        mPresenter.initData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<MainOptionInfo> data) {
        mData = data;
        mAdapter.setData(mData);
    }
}
