package com.mdove.passwordguard.main;

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
import com.mdove.passwordguard.databinding.DialogAddGroupBinding;
import com.mdove.passwordguard.databinding.DialogUpdateBinding;
import com.mdove.passwordguard.main.model.event.AddGroupEvent;
import com.mdove.passwordguard.model.event.AddPasswordEvent;
import com.mdove.passwordguard.update.UpdateAppService;
import com.mdove.passwordguard.utils.SystemUtils;
import com.mdove.passwordguard.utils.ToastHelper;

/**
 * Created by MDove on 2018/2/16.
 */

public class AddGroupDialog extends AppCompatDialog {
    private DialogAddGroupBinding binding;
    private String mAppUrl;
    private float star = 0;
    private Context mContext;

    public AddGroupDialog(Context context) {
        super(context, R.style.UpgradeDialog);
        mContext = context;
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_add_group,
                null, false);
        setContentView(binding.getRoot());
        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = getWindowWidth();
        setCancelable(true);
        setCanceledOnTouchOutside(false);
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

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tvGroup = binding.etGroup.getText().toString().trim();
                if (TextUtils.isEmpty(tvGroup)) {
                    ToastHelper.shortToast("分组名暂不能为空");
                    return;
                }
                dismiss();
                AddGroupEvent event = new AddGroupEvent();
                event.mTvGroup = tvGroup;
                RxBus.get().post(event);
            }
        });
    }
}
