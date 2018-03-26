package com.mdove.passwordguard.lock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.lock.config.AppLockConfig;
import com.mdove.passwordguard.databinding.ActivityPatternSetBinding;
import com.mdove.passwordguard.lock.utils.AnimUtils;
import com.mdove.passwordguard.ui.patternlockview.PatternLockView;
import com.mdove.passwordguard.ui.patternlockview.listener.PatternLockViewListener;
import com.mdove.passwordguard.ui.patternlockview.utils.PatternLockUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;


/**
 * Created by MDove on 2018/2/11.
 */

public class PatternSetActivity extends AppCompatActivity {
    private static final String EXTRA_DATA = "action";
    private String savedCode = "";

    private enum PatternSetState {
        SET,
        CONFIRM,
    }

    private ActivityPatternSetBinding binding;
    private PatternSetState state = PatternSetState.SET;
    private static Handler handler = new Handler();

    public static void startWithAnim(Activity context) {
        Intent starter = new Intent(context, PatternSetActivity.class);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PatternSetActivity.class);
        context.startActivity(starter);
    }

    public static void startForReset(Activity context, int requestCode) {
        Intent starter = new Intent(context, PatternSetActivity.class);
        starter.putExtra(EXTRA_DATA, true);
        context.startActivityForResult(starter, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pattern_set);
        StatusBarUtils.setTransparent(this);

        initSwitch();

        final boolean isReset = getIntent().getBooleanExtra(EXTRA_DATA, false);
        if (isReset) {
            binding.hint.setText(R.string.lock_forget_set_new_hint);
        }

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = PatternSetState.SET;
                binding.patternLockView.clearPattern();
                binding.hint.setText(getResources().getString(R.string.lock_pattern_set_hint));
                binding.btnReset.setVisibility(View.GONE);
                AppLockConfig.setPassCode("");
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
                if (state == PatternSetState.SET) {
                    if (code.length() < 4) {
                        AnimUtils.startTextSetAnim(binding.hint, R.string.lock_pattern_set_error_hint);
                        binding.patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                        AnimUtils.tada(binding.lockIcon).start();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.patternLockView.clearPattern();
                            }
                        }, 300);
                    } else {
                        binding.btnReset.setVisibility(View.VISIBLE);
                        state = PatternSetState.CONFIRM;
                        AnimUtils.startTextSetAnim(binding.hint, R.string.lock_pattern_confirm);

                        savedCode = code;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.patternLockView.clearPattern();
                            }
                        }, 300);
                    }
                } else {
                    if (!code.equals(savedCode)) {
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
                        AppLockConfig.setPassCode(savedCode);
                        AppLockConfig.setLockSet(true);
                        if (isReset) {
                            setResult(RESULT_OK);
                        } else {
//                            LockAppListActivity.startFromSet(PatternSetActivity.this);
                        }
                        finish();
                    }
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void initSwitch() {
        boolean isSwitchOn = AppLockConfig.isLockSwitchOn();
        if (isSwitchOn) {
            binding.switchIsOpenLock.setChecked(true);
            binding.tvStrSwitch.setText(getString(R.string.lock_string_switch_on));
        } else {
            binding.switchIsOpenLock.setChecked(false);
            binding.tvStrSwitch.setText(getString(R.string.lock_string_switch_off));
        }

        binding.switchIsOpenLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.tvStrSwitch.setText(getString(R.string.lock_string_switch_on));
                    AppLockConfig.setLockSwitchOn(true);
                } else {
                    binding.tvStrSwitch.setText(getString(R.string.lock_string_switch_off));
                    AppLockConfig.setLockSwitchOn(false);
                }
            }
        });
    }


}
