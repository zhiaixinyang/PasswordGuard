package com.mdove.passwordguard.addoralter;

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
import com.mdove.passwordguard.addoralter.model.AlterPasswordModel;
import com.mdove.passwordguard.databinding.DialogAlterPasswordBinding;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.model.event.AlterPasswordEvent;
import com.mdove.passwordguard.utils.SystemUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;

/**
 * Created by MDove on 18/2/10.
 */

public class AlterPasswordDialog extends AppCompatDialog {
    private DialogAlterPasswordBinding mBinding;
    private String mTitle, mUserName, mPassword;
    private String mOldUserName, mOldPassword;
    private Password oldPassword;
    private AlterPasswordEvent mEvent;
    private int mItemPosition;

    public AlterPasswordDialog(Context context, PasswordModel password, int itemPosition) {
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

    private void initPassword(PasswordModel password) {
        mBinding.tvTitle.setText(password.mTitle);
        mTitle = password.mTitle;
        mBinding.etAlterUsername.setText(password.mUserName);
        mBinding.etAlterPassword.setText(password.mPassword);
        oldPassword = password.password;

        mOldPassword = password.mPassword;
        mOldUserName = password.mUserName;
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
                    dismiss();
                } else {
                    ToastHelper.shortToast("未进行任何修改");
                }
            }
        });
    }

    private boolean isOkEnable() {
        getAllText();
        if (TextUtils.equals(mOldUserName, mUserName) && TextUtils.equals(mOldPassword, mPassword)) {
            return false;
        }
        Password newPassword = new Password();
        newPassword.mPassword = mPassword;
        newPassword.mUserName = mUserName;
        newPassword.mTitle = mTitle;
        newPassword.mTimeStamp = new Date().getTime();
        newPassword.isNew = 1;
        oldPassword.isNew = 0;

        mEvent = new AlterPasswordEvent();
        mEvent.mItemPosition = mItemPosition;
        mEvent.mModel = new AlterPasswordModel(oldPassword, newPassword);
        RxBus.get().post(mEvent);

        return true;
    }

    private void getAllText() {
        mUserName = mBinding.etAlterUsername.getText().toString().trim();
        mPassword = mBinding.etAlterPassword.getText().toString().trim();
    }

    public static void showDialog(Context context, PasswordModel password, int itemPosition) {
        AlterPasswordDialog dialog = new AlterPasswordDialog(context, password, itemPosition);
        dialog.show();
    }
}
