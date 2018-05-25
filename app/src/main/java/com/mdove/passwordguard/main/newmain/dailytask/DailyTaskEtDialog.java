package com.mdove.passwordguard.main.newmain.dailytask;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.DialogDailyTaskEtBinding;
import com.mdove.passwordguard.databinding.DialogUpdateBinding;
import com.mdove.passwordguard.update.UpdateAppService;
import com.mdove.passwordguard.utils.DensityUtil;
import com.mdove.passwordguard.utils.SystemUtils;

/**
 * Created by MDove on 18/2/14.
 */
public class DailyTaskEtDialog extends AppCompatDialog {
    private DialogDailyTaskEtBinding binding;
    private Context mContext;

    public DailyTaskEtDialog(Context context) {
        super(context, R.style.UpgradeDialog);
        mContext = context;
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_daily_task_et,
                null, false);
        setContentView(binding.getRoot());

        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = DensityUtil.getScreenWidth(mContext);
        paramsWindow.height = DensityUtil.getScreenHeight(mContext);

        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
