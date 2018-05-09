package com.mdove.passwordguard.main.newmain.home;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.calendar.CalendarSmoothAdapter;
import com.mdove.passwordguard.calendar.CustomLinearLayoutManager;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.databinding.FragmentEveryReplayBinding;
import com.mdove.passwordguard.main.newmain.everydayreplay.EtEverydayReplayActivity;
import com.mdove.passwordguard.main.newmain.home.adapter.HomeEverydayReplayAdapter;
import com.mdove.passwordguard.main.newmain.home.model.handler.EverydayReplayHandler;
import com.mdove.passwordguard.main.newmain.home.presenter.EverydayReplayPresenter;
import com.mdove.passwordguard.main.newmain.home.presenter.contract.EverydayReplayContract;
import com.mdove.passwordguard.ui.calendar.decorators.EventDecorator;
import com.mdove.passwordguard.ui.calendar.decorators.RemindDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendar.MonthWeekMaterialCalendarView;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.MaterialCalendarView;
import com.mdove.passwordguard.utils.DateUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public class EverydayReplayFragment extends Fragment implements EverydayReplayContract.MvpView {
    private FragmentEveryReplayBinding mBinding;
    private MonthWeekMaterialCalendarView monthWeekMaterialCalendarView;
    private CalendarDay selectedDate;
    private RecyclerView recyclerView;
    private EverydayReplayPresenter mPresenter;
    private HomeEverydayReplayAdapter mAdapter;
    private TextView mTvTime;

    public static EverydayReplayFragment newInstance() {

        Bundle args = new Bundle();

        EverydayReplayFragment fragment = new EverydayReplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_every_replay, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectedDate = CalendarDay.today();

        monthWeekMaterialCalendarView = mBinding.slidelayout;
        recyclerView = mBinding.recyclerView;
        mTvTime = mBinding.tvTime;

        initRecyclerView();

        monthWeekMaterialCalendarView.setCurrentDate(selectedDate);
        monthWeekMaterialCalendarView.setSelectedDate(selectedDate);
        mTvTime.setText(DateUtils.getDateChinese(selectedDate.getDate()));

        monthWeekMaterialCalendarView.state().edit().setSlideModeChangeListener(new MonthWeekMaterialCalendarView.SlideModeChangeListener() {
            @Override
            public void modeChange(MonthWeekMaterialCalendarView.Mode mode) {

            }
        }).setSlideDateSelectedlistener(new MonthWeekMaterialCalendarView.SlideDateSelectedlistener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selectedDate = date;
                mTvTime.setText(DateUtils.getDateChinese(selectedDate.getDate()));
                mPresenter.onSelectDay(selectedDate);
            }
        }).setSlideOnMonthChangedListener(new MonthWeekMaterialCalendarView.SlideOnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mTvTime.setText(DateUtils.getDateChinese(date.getDate()));
            }
        }).commit();

        addDecorator();
        initListener();
    }

    private void initListener() {
        mBinding.setActionHandler(new EverydayReplayHandler(mPresenter));
    }

    private void addDecorator() {
        //增加有红点标志
        monthWeekMaterialCalendarView.addDecorator(new EventDecorator(Color.RED));
        monthWeekMaterialCalendarView.addDecorator(new RemindDecorator(getActivity()));
    }

    private void initRecyclerView() {
        mPresenter = new EverydayReplayPresenter();
        mPresenter.subscribe(this);
        mPresenter.onSelectDay(selectedDate);

        mAdapter = new HomeEverydayReplayAdapter(getActivity(), mPresenter);

        recyclerView.setLayoutManager(new CustomLinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        mPresenter.initData();

        mPresenter.onSelectDay(selectedDate);
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

    @Override
    public void onClickEt() {
        EtEverydayReplayActivity.start(getContext());
    }
}
