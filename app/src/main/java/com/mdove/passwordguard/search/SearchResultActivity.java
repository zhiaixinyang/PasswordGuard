package com.mdove.passwordguard.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.search.adapter.SearchResultAdapter;
import com.mdove.passwordguard.search.presenter.SearchResultPresenter;
import com.mdove.passwordguard.search.presenter.contract.SearchResultContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/3/23.
 */

public class SearchResultActivity extends BaseActivity implements SearchResultContract.MvpView {
    private RecyclerView mRlv;
    private static final String EXTRA_LIST = "EXTRA_LIST";
    private List<BaseMainModel> mData;
    private SearchResultPresenter mPresenter;
    private SearchResultAdapter mAdapter;

    public static void start(Context context, List<BaseMainModel> data) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(EXTRA_LIST, (Serializable) data);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("搜索结果");
        handleIntent(getIntent());
        setContentView(R.layout.activity_search_result);
        mRlv = findViewById(R.id.rlv_search);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        mData = (List<BaseMainModel>) intent.getSerializableExtra(EXTRA_LIST);
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mPresenter = new SearchResultPresenter();
        mAdapter = new SearchResultAdapter(mData, mPresenter);
        mPresenter.subscribe(this);
        mPresenter.initData(mData);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void notifyDailySelfData(int position) {
        mAdapter.notifyFavoriteDailySelfData(position);
    }
}
