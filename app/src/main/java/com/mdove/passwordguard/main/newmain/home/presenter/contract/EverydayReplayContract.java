package com.mdove.passwordguard.main.newmain.home.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModelVM;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.List;

/**
 * Created by MDove on 2018/5/3.
 */

public interface EverydayReplayContract {
    interface Presenter extends BasePresenter<EverydayReplayContract.MvpView> {
        void initData();

        void updateLostOrGet(long id, int type);

        void onClickDailyPlanDelete(EverydayReplayModelVM vm);

        void onSelectDay(CalendarDay selectDay);

    }

    interface MvpView extends BaseView<Presenter> {
        void showData(List<BaseCalendarModel> data);

        void updateLostOrGet(int position);

        void onClickDailyPlanDelete(int position);
    }
}
