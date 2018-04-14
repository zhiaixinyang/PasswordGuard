package com.mdove.passwordguard.calendar;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.base.listener.BaseAnimatorlistener;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.presenter.CalendarPresenter;
import com.mdove.passwordguard.calendar.presenter.contract.CalendarContract;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.ui.calendar.DropIndicator;
import com.mdove.passwordguard.ui.calendar.decorators.EventDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendar.MonthWeekMaterialCalendarView;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MDove on 2018/4/10.
 */
public class CalendarSmoothActivity extends BaseActivity implements CalendarContract.MvpView {
    private MonthWeekMaterialCalendarView monthWeekMaterialCalendarView;
    private CalendarDay selectedDate;
    private RecyclerView recyclerView;
    private boolean canPaging = true;
    private CalendarPresenter mPresenter;
    private CalendarSmoothAdapter mAdapter;

    public static void start(Context context) {
        Intent start = new Intent(context, CalendarSmoothActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("每日复盘");
        setContentView(R.layout.activity_calendar_smooth);
        selectedDate = CalendarDay.today();

        monthWeekMaterialCalendarView = findViewById(R.id.slidelayout);
        recyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

        monthWeekMaterialCalendarView.setCurrentDate(selectedDate);
        monthWeekMaterialCalendarView.setSelectedDate(selectedDate);

        monthWeekMaterialCalendarView.state().edit().setSlideModeChangeListener(new MonthWeekMaterialCalendarView.SlideModeChangeListener() {
            @Override
            public void modeChange(MonthWeekMaterialCalendarView.Mode mode) {

            }
        }).setSlideDateSelectedlistener(new MonthWeekMaterialCalendarView.SlideDateSelectedlistener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selectedDate = date;
            }
        }).setSlideOnMonthChangedListener(new MonthWeekMaterialCalendarView.SlideOnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        }).commit();

        AddDecorator();
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    private void AddDecorator() {
        //150天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        ArrayList<CalendarDay> dates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            CalendarDay day = CalendarDay.from(calendar);
            dates.add(day);
            calendar.add(Calendar.DATE, 5);
        }
        //增加有红点标志
        monthWeekMaterialCalendarView.addDecorator(new EventDecorator(Color.RED, dates));
    }


    private void initRecyclerView() {
        mPresenter = new CalendarPresenter();
        mPresenter.subscribe(this);

        mAdapter = new CalendarSmoothAdapter(this,mPresenter);

        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mPresenter.initData();
    }


    public void clickPrevious() {
        monthWeekMaterialCalendarView.goToPrevious();
    }

    public void clickNext() {
        monthWeekMaterialCalendarView.goToNext();
    }

    public void clickToday() {
        selectedDate = CalendarDay.today();
        monthWeekMaterialCalendarView.setCurrentDate(selectedDate);
        monthWeekMaterialCalendarView.setSelectedDate(selectedDate);
    }

    public void clickSetting() {
        CharSequence[] items = {
                "显示其他月份",
                "显示超出最大和最小日期范围",
                "能否选中其他月，选中会跳转",
                "能否竖直滑动",
                "能否左右滑动"
        };

        final int[] itemValues = {
                MaterialCalendarView.SHOW_OTHER_MONTHS,
                MaterialCalendarView.SHOW_OUT_OF_RANGE,
        };
        int showOtherDates = monthWeekMaterialCalendarView.getShowOtherDates();
        @SuppressWarnings("ResourceType")
        boolean[] initSelections = {
                MaterialCalendarView.showOtherMonths(showOtherDates),
                MaterialCalendarView.showOutOfRange(showOtherDates),
                monthWeekMaterialCalendarView.allowClickDaysOutsideCurrentMonth(),
                monthWeekMaterialCalendarView.isCanDrag(),
                canPaging
        };
        new AlertDialog.Builder(this)
                .setTitle("Show Other Dates")
                .setMultiChoiceItems(items, initSelections, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (which < 2) {
                            int showOtherDates = monthWeekMaterialCalendarView.getShowOtherDates();
                            if (isChecked) {
                                //Set flag
                                showOtherDates |= itemValues[which];
                            } else {
                                //Unset flag
                                showOtherDates &= ~itemValues[which];
                            }
                            monthWeekMaterialCalendarView.setShowOtherDates(showOtherDates);
                        } else if (which == 2) {
                            monthWeekMaterialCalendarView.setAllowClickDaysOutsideCurrentMonth(isChecked);
                        } else if (which == 3) {
                            monthWeekMaterialCalendarView.setCanDrag(isChecked);
                        } else if (which == 4) {
                            canPaging = isChecked;
                            monthWeekMaterialCalendarView.setPagingEnabled(isChecked);
                        }

                    }
                })
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<BaseCalendarModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void updateLostOrGet(int position) {
        mAdapter.notifyPosition(position);
    }

    @Override
    public void onClickDailyPlanDelete(int position) {
        mAdapter.notifyDelete(position);
    }
}
