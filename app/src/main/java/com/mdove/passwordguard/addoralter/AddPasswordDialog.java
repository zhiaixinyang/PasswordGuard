package com.mdove.passwordguard.addoralter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.DialogAddPasswordBinding;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.utils.SystemUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 18/2/10.
 */

public class AddPasswordDialog extends AppCompatDialog {
    private Context mContext;
    private DialogAddPasswordBinding mBinding;
    private String mTitle, mUserName, mPassword;
    private AddPasswordEvent mEvent;
    private MainGroupModel mModel;

    public AddPasswordDialog(Context context, MainGroupModel model) {
        super(context, R.style.UpgradeDialog);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_password,
                null, false);
        setContentView(mBinding.getRoot());

        mModel = model;
        mContext = context;

        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = getWindowWidth();
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        List<String> dataList = new ArrayList<>();
        for (MainGroupRlvModel model : mModel.mData) {
            dataList.add(model.mTvGroup);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, dataList);
        mBinding.spinner.setAdapter(adapter);
        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        if (TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            return false;
        }
        Password password = new Password();
        password.mPassword = mPassword;
        password.mUserName = mUserName;
        password.mTitle = mTitle;
        password.mTimeStamp = new Date().getTime();
        password.isNew = 1;

        mEvent = new AddPasswordEvent();
        mEvent.mPassword = password;
        return true;
    }

    private void getAllText() {
        mTitle = mBinding.etTitle.getText().toString().trim();
        mUserName = mBinding.etUsername.getText().toString().trim();
        mPassword = mBinding.etPassword.getText().toString().trim();
    }

    public static void showDialog(Context context, MainGroupModel model) {
        AddPasswordDialog dialog = new AddPasswordDialog(context, model);
        dialog.show();
    }

}
