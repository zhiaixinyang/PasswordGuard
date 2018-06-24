package com.mdove.passwordguard.singleplan.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.BaseMainSelfTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelTempModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanTemp;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public interface EtSinglePlanContract {
    interface Presenter extends BasePresenter<EtSinglePlanContract.MvpView> {
        void initLabel();

        void onClickLabelSetting();

        void addSinglePlan(SinglePlan temp);
    }

    interface MvpView extends BaseView<Presenter> {
        void initLabel(List<DailyTaskLabelModel> data);
    }
}
