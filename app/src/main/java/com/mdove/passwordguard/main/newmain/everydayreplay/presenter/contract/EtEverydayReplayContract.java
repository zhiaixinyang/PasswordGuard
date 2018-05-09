package com.mdove.passwordguard.main.newmain.everydayreplay.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModelVM;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public interface EtEverydayReplayContract {
    interface Presenter extends BasePresenter<EtEverydayReplayContract.MvpView> {
        void initData();

        void updateLostOrGet(long id, int type);

        void onClickDailyPlanDelete(EverydayReplayRlvModelVM vm);

        void onSelectDay(CalendarDay selectDay);

        void addDailyPlan(String string,int status);
    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseCalendarModel> data);

        void updateLostOrGet(int position);

        void onClickDailyPlanDelete(int position);

        void addDailyPlan(int position);

    }
}
