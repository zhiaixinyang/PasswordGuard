package com.mdove.passwordguard.home.schedule.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;

/**
 * Created by MDove on 2018/7/1.
 */

public interface EtScheduleContract {
    interface Presenter extends BasePresenter<EtScheduleContract.MvpView> {
        void onClickBack();

        void addSchedule(Schedule schedule);

        void onClickTimePicker();

        void initEditData(long id);

        void editSchedule(Schedule schedule);
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();

        void showTimePicker();

        void initEditData(Schedule schedule);
    }
}
