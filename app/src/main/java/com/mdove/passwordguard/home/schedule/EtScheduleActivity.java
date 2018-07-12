package com.mdove.passwordguard.home.schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityEtScheduleBinding;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.home.constant.TimeConstant;
import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.ettodayplan.model.event.SetTimeEvent;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.schedule.model.handler.EtScheduleHandler;
import com.mdove.passwordguard.home.schedule.presenter.EtSchedulePresenter;
import com.mdove.passwordguard.home.schedule.presenter.SchedulePresenter;
import com.mdove.passwordguard.home.schedule.presenter.contract.EtScheduleContract;
import com.mdove.passwordguard.home.schedule.presenter.contract.ScheduleContract;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelConstant;
import com.mdove.passwordguard.ui.renkstar.BubbleSeekBar;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtScheduleActivity extends BaseActivity implements EtScheduleContract.MvpView {
    public static final String EXTRA_INTENT_TYPE = "extra_intent_type";
    public static final String EXTRA_INTENT_TYPE_TODAY_ID = "extra_intent_type_today_id";
    public static final int INTENT_TYPE_ADD_SCHEDULE = 0;
    public static final int INTENT_TYPE_SCHEDULE = 1;

    private ActivityEtScheduleBinding mBinding;
    public int mSelectStartHour = TimeConstant.DEFAUT_START_HOUR, mSelectStartMin = TimeConstant.DEFAUT_START_MIN;
    public int mSelectEndHour = TimeConstant.DEFAUT_END_HOUR, mSelectEndMin = TimeConstant.DEFAUT_END_MIN;
    private int mImportant = TimeConstant.DEFAUT_IMPORTANT, mUrgent = TimeConstant.DEFAUT_URGENT;
    private EtSchedulePresenter mPresenter;
    private boolean isAddStatus = true;

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    public static void start(Context context) {
        start(context, INTENT_TYPE_ADD_SCHEDULE, -1);
    }

    public static void start(Context context, int intentType, long todayId) {
        Intent start = new Intent(context, EtScheduleActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_INTENT_TYPE, intentType);
        start.putExtra(EXTRA_INTENT_TYPE_TODAY_ID, todayId);
        context.startActivity(start);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_schedule);
        StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.gray_new_home));

        RxBus.get().register(this);

        mPresenter = new EtSchedulePresenter();
        mPresenter.subscribe(this);
        initIntent(getIntent());
        mBinding.setHandler(new EtScheduleHandler(mPresenter));

        initListener();
    }

    private void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        int intentType = intent.getIntExtra(EXTRA_INTENT_TYPE, -1);
        switch (intentType) {
            case -1: {
                break;
            }
            case INTENT_TYPE_ADD_SCHEDULE: {
                isAddStatus = true;
                break;
            }
            case INTENT_TYPE_SCHEDULE: {
                isAddStatus = false;
                mBinding.btnSendIn.setText("修改日程");
                long id = intent.getLongExtra(EXTRA_INTENT_TYPE_TODAY_ID, -1);
                mPresenter.initEditData(id);
                break;
            }
        }
    }

    private void initListener() {
        mBinding.bbUrgent.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mUrgent = progress;
            }
        });

        mBinding.bbImportant.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress, float progressFloat) {
                super.onProgressChanged(progress, progressFloat);
                mImportant = progress;
            }
        });
        mBinding.etPlan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mBinding.btnSendIn.setClickable(false);
                    mBinding.btnSendIn.setTextColor(ContextCompat.getColor(EtScheduleActivity.this, R.color.black));
                    mBinding.btnSendIn.setBackgroundResource(R.drawable.bg_normal_gray);
                } else {
                    mBinding.btnSendIn.setClickable(true);
                    mBinding.btnSendIn.setTextColor(ContextCompat.getColor(EtScheduleActivity.this, R.color.white));
                    mBinding.btnSendIn.setBackgroundResource(R.drawable.bg_normal_blue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mBinding.btnSendIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                if (TextUtils.isEmpty(content)){
                    ToastHelper.shortToast(getString(R.string.string_content_is_empty));
                    return;
                }
                String tips = mBinding.etTips.getText().toString();
                Schedule schedule = new Schedule();
                schedule.setEndHour(mSelectEndHour);
                schedule.setEndMin(mSelectEndMin);
                schedule.setStartHour(mSelectStartHour);
                schedule.setStartMin(mSelectStartMin);
                schedule.setMSchedule(content);
                schedule.setMTips(tips);
                schedule.setMTime(new Date().getTime());
                schedule.setMImportant(mImportant);
                schedule.setMUrgent(mUrgent);

                if (isAddStatus) {
                    mPresenter.addSchedule(schedule);
                    ToastHelper.shortToast("日程添加成功。");
                } else {
                    mPresenter.editSchedule(schedule);
                    ToastHelper.shortToast("修改日程成功。");
                }
                finish();
            }
        });
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
    public void showTimePicker() {
        new TimeBottomSheetDialog(this).show();
    }

    @Override
    public void initEditData(Schedule schedule) {

    }

    @Subscribe
    public void setTimeCallBack(SetTimeEvent event) {
        mSelectEndHour = event.mModel.mSelectEndHour;
        mSelectEndMin = event.mModel.mSelectEndMin;
        mSelectStartHour = event.mModel.mSelectStartHour;
        mSelectStartMin = event.mModel.mSelectStartMin;

        mBinding.tvTime.setText(event.mModel.mTime);
    }
}
