package com.mdove.passwordguard.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.config.AppLockConfig;
import com.mdove.passwordguard.databinding.ActivityMainBinding;
import com.mdove.passwordguard.lock.PatternSetActivity;
import com.mdove.passwordguard.lock.PatternUnlockActivity;
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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView {
    private ActivityMainBinding mBinding;
    private MainPresenter mPresenter;
    private RecyclerView mRlv;
    private MainAdapter mAdapter;

    public static final String EXTRA_ACTION_KEY = "extra_action_key";
    public static final String ACTION_LOCK_IS_SUC = "action_lock_is_suc";
    private String mAction;
    private boolean isLockFree = false;

    @StringDef(value = {ACTION_LOCK_IS_SUC})
    @Retention(RetentionPolicy.CLASS)
    public @interface MainAction {
    }

    public static void start(Context context, @MainAction String action) {
        Intent to = new Intent(context, MainActivity.class);
        to.putExtra(EXTRA_ACTION_KEY, action);
        context.startActivity(to);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        StatusBarUtil.setTranslucent(this);

        handleAction(getIntent());

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

    private void handleAction(Intent intent) {
        String action = intent.getStringExtra(EXTRA_ACTION_KEY);
        if (TextUtils.isEmpty(action)) {
            //判断是否启动手势锁
            if (AppLockConfig.isLock() && !TextUtils.isEmpty(AppLockConfig.getPassCode())) {
                PatternUnlockActivity.start(this, PatternUnlockActivity.ACTION_FORM_MAIN_TO_LOCK);
                finish();
            }
            return;
        }

        switch (action) {
            case ACTION_LOCK_IS_SUC: {

                break;
            }
            default: {
                break;
            }
        }
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
    public void onClickBtnLock() {
        PatternSetActivity.startWithAnim(this);
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
