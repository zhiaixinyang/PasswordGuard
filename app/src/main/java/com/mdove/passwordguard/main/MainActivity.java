package com.mdove.passwordguard.main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.StringDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.model.event.AddDailySelfActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.AddPasswordActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.EditDailySelfActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.EditPasswordActivityEvent;
import com.mdove.passwordguard.alldata.model.event.AllDailySelfFavoriteEvent;
import com.mdove.passwordguard.alldata.model.event.AllDailySelfHideEvent;
import com.mdove.passwordguard.alldata.model.event.AllPasswordFavoriteEvent;
import com.mdove.passwordguard.alldata.model.event.AllPasswordHideEvent;
import com.mdove.passwordguard.base.listener.OnItemLongClickListener;
import com.mdove.passwordguard.collect.model.event.CollectDailySelfEvent;
import com.mdove.passwordguard.collect.model.event.CollectPasswordEvent;
import com.mdove.passwordguard.deletelist.model.event.DeleteDailySelfReturnEvent;
import com.mdove.passwordguard.deletelist.model.event.DeletePasswordReturnEvent;
import com.mdove.passwordguard.group.model.event.GroupDeleteEvent;
import com.mdove.passwordguard.lock.config.AppLockConfig;
import com.mdove.passwordguard.databinding.ActivityMainBinding;
import com.mdove.passwordguard.lock.PatternSetActivity;
import com.mdove.passwordguard.lock.PatternUnlockActivity;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainPasswordModel;
import com.mdove.passwordguard.main.model.event.AddGroupEvent;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.event.DailyTaskScrollEvent;
import com.mdove.passwordguard.main.model.event.HideItemMainEvent;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.main.presenter.contract.MainContract;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.model.event.AlterPasswordEvent;
import com.mdove.passwordguard.addoralter.dialog.AddPasswordDialog;
import com.mdove.passwordguard.addoralter.dialog.AlterPasswordDialog;
import com.mdove.passwordguard.search.SearchResultActivity;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.event.SelfTaskClickDeleteEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickEditEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickPriorityEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSeeEvent;
import com.mdove.passwordguard.task.model.event.SelfTaskClickSucEvent;
import com.mdove.passwordguard.addoralter.dialog.AddDailySelfFragment;
import com.mdove.passwordguard.ui.searchbox.SearchFragment;
import com.mdove.passwordguard.ui.searchbox.custom.CircularRevealAnim;
import com.mdove.passwordguard.ui.searchbox.custom.IOnAddDailySelfClickListener;
import com.mdove.passwordguard.ui.searchbox.custom.IOnSearchClickListener;
import com.mdove.passwordguard.utils.AppUtils;
import com.mdove.passwordguard.utils.DensityUtil;
import com.mdove.passwordguard.utils.KeyBoardUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView,
        IOnSearchClickListener, IOnAddDailySelfClickListener, View.OnClickListener {
    private ActivityMainBinding mBinding;
    private MainPresenter mPresenter;
    private RecyclerView mRlv;
    private MainAdapter mAdapter;

    private SearchFragment mSearchFragment;
    private AddDailySelfFragment mAddDailySelfFragment;

    public static final String EXTRA_ACTION_KEY = "extra_action_key";
    public static final String ACTION_LOCK_IS_SUC = "action_lock_is_suc";
    public static final int TOOLBAR_HEIGHT = DensityUtil.getScreenHeight(App.getAppContext()) / 5;
    private String mAction;
    private boolean isLockFree = false;
    private TextView mTitle;
    private RelativeLayout mToolbar;
    private int mHasDy;

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

    public static void start(Context context) {
        Intent start = new Intent(context, MainActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
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
        StatusBarUtils.setStatusBarTransparentCompat(this);

        handleAction(getIntent());

        mRlv = mBinding.rlvMain;

        mBinding.btnSend.setOnClickListener(this);
        mToolbar = mBinding.toolbar;
        mTitle = mBinding.tvTitle;
        mBinding.statusBar.setHeight(StatusBarUtils.getStatusBarHeight(this));

        RxBus.get().register(this);
        mSearchFragment = SearchFragment.newInstance();
        mSearchFragment.setOnSearchClickListener(this);
        mAddDailySelfFragment = AddDailySelfFragment.newInstance();
        mAddDailySelfFragment.setOnAddDailySelfClickListener(this);

        mPresenter = new MainPresenter();
        mPresenter.subscribe(this);
        mBinding.setActionHandler(mPresenter);

        mAdapter = new MainAdapter(this, mPresenter);
        mAdapter.setOnLongClickListener(new OnItemLongClickListener<MainPasswordModel>() {
            @Override
            public void onItemLongClick(int position, MainPasswordModel object) {
                AlterPasswordDialog.showDialog(MainActivity.this, object, position);
            }
        });

        mRlv.setAdapter(mAdapter);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
//        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(mRlv));

        mPresenter.initData();
        mPresenter.checkUpdate(AppUtils.getAPPVersionCodeFromAPP(this));

        mRlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    mBinding.btnAdd.hide();
                } else {
                    mBinding.btnAdd.show();
                }
                mHasDy += dy;
                if (mHasDy <= 0 || !mRlv.canScrollVertically(-1)) {   //mRlv.canScrollVertically(-1)返回false表示到顶部了
                    mTitle.setTextColor(Color.argb(0, 255, 255, 255));
                    mToolbar.setBackgroundColor(Color.argb(0, 39, 40, 81));
                    isShowSearchBtn(false);
                } else if (mHasDy > 0 && mHasDy <= TOOLBAR_HEIGHT) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    isShowSearchBtn(false);
                    float scale = (float) mHasDy / TOOLBAR_HEIGHT;
                    float alpha = (255 * scale);
                    mTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                    mToolbar.setBackgroundColor(Color.argb((int) alpha, 39, 40, 81));
                } else if (mHasDy > TOOLBAR_HEIGHT) {    //滑动到banner下面设置普通颜色
                    mToolbar.setBackgroundColor(Color.argb(255, 39, 40, 81));
                    mTitle.setTextColor(Color.argb(255, 255, 255, 255));
                    isShowSearchBtn(true);
                }
            }
        });
    }

    private void isShowSearchBtn(boolean isShow) {
        if (isShow) {
            mBinding.btnSearch.setVisibility(View.VISIBLE);
            mBinding.btnSearch.setClickable(true);
        } else {
            mBinding.btnSearch.setVisibility(View.GONE);
            mBinding.btnSearch.setClickable(false);
        }
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
    public void onShowGuide() {
        mAdapter.showCommonGuide();
    }

    @Override
    public void onClickBtnSearch() {
        mSearchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
    }

    @Override
    public void onClickBtnAddDailySelf() {
        mAddDailySelfFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
    }

    @Override
    public void searchReturn(final List<BaseMainModel> data, String error) {
        if (data == null) {
            ToastHelper.shortToast(error);
            return;
        }
        mBinding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                SearchResultActivity.start(MainActivity.this, data);
            }
        }, CircularRevealAnim.ANIM_DURATION);
    }

    @Override
    public void addGroupSuc() {
        mAdapter.notifyAddGroup();
    }

    @Override
    public void deletePassword(int position) {
        mAdapter.notifyDeletePosition(position);
        ToastHelper.shortToast("删除成功");
    }

    @Override
    public void deleteDailySelf(int position) {
        mAdapter.notifyDeletePosition(position);
        ToastHelper.shortToast("撤回成功");
    }

    @Override
    public void alterPasswordSuc(int itemPosition, int newItemPosition) {
        mAdapter.notifyPosition(itemPosition);
//        mAdapter.notifyPosition(newItemPosition);
    }

    @Override
    public void alterDailySelfSuc(int itemPosition) {
        mAdapter.notifyPosition(itemPosition);
    }

    @Override
    public void checkOrderSuc(List<BaseMainModel> data) {
        mAdapter.addBaseMainModelData(data);
    }

    @Override
    public void onClickTaskSuc(int position) {
        mAdapter.notifySelfTaskClickSuc(position);
    }

    @Override
    public void insertItemMainSelfTask(SelfTaskModel model) {
        mAdapter.insertItemMainSelfTask(model);
    }

    @Override
    public void onClickBtnHideGroup(int position) {
        mAdapter.notifyDeletePosition(position);
    }

    @Override
    public void onClickBtnHideSearch(int position) {
        mAdapter.notifyDeletePosition(position);
    }

    @Override
    public void onClickBtnHideOption(int position) {
        mRlv.scrollToPosition(0);
        mAdapter.notifyPosition(position);
    }

    @Override
    public void onClickBtnHideDailyPlan(int position) {
        mRlv.scrollToPosition(0);
        mAdapter.notifyPosition(position);
    }

    @Override
    public void onClickBtnHideDailySelf(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void onClickBtnHideTimeTop(int position) {
        mAdapter.notifyDeletePosition(position);
    }

    @Override
    public void notifyTaskSelf(int position) {
        mAdapter.notifyTaskSelf(position);
    }

    @Override
    public void notifyTaskSelfSee(int position) {
        mAdapter.notifyTaskSelfSee(position);
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
    public void OnAddDailySelfClick(String content) {
        if (TextUtils.isEmpty(content)) {
            ToastHelper.shortToast("记录内容不能为空");
            return;
        }
        mPresenter.insertDailySelf(content);
    }

    @Override
    public void addPasswordSuc(String suc) {
        ToastHelper.shortToast(suc);
    }

    @Override
    public void notifyPasswordData(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void notifyDailySelfData(int position) {
        mAdapter.notifyAddDailySelfData(position);
    }

    @Override
    public void notifyBtnHide(int position) {
        mAdapter.notifyDeletePosition(position);
    }

    @Override
    public void notifyBtnNoHide(int position) {
        mAdapter.notifyPosition(position);
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
    public void addDailySelfActivityInfo(AddDailySelfActivityEvent event) {
        mPresenter.addDailySelf(event.mDailySelf);
    }

    @Subscribe
    public void editPasswordActivityInfo(EditPasswordActivityEvent event) {
        mPresenter.alterPassword(event.alterPasswordModel, event.mEditItemPosition);
    }

    @Subscribe
    public void editDailySelfActivityInfo(EditDailySelfActivityEvent event) {
        mPresenter.alterDailySelf(event.mAlterDailySelfModel, event.mEditItemPosition);
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

    @Subscribe
    public void selfTaskClickSuc(SelfTaskClickSucEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickSuc(event.mId, event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickSee(SelfTaskClickSeeEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickSee(event.mSelfTaskModel);
    }

    @Subscribe
    public void selfTaskClickDelete(SelfTaskClickDeleteEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickDelete(event.mId);
    }

    @Subscribe
    public void selfTaskClickPriority(SelfTaskClickPriorityEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickPriority(event.mId, event.mSelfTaskModel.mPriority);
    }

    @Subscribe
    public void selfTaskClickEdit(SelfTaskClickEditEvent event) {
        //从SelfTaskActivity post 过来的notify
        mAdapter.notifyEventSelfTaskClickEdit(event.mId, event.mSelfTaskModel.mTask);
    }

    @Subscribe
    public void collectDailySelf(CollectDailySelfEvent event) {
        mAdapter.notifyEventCollectDailySelf(event.mId, event.isFavorite);
    }

    @Subscribe
    public void collectPassword(CollectPasswordEvent event) {
        mAdapter.notifyEventCollectPassword(event.mId, event.isFavorite);
    }

    @Subscribe
    public void hideMainItem(HideItemMainEvent event) {
        //TODO 暂时不做特别处理，有待优化
        mPresenter.initData();
    }

    @Subscribe
    public void hidePassword(AllPasswordHideEvent event) {
        mPresenter.postAllPasswordHide(event.mId, event.mIsHide);
    }

    @Subscribe
    public void hideDailySelf(AllDailySelfHideEvent event) {
        mPresenter.postAllDailySelfHide(event.mId, event.mIsHide);
    }

    @Subscribe
    public void favoriteAllDailySelf(AllDailySelfFavoriteEvent event) {
        mPresenter.postAllDailySelfFavorite(event.mId, event.mIsFavorite);
    }

    @Subscribe
    public void favoriteAllPassword(AllPasswordFavoriteEvent event) {
        mPresenter.postAllPasswordFavorite(event.mId, event.mIsFavorite);
    }

    @Subscribe
    public void dailyTaskScroll(DailyTaskScrollEvent event) {
    }
}
