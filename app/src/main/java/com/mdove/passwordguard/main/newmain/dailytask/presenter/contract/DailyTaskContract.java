package com.mdove.passwordguard.main.newmain.dailytask.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.DailyTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.DailyTaskModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public interface DailyTaskContract {
    interface Presenter extends BasePresenter<DailyTaskContract.MvpView> {
        void initData();

        void insertSelfTask(String content);

        void onClickTaskSuc(DailyTaskModelVM vm);

        void onClickSee(DailyTaskModelVM vm);

        void onClickDelete(DailyTaskModelVM vm);

        void onClickPriority(DailyTaskModelVM vm);

        void onClickCopy(DailyTaskModelVM vm);

        void onClickBtnEdit(DailyTaskModelVM vm,boolean isNoChange);
    }

    interface MvpView extends BaseView<Presenter> {
        void insertSelfTask(int position);

        void initData(List<SelfTaskModel> data);

        void notifySelfTaskIsSuc(int position);

        void notifySelfTaskPriority(int position);

        void notifySelfSee(int position);

        void onClickDelete(int position);

        void onClickBtnEdit(int position);
    }
}
