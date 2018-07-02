package com.mdove.passwordguard.home.todayreview.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;

import java.util.List;

/**
 * Created by MDove on 2018/7/2.
 */

public interface ActivityTodayReViewContract {
    interface Presenter extends BasePresenter<ActivityTodayReViewContract.MvpView> {
        void onClickBack();
    }

    interface MvpView extends BaseView<Presenter> {
        void finish();
    }
}
