package com.mdove.passwordguard.addoralter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.adapter.AddDailySelfGroupAdapter;
import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.event.AddDailySelfActivityEvent;
import com.mdove.passwordguard.addoralter.model.event.EditDailySelfActivityEvent;
import com.mdove.passwordguard.addoralter.presenter.AddDailySelfPresenter;
import com.mdove.passwordguard.addoralter.presenter.contract.AddDailySelfContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfActivity extends BaseActivity implements AddDailySelfContract.MvpView {
    private RecyclerView mRlvGroup;
    private EditText mEtContent;
    private TextView mBtnCancel, mBtnOk, mTvGroup;
    private AddDailySelfGroupAdapter mAdapter;
    private AddDailySelfPresenter mPresenter;
    private String mDefaultTitle = AppConstant.DEFAULT_DAILY_SELF_TV_GROUP;
    private String mContent;
    private DailySelf dailySelf;
    private static final String EXTRA_EDIT_DAILY_SELF = "extra_edit_daily_self";
    private static final String EXTRA_EDIT_ITEM_POSITION_KEY = "extra_edit_item_position_key";
    private ItemMainDailySelfVM mEditDailySelfVM;
    private DailySelf mOldDailySelf;
    private int mEditPosition;
    private boolean isEdit = false;

    public static void start(Context context) {
        Intent start = new Intent(context, AddDailySelfActivity.class);
        context.startActivity(start);
    }

    public static void startEdit(Context context, ItemMainDailySelfVM itemMainDailySelfVM, int position) {
        Intent start = new Intent(context, AddDailySelfActivity.class);
        start.putExtra(EXTRA_EDIT_DAILY_SELF, itemMainDailySelfVM);
        start.putExtra(EXTRA_EDIT_ITEM_POSITION_KEY, position);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("随手记");
        setContentView(R.layout.activity_add_daily_self);
        handleAction(getIntent());
        initView();
        initData();
    }

    private void initData(){
        mEtContent.setText(mContent);
        mTvGroup.setText(mDefaultTitle);
        mAdapter.initCheck(mDefaultTitle);
    }

    private void handleAction(Intent intent) {
        if (intent == null) {
            return;
        }
        mEditDailySelfVM = (ItemMainDailySelfVM) intent.getSerializableExtra(EXTRA_EDIT_DAILY_SELF);
        mEditPosition = intent.getIntExtra(EXTRA_EDIT_ITEM_POSITION_KEY, 0);
        if (mEditDailySelfVM == null) {
            return;
        }
        isEdit = true;
        mOldDailySelf = mEditDailySelfVM.mDailySelf;
        mContent = mEditDailySelfVM.mContent.get();
        mDefaultTitle = mEditDailySelfVM.mTvGroup.get();
    }

    private void initView() {
        mEtContent = findViewById(R.id.et_content);
        mRlvGroup = findViewById(R.id.rlv_group);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);
        mTvGroup = findViewById(R.id.tv_group);

        mAdapter = new AddDailySelfGroupAdapter(this, new ArrayList<AddDailySelfGroupRlvModel>());
        mRlvGroup.setLayoutManager(new GridLayoutManager(this, 3));
        mRlvGroup.setAdapter(mAdapter);
        mAdapter.setOnCheckListener(new AddDailySelfGroupAdapter.OnCheckListener() {
            @Override
            public void onCheck(boolean isCheck, String selectTitle) {
                if (!isCheck) {
                    mTvGroup.setText("");
                    mDefaultTitle = null;
                    return;
                }
                mTvGroup.setText(selectTitle);
                mDefaultTitle = selectTitle;
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOkEnable()) {
                    if (!isEdit) {
                        RxBus.get().post(new AddDailySelfActivityEvent(dailySelf));
                        finish();
                        return;
                    } else {
                        RxBus.get().post(new EditDailySelfActivityEvent(mOldDailySelf, mEditPosition));
                        finish();
                        return;
                    }
                }
                ToastHelper.shortToast("请完成对应信息");
            }
        });

        mPresenter = new AddDailySelfPresenter();
        mPresenter.subscribe(this);
        mPresenter.initGroup();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showGroup(List<AddDailySelfGroupRlvModel> data) {
        mAdapter.addData(data);
    }

    private boolean isOkEnable() {
        getAllText();
        if (TextUtils.isEmpty(mContent) || TextUtils.isEmpty(mDefaultTitle)) {
            return false;
        }

        if (!isEdit) {
            dailySelf = new DailySelf();
            dailySelf.mIsFavorite = 0;
            dailySelf.mContent = mContent;
            dailySelf.mTimeStamp = new Date().getTime();
            dailySelf.mTvGroup = mDefaultTitle;
        } else {
            mOldDailySelf.mContent = mContent;
            mOldDailySelf.mTimeStamp = new Date().getTime();
            mOldDailySelf.mTvGroup = mDefaultTitle;
        }

        return true;
    }

    private void getAllText() {
        mContent = mEtContent.getText().toString().trim();
    }
}
