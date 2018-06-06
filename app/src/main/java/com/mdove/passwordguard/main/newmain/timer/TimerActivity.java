package com.mdove.passwordguard.main.newmain.timer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityTimerBinding;
import com.mdove.passwordguard.main.newmain.timer.presenter.MainSelfTaskTimerPresenter;
import com.mdove.passwordguard.main.newmain.timer.presenter.contract.MainSelfTaskTimerContract;
import com.mdove.passwordguard.ui.pickerview.listener.CustomListener;
import com.mdove.passwordguard.ui.pickerview.view.TimePickerView;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MDove on 2018/6/5.
 */

public class TimerActivity extends BaseActivity implements MainSelfTaskTimerContract.MvpView {
    private ActivityTimerBinding mBinding;
    private TimePickerView mPvTime;
    private Date mSelectDate;
    private MainSelfTaskTimerPresenter mPresenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, TimerActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_timer);
        initToolbar();
        initView();
        mPresenter = new MainSelfTaskTimerPresenter();
        mPresenter.subscribe(this);

        mBinding.btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPvTime.show();
            }
        });
        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etTimer.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.insertSelfTaskTimer(content);
                }else{
                    ToastHelper.shortToast("计划总不能为空吧...");
                }
            }
        });
    }

    private void initToolbar() {
        mBinding.toolbar.setTitle("闹钟计划");
        setSupportActionBar(mBinding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initView() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);

        mPvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mSelectDate = date;
                mBinding.tvTime.setText(DateUtils.getDateChinese(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(selectedDate, endDate)
                .setLayoutRes(R.layout.view_pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvTime.returnData();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPvTime.dismiss();
                            }
                        });
                    }
                })
                .setDividerColor(Color.BLACK)
                .build();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void insertSelfTaskTimer(String content) {
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent();
        myIntent.setAction(TimerReceiver.TIMER_ACTION);
        myIntent.putExtra(TimerConstant.TIMER_EXTRA, content);
        PendingIntent sender = PendingIntent.getBroadcast(TimerActivity.this, 0, myIntent, 0);
        alarm.set(AlarmManager.RTC_WAKEUP, mSelectDate.getTime(), sender);
    }
}
