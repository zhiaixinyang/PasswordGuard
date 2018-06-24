package com.mdove.passwordguard.singleplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface SinglePlanContract {
    interface Presenter extends BasePresenter<SinglePlanContract.MvpView> {
        void initSinglePlan();

        void onClickInEtSinglePlan();

        void onClickDeleteSinglePlan(long id);
    }

    interface MvpView extends BaseView<Presenter> {
        void initSinglePlan(List<SinglePlanModel> data);

        void deleteSinglePlan(int position);
    }
}
