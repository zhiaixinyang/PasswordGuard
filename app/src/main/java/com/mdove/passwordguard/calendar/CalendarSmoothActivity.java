package com.mdove.passwordguard.calendar;

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
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.presenter.CalendarPresenter;
import com.mdove.passwordguard.calendar.presenter.contract.CalendarContract;
import com.mdove.passwordguard.ui.calendar.decorators.EventDecorator;
import com.mdove.passwordguard.ui.calendar.decorators.RemindDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendar.MonthWeekMaterialCalendarView;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.MaterialCalendarView;
import com.mdove.passwordguard.utils.DateUtil;

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
    private TextView mTvTime;

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
        mTvTime = findViewById(R.id.tv_time);

        initRecyclerView();

        monthWeekMaterialCalendarView.setCurrentDate(selectedDate);
        monthWeekMaterialCalendarView.setSelectedDate(selectedDate);
        mTvTime.setText(DateUtil.getDateChinese(selectedDate.getDate()));

        monthWeekMaterialCalendarView.state().edit().setSlideModeChangeListener(new MonthWeekMaterialCalendarView.SlideModeChangeListener() {
            @Override
            public void modeChange(MonthWeekMaterialCalendarView.Mode mode) {

            }
        }).setSlideDateSelectedlistener(new MonthWeekMaterialCalendarView.SlideDateSelectedlistener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selectedDate = date;
                mTvTime.setText(DateUtil.getDateChinese(selectedDate.getDate()));
                mPresenter.onSelectDay(selectedDate);
            }
        }).setSlideOnMonthChangedListener(new MonthWeekMaterialCalendarView.SlideOnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mTvTime.setText(DateUtil.getDateChinese(date.getDate()));
            }
        }).commit();

        addDecorator();
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    private void addDecorator() {
        //增加有红点标志
        monthWeekMaterialCalendarView.addDecorator(new EventDecorator(Color.RED));
        monthWeekMaterialCalendarView.addDecorator(new RemindDecorator(this));
    }


    private void initRecyclerView() {
        mPresenter = new CalendarPresenter();
        mPresenter.subscribe(this);
        mPresenter.onSelectDay(selectedDate);

        mAdapter = new CalendarSmoothAdapter(this, mPresenter);

        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mPresenter.initData();

        mPresenter.onSelectDay(selectedDate);
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
