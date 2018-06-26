package com.mdove.passwordguard.home.ettodayplan.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.layoutmanager.PickerLayoutManager;
import com.mdove.passwordguard.home.ettodayplan.adapter.EtTodayPlanTimeAdapter;
import com.mdove.passwordguard.singleplan.adapter.EtSinglePlanAdapter;
import com.mdove.passwordguard.utils.SystemUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/26.
 */

public class TimeBottomSheetDialog extends Dialog {
    private RecyclerView mRlvStartHour, mRlvStartMin;
    private RecyclerView mRlvEndHour, mRlvEndMin;
    private TextView mBtnCancel, mBtnOk;
    private PickerLayoutManager mPickerLayoutManagerStartHour, mPickerLayoutManagerStartMin;
    private PickerLayoutManager mPickerLayoutManagerEndHour, mPickerLayoutManagerEndMin;
    private EtTodayPlanTimeAdapter mStartHourAdapter, mStartMinAdapter, mEndHourAdapter, mEndMinAdapter;

    private List<String> mStartHours = new ArrayList<>();
    private List<String> mEndHours = new ArrayList<>();
    private List<String> mStartMinutes = new ArrayList<>();
    private List<String> mEndMinutes = new ArrayList<>();
    private int mSelectStartHour, mSelectStartMin;
    private int mSelectEndHour, mSelectEndMin;

    public TimeBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public TimeBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_sheet_et_today_plan_time);
        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = getWindowWidth();
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        mRlvStartHour = findViewById(R.id.rlv_start_hour);
        mRlvStartMin = findViewById(R.id.rlv_start_min);
        mRlvEndHour = findViewById(R.id.rlv_end_hour);
        mRlvEndMin = findViewById(R.id.rlv_end_min);

        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk = findViewById(R.id.btn_ok);

        initData();
        initListener();
    }

    protected int getWindowWidth() {
        float percent = 1f;
        WindowManager wm = this.getWindow().getWindowManager();
        int screenWidth = SystemUtils.getScreenWidth(wm);
        int screenHeight = SystemUtils.getScreenHeight(wm);
        return (int) (screenWidth > screenHeight
                ? screenHeight * percent
                : screenWidth * percent);
    }

    private void initData() {
        for (int i = 1; i <= 24; i++) {
            if (i <= 9) {
                mStartHours.add("0" + i);
            } else {
                mStartHours.add(i + "");
            }
        }

        for (int i = 1; i <= 24; i++) {
            if (i <= 9) {
                mEndHours.add("0" + i);
            } else {
                mEndHours.add(i + "");
            }
        }

        for (int i = 0; i < 60; i++) {
            if (i <= 9) {
                mStartMinutes.add("0" + i);
            } else {
                mStartMinutes.add(i + "");
            }
        }

        for (int i = 0; i < 60; i++) {
            if (i <= 9) {
                mEndMinutes.add("0" + i);
            } else {
                mEndMinutes.add(i + "");
            }
        }

        mStartHourAdapter = new EtTodayPlanTimeAdapter(mStartHours);
        mStartMinAdapter = new EtTodayPlanTimeAdapter(mStartMinutes);
        mEndHourAdapter = new EtTodayPlanTimeAdapter(mEndHours);
        mEndMinAdapter = new EtTodayPlanTimeAdapter(mEndMinutes);

        mPickerLayoutManagerStartHour = new PickerLayoutManager(getContext(), mRlvStartHour, PickerLayoutManager.VERTICAL, false, 3, 0.4f, true);
        mRlvStartHour.setAdapter(mStartHourAdapter);
        mRlvStartHour.setLayoutManager(mPickerLayoutManagerStartHour);

        mPickerLayoutManagerStartMin = new PickerLayoutManager(getContext(), mRlvStartMin, PickerLayoutManager.VERTICAL, false, 3, 0.4f, true);
        mRlvStartMin.setAdapter(mStartMinAdapter);
        mRlvStartMin.setLayoutManager(mPickerLayoutManagerStartMin);

        mPickerLayoutManagerEndHour = new PickerLayoutManager(getContext(), mRlvEndHour, PickerLayoutManager.VERTICAL, false, 3, 0.4f, true);
        mRlvEndHour.setAdapter(mEndHourAdapter);
        mRlvEndHour.setLayoutManager(mPickerLayoutManagerEndHour);

        mPickerLayoutManagerEndMin = new PickerLayoutManager(getContext(), mRlvEndMin, PickerLayoutManager.VERTICAL, false, 3, 0.4f, true);
        mRlvEndMin.setAdapter(mEndMinAdapter);
        mRlvEndMin.setLayoutManager(mPickerLayoutManagerEndMin);
    }

    private void initListener() {
        mPickerLayoutManagerStartHour.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int position) {
                mSelectStartHour = Integer.valueOf(mStartHours.get(position));
            }
        });

        mPickerLayoutManagerStartMin.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int position) {
                mSelectStartMin = Integer.valueOf(mStartMinutes.get(position));
            }
        });
        mPickerLayoutManagerEndHour.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int position) {
                mSelectEndHour = Integer.valueOf(mStartHours.get(position));
            }
        });

        mPickerLayoutManagerEndMin.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int position) {
                mSelectEndMin = Integer.valueOf(mStartMinutes.get(position));
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectStartHour < mSelectEndHour || (mSelectStartHour == mSelectEndMin && mSelectEndMin < mSelectStartMin)) {
                    ToastHelper.shortToast("设置时间有点不合理呀~");
                } else {

                }
            }
        });
    }
}
