package com.mdove.passwordguard.deletelist;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.deletelist.adapter.DeleteListDailySelfAdapter;
import com.mdove.passwordguard.deletelist.model.vm.DeleteDailySelfModelVM;
import com.mdove.passwordguard.deletelist.presenter.contract.DeleteListDailySelfContract;
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
        Intent start = new Intent(context, DeleteListDailySelfActivity.class);
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
        setTitle(R.string.activity_title_delete_list_daily_self);
        setContentView(R.layout.activity_delete_list_dailyself);

        mRlvDeleteList = findViewById(R.id.rlv_delete_list);

        mPresenter = new DeleteListDailySelfPresenter();
        mPresenter.subscribe(this);

        mAdapter = new DeleteListDailySelfAdapter(mPresenter);
        mAdapter.setOnChangeDataSizeListener(new OnChangeDataSizeListener() {
            @Override
            public void dataIsEmpty(boolean isEmpty) {
                setDataIsEmpty(isEmpty);
            }
        });

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

    @Override
    public void realDelete(int position) {
        mAdapter.notifyDeleteReturn(position);
    }

    @Override
    public void warningDeleteDialog(final DeleteDailySelfModelVM vm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteListDailySelfActivity.this);
        builder.setTitle("确定要永久删除？");
        builder.setMessage("真的要把这条记录删掉么？");
        builder.setNegativeButton("手滑了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("删！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.realDelete(vm);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
