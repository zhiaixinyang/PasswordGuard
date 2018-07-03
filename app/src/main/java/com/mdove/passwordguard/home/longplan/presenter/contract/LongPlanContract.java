package com.mdove.passwordguard.home.longplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.home.longplan.model.BaseLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.LongPlanModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface LongPlanContract {
    interface Presenter extends BasePresenter<LongPlanContract.MvpView> {
        void initData();

        void onClickBack();

        void onClickAddLongPlan();

        void onClickEditLongPlan(long id);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<BaseLongPlanModel> data);

        void finish();

        void deleteLongPlan(int position);
    }
}
