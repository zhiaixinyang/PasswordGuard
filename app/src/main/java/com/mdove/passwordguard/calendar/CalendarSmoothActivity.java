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
    private TextView _titlebar_month;
    private TextView _titlebar_week;
    private RecyclerView recyclerView;
    private DropIndicator dropIndicator;
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
        setContentView(R.layout.activity_calendar_smooth);
        selectedDate = CalendarDay.today();

        monthWeekMaterialCalendarView = findViewById(R.id.slidelayout);
        dropIndicator = findViewById(R.id.circleIndicator);
        _titlebar_month = findViewById(R.id.titlebar_month);
        _titlebar_week = findViewById(R.id.titlebar_week);
        recyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

        monthWeekMaterialCalendarView.setCurrentDate(selectedDate);
        monthWeekMaterialCalendarView.setSelectedDate(selectedDate);
        //默认是月模式
        setMonthSelector();
        monthWeekMaterialCalendarView.state().edit().setSlideModeChangeListener(new MonthWeekMaterialCalendarView.SlideModeChangeListener() {
            @Override
            public void modeChange(MonthWeekMaterialCalendarView.Mode mode) {
                if (mode.equals(MonthWeekMaterialCalendarView.Mode.MONTH)) {
                    if (!_titlebar_month.isSelected() && !dropAnimat) {
                        setMonthSelector();
                        clickMonthAnimator();
                    }
                }
                if (mode.equals(MonthWeekMaterialCalendarView.Mode.WEEK)) {
                    if (!_titlebar_week.isSelected() && !dropAnimat) {
                        setWeekSelector();
                        clickWeekAnimator();
                    }
                }
            }
        }).setSlideDateSelectedlistener(new MonthWeekMaterialCalendarView.SlideDateSelectedlistener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selectedDate = date;
//                _tv_selectdate.setText(new DateFormatTitleFormatter().format(selectedDate));
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

    private void clickWeekAnimator() {
        dropIndicator.startAniTo(0, 1, new BaseAnimatorlistener() {
            @Override
            public void onAnimationStart(Animator animator) {
                dropAnimat = true;
                _titlebar_month.setTextColor(getResources().getColor(R.color.white));
                _titlebar_week.setTextColor(getResources().getColor(R.color.white));
                monthWeekMaterialCalendarView.setAnimatStart(true);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                dropAnimat = false;
                _titlebar_month.setTextColor(getResources().getColor(R.color.white));
                _titlebar_week.setTextColor(getResources().getColor(R.color.colorPrimary));
                monthWeekMaterialCalendarView.setAnimatStart(false);
            }
        });
    }

    private boolean dropAnimat;

    private void clickMonthAnimator() {
        dropIndicator.startAniTo(1, 0, new BaseAnimatorlistener() {
            @Override
            public void onAnimationStart(Animator animator) {
                dropAnimat = true;
                _titlebar_week.setTextColor(getResources().getColor(R.color.white));
                _titlebar_month.setTextColor(getResources().getColor(R.color.white));
                monthWeekMaterialCalendarView.setAnimatStart(true);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                dropAnimat = false;
                _titlebar_week.setTextColor(getResources().getColor(R.color.white));
                _titlebar_month.setTextColor(getResources().getColor(R.color.colorPrimary));
                monthWeekMaterialCalendarView.setAnimatStart(false);
            }
        });
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

        mAdapter = new CalendarSmoothAdapter(this);

        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mPresenter.initData();
    }


    private void setWeekSelector() {
        clearTextSelect();
        //周模式时候设置回默认的DateFormatTitleFormatter 标题显示样式
//        _tv_selectdate.setText(new DateFormatTitleFormatter().format(selectedDate));
        _titlebar_month.setSelected(false);
        _titlebar_week.setSelected(true);
        _titlebar_week.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void setMonthSelector() {
        clearTextSelect();
        //月模式时候设置回默认的DateFormatTitleFormatter 标题显示样式
//        _tv_selectdate.setText(new DateFormatTitleFormatter().format(selectedDate));
        _titlebar_month.setSelected(true);
        _titlebar_month.setTextColor(getResources().getColor(R.color.colorPrimary));
        _titlebar_week.setSelected(false);
    }

    private void clearTextSelect() {
        _titlebar_week.setTextColor(getResources().getColor(R.color.white));
        _titlebar_month.setTextColor(getResources().getColor(R.color.white));
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

    public void clickMonth() {
        if (!_titlebar_month.isSelected() && !monthWeekMaterialCalendarView.isAnimatStart() && !dropAnimat) {
            setMonthSelector();
            monthWeekMaterialCalendarView.setMode(MonthWeekMaterialCalendarView.Mode.MONTH);
            clickMonthAnimator();

        }
    }

    public void clickWeek() {
        if (!_titlebar_week.isSelected() && !monthWeekMaterialCalendarView.isAnimatStart() && !dropAnimat) {
            setWeekSelector();
            monthWeekMaterialCalendarView.setMode(MonthWeekMaterialCalendarView.Mode.WEEK);
            clickWeekAnimator();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showData(List<DailyPlanModel> data) {
        mAdapter.setData(data);
    }
}
