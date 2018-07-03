package com.mdove.passwordguard.home.longplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityEtLongPlanBinding;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.home.constant.TimeConstant;
import com.mdove.passwordguard.home.longplan.model.TempLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.handler.EtLongPlanHandler;
import com.mdove.passwordguard.home.longplan.presenter.EtLongPlanPresenter;
import com.mdove.passwordguard.home.longplan.presenter.contract.EtLongPlanContract;
import com.mdove.passwordguard.home.richeditor.RichEditorActivity;
import com.mdove.passwordguard.ui.pickerview.view.TimePickerView;
import com.mdove.passwordguard.ui.renkstar.BubbleSeekBar;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MDove on 2018/7/1.
 */

public class EtLongPlanActivity extends BaseActivity implements EtLongPlanContract.MvpView {
    public static final String EXTRA_INTENT_TYPE = "extra_intent_type";
    public static final String EXTRA_INTENT_TYPE_TODAY_ID = "extra_intent_type_today_id";
    public static final int INTENT_TYPE_ADD_PLAN = 0;
    public static final int INTENT_TYPE_EDIT_PLAN = 1;
    private boolean isAddStatus = true;

    private ActivityEtLongPlanBinding mBinding;
    private TimePickerView mStartPvTime, mEndPvTime;
    private Date mSelectStartDate, mSelectEndDate, mDefaultStartDate, mDefaultEndDate;
    private EtLongPlanPresenter mPresenter;
    private int mImportant = TimeConstant.DEFAUT_IMPORTANT, mUrgent = TimeConstant.DEFAUT_URGENT;

    public static void start(Context context) {
        start(context, INTENT_TYPE_ADD_PLAN, -1);
    }

    public static void start(Context context, int intentType, long todayId) {
        Intent start = new Intent(context, EtLongPlanActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_INTENT_TYPE, intentType);
        start.putExtra(EXTRA_INTENT_TYPE_TODAY_ID, todayId);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_et_long_plan);
        StatusBarUtils.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.gray_new_home));
        mBinding.btnRichEditor.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mPresenter = new EtLongPlanPresenter();
        mPresenter.subscribe(this);
        initIntent(getIntent());
        initTimePickerView();
        initListener();

        mBinding.setHandler(new EtLongPlanHandler(mPresenter));
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
            case INTENT_TYPE_ADD_PLAN: {
                isAddStatus = true;
                break;
            }
            case INTENT_TYPE_EDIT_PLAN: {
                isAddStatus = false;
                mBinding.tvSendIn.setText("修改计划");
                long id = intent.getLongExtra(EXTRA_INTENT_TYPE_TODAY_ID, -1);
                mPresenter.initEditData(id);
                break;
            }
        }
    }

    private void initListener() {
        mBinding.etPlan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mBinding.btnSendIn.setClickable(true);
                    mBinding.tvSendIn.setTextColor(ContextCompat.getColor(EtLongPlanActivity.this, R.color.black));
                    mBinding.btnSendIn.setCardBackgroundColor(ContextCompat.getColor(EtLongPlanActivity.this, R.color.white));
                } else {
                    mBinding.btnSendIn.setClickable(false);
                    mBinding.tvSendIn.setTextColor(ContextCompat.getColor(EtLongPlanActivity.this, R.color.white));
                    mBinding.btnSendIn.setCardBackgroundColor(ContextCompat.getColor(EtLongPlanActivity.this, R.color.gray_light));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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

        mBinding.btnSendIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etPlan.getText().toString();
                String tips = mBinding.etTips.getText().toString();
                LongPlan longPlan = new LongPlan();
                longPlan.setMEndTime(mSelectEndDate.getTime());
                longPlan.setMStartTime(mSelectStartDate.getTime());
                longPlan.setMImportant(mImportant);
                longPlan.setMUrgent(mUrgent);
                longPlan.setMTips(tips);
                longPlan.setMLongPlan(content);
                longPlan.setMTime(new Date().getTime());
                if (isAddStatus) {
                    mPresenter.addLongPlan(longPlan);
                    ToastHelper.shortToast("长期计划添加成功。");
                } else {
                    mPresenter.editLongPlan(longPlan);
                    ToastHelper.shortToast("修改计划成功。");
                }
                finish();
            }
        });
    }

    private void initTimePickerView() {
        mDefaultStartDate = new Date();
        mSelectStartDate = mDefaultStartDate;

        mBinding.tvTimeStart.setText(DateUtils.getDateChineseNoH(mDefaultStartDate.getTime()));
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 1);
        mDefaultEndDate = c.getTime();
        mSelectEndDate = mDefaultEndDate;
        mBinding.tvTimeEnd.setText(DateUtils.getDateChineseNoH(mDefaultEndDate.getTime()));

        Calendar selectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 12, 31);
        Calendar startDate = Calendar.getInstance();

        mStartPvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mSelectStartDate = date;
                mBinding.tvTimeStart.setText(DateUtils.getDateChineseNoH(mSelectStartDate.getTime()));
                if (mSelectStartDate.getTime() > mDefaultEndDate.getTime()) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(mSelectStartDate);
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    mDefaultEndDate = c.getTime();
                    mSelectEndDate = mDefaultEndDate;
                    mBinding.tvTimeEnd.setText(DateUtils.getDateChineseNoH(mDefaultEndDate.getTime()));
                }
                mStartPvTime.dismiss();
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .build();
        mEndPvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (date.getTime() > mSelectStartDate.getTime()) {
                    mSelectEndDate = date;

                    mBinding.tvTimeEnd.setText(DateUtils.getDateChineseNoH(mSelectEndDate.getTime()));
                    mEndPvTime.dismiss();
                } else {
                    ToastHelper.shortToast("时间选择不合理，怎么可能比开始时间还要早呢？");
                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .build();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showStartTimePicker() {
        mStartPvTime.show();
    }

    @Override
    public void showEndTimePicker() {
        mEndPvTime.show();
    }

    @Override
    public void onClickRichEditor() {
        LongPlan longPlan = new LongPlan();
        longPlan.setMStartTime(mSelectStartDate.getTime());
        longPlan.setMEndTime(mSelectEndDate.getTime());
        longPlan.setMImportant(mImportant);
        longPlan.setMUrgent(mUrgent);
        longPlan.setMTips(mBinding.etTips.getText().toString());
        longPlan.setMLongPlan(mBinding.etPlan.getText().toString());
        RichEditorActivity.start(this, new TempLongPlanModel(longPlan));
    }

    @Override
    public void initEditData(LongPlan longPlan) {
        mSelectEndDate = new Date(longPlan.getMEndTime());
        mSelectStartDate = new Date(longPlan.getMStartTime());
        mImportant = longPlan.mImportant;
        mUrgent = longPlan.mUrgent;

        mBinding.etPlan.setText(longPlan.mLongPlan);
        mBinding.tvTimeStart.setText(DateUtils.getDateChineseNoH(longPlan.mStartTime));
        mBinding.tvTimeEnd.setText(DateUtils.getDateChineseNoH(longPlan.mEndTime));
        mBinding.tvPlanTips.setText(longPlan.mTips);
        mBinding.bbImportant.setProgress(longPlan.mImportant);
        mBinding.bbUrgent.setProgress(longPlan.mUrgent);
    }
}
