package com.mdove.passwordguard.home.main.presenter;

import com.mdove.passwordguard.base.listlayout.TestActivity;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.longplan.LongPlanActivity;
import com.mdove.passwordguard.home.main.presenter.contract.HomeOptionsContract;
import com.mdove.passwordguard.home.richeditor.RichEditorActivity;
import com.mdove.passwordguard.home.schedule.ScheduleActivity;
import com.mdove.passwordguard.home.todayreview.TodayReViewActivity;

public class HomeOptionsPresenter implements HomeOptionsContract.Presenter {
    private HomeOptionsContract.MvpView mView;

    public HomeOptionsPresenter() {

    }

    @Override
    public void onClickSchedule() {
        ScheduleActivity.start(mView.getContext());
    }

    @Override
    public void onClickTodayPlanReView() {
        TodayReViewActivity.start(mView.getContext());
    }

    @Override
    public void onClickAllPlan() {
        RichEditorActivity.start(mView.getContext());
    }

    @Override
    public void onClickEtLongPlan() {
        EtLongPlanActivity.start(mView.getContext());
    }

    @Override
    public void onClickLongPlan() {
        LongPlanActivity.start(mView.getContext());
    }

    @Override
    public void subscribe(HomeOptionsContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }
}
