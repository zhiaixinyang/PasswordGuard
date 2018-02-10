package com.mdove.passwordguard.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ActivityMainBinding;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.ui.AddPasswordDialog;
import com.mdove.passwordguard.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import com.mdove.passwordguard.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import com.mdove.passwordguard.utils.StatusBarUtil;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView {
    private ActivityMainBinding mBinding;
    private MainPresenter mPresenter;
    private RecyclerView mRlv;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        StatusBarUtil.setTranslucent(this);
        mRlv = mBinding.rlvMain;

        RxBus.get().register(this);
        mPresenter = new MainPresenter();
        mPresenter.subscribe(this);

        mAdapter = new MainAdapter(this, mPresenter);
        mRlv.setAdapter(mAdapter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(mRlv));

        mPresenter.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
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
    public void onClickBtnPassword() {
        AddPasswordDialog.showDialog(this);
    }

    @Override
    public void addPasswordSuc(String suc) {
        ToastHelper.shortToast(suc);
    }

    @Override
    public void notifyPasswordData(int position) {
        mAdapter.notifyPasswordData(position);
    }

    @Subscribe
    public void getAddPasswordInfo(AddPasswordEvent event) {
        mPresenter.addPassword(event.mPassword);
    }
}
