package com.mdove.passwordguard.home.longplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.LongPlan;

/**
 * Created by MDove on 2018/7/1.
 */

public interface EtLongPlanContract {
    interface Presenter extends BasePresenter<EtLongPlanContract.MvpView> {
        void onClickBack();

        void initLongPlan();

        void addLongPlan(LongPlan longPlan);

        void onClickStartTimePicker();

        void onClickEndTimePicker();

        void initEditData(long id);

        void editLongPlan(LongPlan longPlan);
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();

        void showStartTimePicker();

        void showEndTimePicker();

        void initEditData(LongPlan longPlan);
    }
}
