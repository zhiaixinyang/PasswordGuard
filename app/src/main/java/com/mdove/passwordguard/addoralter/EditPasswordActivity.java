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
import com.mdove.passwordguard.addoralter.adapter.AddPasswordGroupAdapter;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.event.EditPasswordActivityEvent;
import com.mdove.passwordguard.addoralter.presenter.AddPasswordPresenter;
import com.mdove.passwordguard.addoralter.presenter.contract.AddPasswordContract;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.config.AppConstant;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/2/21.
 */

public class EditPasswordActivity extends BaseActivity implements AddPasswordContract.MvpView {
    private RecyclerView mRlvGroup;
    private EditText mEtUserName, mEtPassword, mEtTitle;
    private TextView mBtnCancel, mBtnOk, mTvGroup;
    private AddPasswordGroupAdapter mAdapter;
    private AddPasswordPresenter mPresenter;
    private String mDefaultTitle = AppConstant.DEFAULT_CHECK_GROUP_TITLE;
    private String mTitle, mUserName, mPassword;
    private String mOldTitle, mOldUserName, mOldPassword, mOldTvGroup;
    private Password mTmpPassword, mNeedEditPassword;
    public static final String ACTION_FROM_IS_EDIT = "action_from_is_edit";
    private static final String ACTION_KEY = "action_key";
    private static final String EXTRA_ACTION_EDIT_VIEW_MODEL_KEY = "extra_action_key";
    private static final String EXTRA_ACTION_EDIT_ITEM_POSITION_KEY = "extra_action_edit_item_position_key";
    private int mEditItemPosition;

    public static void start(Context context, ItemMainPasswordVM model, int position) {
        Intent start = new Intent(context, EditPasswordActivity.class);
        start.putExtra(ACTION_KEY, ACTION_FROM_IS_EDIT);
        start.putExtra(EXTRA_ACTION_EDIT_VIEW_MODEL_KEY, model);
        start.putExtra(EXTRA_ACTION_EDIT_ITEM_POSITION_KEY, position);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected boolean isNeedBaseMenu() {
        return true;
    }

    @Override
    protected void onClickMenuSend() {
        editPassword();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("修改账号信息");
        setContentView(R.layout.activity_edit_password);
        initView();
        handleAction(getIntent());
    }

    private void handleAction(Intent intent) {
        String actionKey = intent.getStringExtra(ACTION_KEY);
        if (TextUtils.isEmpty(actionKey)) {
            return;
        }
        switch (actionKey) {
            case ACTION_FROM_IS_EDIT: {
                ItemMainPasswordVM model = (ItemMainPasswordVM) intent.getSerializableExtra(EXTRA_ACTION_EDIT_VIEW_MODEL_KEY);
                mEditItemPosition = intent.getIntExtra(EXTRA_ACTION_EDIT_ITEM_POSITION_KEY, 0);
                mNeedEditPassword = model.mMainPasswordModel.password;
                initEdit(model);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void initEdit(ItemMainPasswordVM model) {
        mOldTitle = model.mTitle.get();
        mOldPassword = model.mPassword.get();
        mOldUserName = model.mUserName.get();
        mOldTvGroup = model.mTvGroup.get();
        mDefaultTitle = model.mTvGroup.get();

        mEtUserName.setText(mOldUserName);
        mEtPassword.setText(mOldPassword);
        mEtTitle.setText(mOldTitle);
        mTvGroup.setText(mOldTvGroup);
        mAdapter.initCheck(mOldTvGroup);
    }

    private void initView() {
        mEtPassword = findViewById(R.id.et_password);
        mEtTitle = findViewById(R.id.et_title);
        mEtUserName = findViewById(R.id.et_username);
        mRlvGroup = findViewById(R.id.rlv_group);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);
        mTvGroup = findViewById(R.id.tv_group);

        mAdapter = new AddPasswordGroupAdapter(this, new ArrayList<AddPasswordGroupRlvModel>());
        mRlvGroup.setLayoutManager(new GridLayoutManager(this, 3));
        mRlvGroup.setAdapter(mAdapter);
        mAdapter.setOnCheckListener(new AddPasswordGroupAdapter.OnCheckListener() {
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
                editPassword();
            }
        });

        mPresenter = new AddPasswordPresenter();
        mPresenter.subscribe(this);
        mPresenter.initGroup();
    }

    private void editPassword() {
        if (isOkEnable()) {
            RxBus.get().post(new EditPasswordActivityEvent(mTmpPassword, mNeedEditPassword, mEditItemPosition));
            finish();
            return;
        }
        ToastHelper.shortToast("请完成对应信息");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showGroup(List<AddPasswordGroupRlvModel> data) {
        mAdapter.addData(data);
    }

    private boolean isOkEnable() {
        getAllText();

        if (TextUtils.equals(mPassword, mOldPassword) && TextUtils.equals(mUserName, mOldUserName)
                && TextUtils.equals(mTitle, mOldTitle) && TextUtils.equals(mDefaultTitle, mOldTvGroup)) {
            return false;
        }
        mTmpPassword = new Password();
        mTmpPassword.mPassword = mNeedEditPassword.mPassword;
        mTmpPassword.mUserName = mNeedEditPassword.mUserName;
        mTmpPassword.mTitle = mNeedEditPassword.mTitle;
        mTmpPassword.mTimeStamp = mNeedEditPassword.mTimeStamp;
        mTmpPassword.isFavorite = mNeedEditPassword.isFavorite;
        mTmpPassword.isHide = mNeedEditPassword.isHide;
        mTmpPassword.isNew = 0;
        mTmpPassword.mTvGroup = mNeedEditPassword.mTvGroup;

        mNeedEditPassword.isNew = 1;
        mNeedEditPassword.mPassword = mPassword;
        mNeedEditPassword.mTimeStamp = new Date().getTime();
        mNeedEditPassword.mUserName = mUserName;
        mNeedEditPassword.mTitle = mTitle;
        mNeedEditPassword.mTvGroup = mDefaultTitle;

        return true;
    }

    private void getAllText() {
        mTitle = mEtTitle.getText().toString().trim();
        mUserName = mEtUserName.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
    }
}
