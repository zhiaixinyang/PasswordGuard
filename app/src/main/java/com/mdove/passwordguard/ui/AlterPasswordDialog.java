package com.mdove.passwordguard.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.DialogAddPasswordBinding;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.model.event.AlterPasswordEvent;
import com.mdove.passwordguard.utils.SystemUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;

/**
 * Created by MDove on 18/2/10.
 */

public class AlterPasswordDialog extends AppCompatDialog {
    private DialogAddPasswordBinding mBinding;
    private String mTitle, mUserName, mPassword;
    private AlterPasswordEvent mEvent;
    private int mItemPosition;

    public AlterPasswordDialog(Context context, Password password, int itemPosition) {
        super(context, R.style.UpgradeDialog);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_alter_password,
                null, false);
        setContentView(mBinding.getRoot());
        mItemPosition = itemPosition;

        initPassword(password);

        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = getWindowWidth();
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    private void initPassword(Password password) {
        mBinding.tvTitle.setText(password.mTitle);
        mBinding.etUsername.setText(password.mUserName);
        mBinding.etPassword.setText(password.mPassword);
    }

    protected int getWindowWidth() {
        float percent = 0.9f;
        WindowManager wm = this.getWindow().getWindowManager();
        int screenWidth = SystemUtils.getScreenWidth(wm);
        int screenHeight = SystemUtils.getScreenHeight(wm);
        return (int) (screenWidth > screenHeight
                ? screenHeight * percent
                : screenWidth * percent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOkEnable()) {
                    RxBus.get().post(mEvent);
                    dismiss();
                } else {
                    ToastHelper.shortToast("请完整填写信息");
                }
            }
        });
    }

    private boolean isOkEnable() {
        getAllText();
        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            return false;
        }
        Password password = new Password();
        password.mPassword = mPassword;
        password.mUserName = mUserName;
        password.mTitle = mTitle;
        password.mTimeStamp = new Date().getTime();

        mEvent = new AlterPasswordEvent();
        mEvent.mItemPosition = mItemPosition;
        mEvent.mPassword = password;
        return true;
    }

    private void getAllText() {
        mTitle = mBinding.etTitle.getText().toString().trim();
        mUserName = mBinding.etUsername.getText().toString().trim();
        mPassword = mBinding.etPassword.getText().toString().trim();
    }

    public static void showDialog(Context context, Password password, int itemPosition) {
        AlterPasswordDialog dialog = new AlterPasswordDialog(context, password, itemPosition);
        dialog.show();
    }

}
