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
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.model.event.AddPasswordActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.EditPasswordActivityEvent;
import com.mdove.passwordguard.base.listener.OnItemLongClickListener;
import com.mdove.passwordguard.deletelist.DeleteListPasswordActivity;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.group.model.event.GroupDeleteEvent;
import com.mdove.passwordguard.lock.config.AppLockConfig;
import com.mdove.passwordguard.databinding.ActivityMainBinding;
import com.mdove.passwordguard.lock.PatternSetActivity;
import com.mdove.passwordguard.lock.PatternUnlockActivity;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.event.AddGroupEvent;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.model.event.AlterPasswordEvent;
import com.mdove.passwordguard.addoralter.dialog.AddPasswordDialog;
import com.mdove.passwordguard.addoralter.dialog.AlterPasswordDialog;
import com.mdove.passwordguard.search.SearchRlvDialog;
import com.mdove.passwordguard.search.model.SearchRlvModel;
import com.mdove.passwordguard.ui.searchbox.SearchFragment;
import com.mdove.passwordguard.ui.searchbox.custom.IOnSearchClickListener;
import com.mdove.passwordguard.utils.AppUtils;
import com.mdove.passwordguard.utils.KeyBoardUtils;
import com.mdove.passwordguard.utils.StatusBarUtil;
import com.mdove.passwordguard.utils.ToastHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView,
        IOnSearchClickListener, View.OnClickListener {
    private ActivityMainBinding mBinding;
    private MainPresenter mPresenter;
    private RecyclerView mRlv;
    private MainAdapter mAdapter;

    private SearchFragment mSearchFragment;

    public static final String EXTRA_ACTION_KEY = "extra_action_key";
    public static final String ACTION_LOCK_IS_SUC = "action_lock_is_suc";
    private String mAction;
    private boolean isLockFree = false;

    @Override
    public void onClick(View v) {
        String content = mBinding.etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastHelper.shortToast("记录内容不能为空");
            return;
        }
        mPresenter.insertDailySelf(content);
        mBinding.etContent.setText("");
        KeyBoardUtils.closeKeyboard(this, mBinding.etContent);
    }

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
        mBinding.btnSend.setOnClickListener(this);

        RxBus.get().register(this);
        mSearchFragment = SearchFragment.newInstance();
        mSearchFragment.setOnSearchClickListener(this);
        mPresenter = new MainPresenter();
        mPresenter.subscribe(this);

        mAdapter = new MainAdapter(this, mPresenter);
        mAdapter.setOnLongClickListener(new OnItemLongClickListener<PasswordModel>() {
            @Override
            public void onItemLongClick(int position, PasswordModel object) {
                AlterPasswordDialog.showDialog(MainActivity.this, object, position);
            }
        });
        mRlv.setAdapter(mAdapter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
//        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(mRlv));

        mPresenter.initData();
        mPresenter.checkUpdate(AppUtils.getAPPVersionCodeFromAPP(this));
    }

    private void handleAction(Intent intent) {
        String action = intent.getStringExtra(EXTRA_ACTION_KEY);
        if (TextUtils.isEmpty(action)) {
            //判断是否启动手势锁
            if (AppLockConfig.isLockSwitchOn() && AppLockConfig.isLockSet() &&
                    !TextUtils.isEmpty(AppLockConfig.getPassCode())) {
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
    public void onClickBtnPassword(MainGroupModel model) {
        AddPasswordDialog.showDialog(this, model);
    }

    @Override
    public void onClickBtnLock() {
        PatternSetActivity.startWithAnim(this);
    }

    @Override
    public void onClickBtnSearch() {
        mSearchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
    }

    @Override
    public void searchReturn(List<Password> data, String error) {
        if (data == null) {
            ToastHelper.shortToast(error);
            return;
        }
        List<BaseMainModel> dataModel = new ArrayList<>();
        for (Password password : data) {
            dataModel.add(new SearchRlvModel(password));
        }
        new SearchRlvDialog(this, dataModel).show();
    }

    @Override
    public void addGroupSuc() {
        mAdapter.notifyAddGroup();
    }

    @Override
    public void deletePassword(int position) {
        mAdapter.notifyDeletePasswordData(position);
        ToastHelper.shortToast("删除成功");
    }

    @Override
    public void deleteDailySelf(int position) {
        mAdapter.notifyDeletePasswordData(position);
        ToastHelper.shortToast("撤回成功");
    }

    @Override
    public void alterPasswordSuc(int itemPosition, int newItemPosition) {
        mAdapter.notifyAddPasswordData(itemPosition);
        mAdapter.notifyAddPasswordData(newItemPosition);
    }

    @Override
    public void checkOrderSuc(List<BaseMainModel> data) {
        mAdapter.addBaseMainModelData(data);
    }

    @Override
    public void OnSearchClick(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            mPresenter.querySearch(keyword);
            return;
        }
        ToastHelper.shortToast("请输入搜索关键字");
    }

    @Override
    public void addPasswordSuc(String suc) {
        ToastHelper.shortToast(suc);
    }

    @Override
    public void notifyPasswordData(int position) {
        mAdapter.notifyAddPasswordData(position);
    }

    @Override
    public void notifyDailySelfData(int position) {
        mAdapter.notifyAddDailySelfData(position);
    }

    @Subscribe
    public void addPasswordInfo(AddPasswordEvent event) {
        mPresenter.addPassword(event.mPassword);
    }

    @Subscribe
    public void addPasswordActivityInfo(AddPasswordActivityEvent event) {
        mPresenter.addPassword(event.mPassword);
    }

    @Subscribe
    public void editPasswordActivityInfo(EditPasswordActivityEvent event) {
        mPresenter.alterPassword(event.alterPasswordModel, event.mEditItemPosition);
    }

    @Subscribe
    public void alterPasswordInfo(AlterPasswordEvent event) {
        mPresenter.alterPassword(event.mModel, event.mItemPosition);
    }

    @Subscribe
    public void deletePasswordReturn(DeletePasswordReturnEvent event) {
        mPresenter.deletePasswordReturn(event);
    }

    @Subscribe
    public void deleteDailySelfReturn(DeleteDailySelfReturnEvent event) {
        mPresenter.deleteDailySelfReturn(event);
    }

    @Subscribe
    public void groupDelete(GroupDeleteEvent event) {
        mPresenter.initData();
    }

    @Subscribe
    public void addGroupInfo(AddGroupEvent event) {
        mPresenter.addGroup(event.mTvGroup);
    }

    @Subscribe
    public void checkOrder(CheckOrderEvent event) {
        mPresenter.checkOrderPassword(event);
    }
}
