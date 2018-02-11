package com.mdove.passwordguard.lock;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.config.AppLockConfig;
import com.mdove.passwordguard.databinding.ActivityPatternUnlockBinding;
import com.mdove.passwordguard.lock.utils.AnimUtils;
import com.mdove.passwordguard.main.MainActivity;
import com.mdove.passwordguard.ui.patternlockview.PatternLockView;
import com.mdove.passwordguard.ui.patternlockview.listener.PatternLockViewListener;
import com.mdove.passwordguard.ui.patternlockview.utils.PatternLockUtils;
import com.mdove.passwordguard.utils.StatusBarUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by MDove on 2018/2/11.
 */

public class PatternUnlockActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_RESET = 333;

    private ActivityPatternUnlockBinding binding;
    private static Handler handler = new Handler();
    private static String EXTRA_ACTION = "extra_action";
    private String mExtraAction;
    private android.support.v4.os.CancellationSignal cancellationSignal;

    public static void start(Activity activity, String action) {
        Intent starter = new Intent(activity, PatternUnlockActivity.class);
        starter.putExtra(EXTRA_ACTION, action);
        activity.startActivity(starter);
    }

    public static void start(Activity activity, int requestCode) {
        Intent starter = new Intent(activity, PatternUnlockActivity.class);
        activity.startActivityForResult(starter, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pattern_unlock);
        StatusBarUtil.setTransparent(this);
        mExtraAction = getIntent().getStringExtra(EXTRA_ACTION);

//        switch (mExtraAction) {
//            case MainActivity.ACTION_FORM_MAIN_TO_LOCK: {
//                binding.btnBack.setVisibility(View.GONE);
//                return;
//            }
//            default: {
//                binding.btnBack.setVisibility(View.VISIBLE);
//                return;
//            }
//        }

        if (AppLockConfig.isAuthWithFinger()) {
            binding.btnFinger.setVisibility(View.VISIBLE);
        } else {
            binding.btnFinger.setVisibility(View.GONE);
        }

        binding.btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        binding.patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {
            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                String code = PatternLockUtils.patternToString(binding.patternLockView, pattern);
                if (!code.equals(AppLockConfig.getPassCode())) {
                    binding.patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                    AnimUtils.tada(binding.lockIcon).start();
                    AnimUtils.startTextSetAnim(binding.hint, R.string.lock_pattern_mismatch_hint);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.patternLockView.clearPattern();
                        }
                    }, 300);
                } else {
                    binding.patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }, 300);
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.LockDialogCustom);
        builder.setTitle(R.string.lock_forget_password)
                .setMessage(R.string.lock_forget_password_hint)
                .setPositiveButton(R.string.lock_login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.lock_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    private void authFinger() {
        cancellationSignal = new android.support.v4.os.CancellationSignal();
        FingerprintManagerCompat manager = FingerprintManagerCompat.from(this);
        manager.authenticate(null, 0, cancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                super.onAuthenticationError(errMsgId, errString);
                if (errMsgId == FingerprintManager.FINGERPRINT_ERROR_CANCELED) {
                    return;
                }
                Toast.makeText(PatternUnlockActivity.this, errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                super.onAuthenticationHelp(helpMsgId, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        }, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppLockConfig.isAuthWithFinger()) {
            authFinger();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }
}
