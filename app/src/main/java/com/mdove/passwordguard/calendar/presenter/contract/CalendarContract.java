package com.mdove.passwordguard.calendar.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.model.CollectPasswordModelVM;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.List;

/**
 * Created by MDove on 2018/3/28.
 */

public interface CalendarContract {
    interface Presenter extends BasePresenter<CalendarContract.MvpView> {
        void initData();

        void updateLostOrGet(long id, int type);

        void onClickDailyPlanDelete(DailyPlanModelVM vm);

        void onSelectDay(CalendarDay selectDay);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseCalendarModel> data);

        void updateLostOrGet(int position);

        void onClickDailyPlanDelete(int position);
    }
}
