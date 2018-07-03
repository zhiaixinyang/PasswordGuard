package com.mdove.passwordguard.home.schedule.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.home.longplan.model.BaseLongPlanModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface ScheduleContract {
    interface Presenter extends BasePresenter<ScheduleContract.MvpView> {
        void initData();

        void onClickBack();

        void onClickShowShort();

        void onClickEtSchedule();

        void onClickEditSchedule(long id);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<BaseScheduleModel> data);

        void finish();

        void deleteSchedule(int position);

        void showShort();
    }
}
