package com.mdove.passwordguard.home.todayreview.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;

import java.util.List;

/**
 * Created by MDove on 2018/7/5.
 */

public interface CustomReViewContract {
    interface Presenter extends BasePresenter<CustomReViewContract.MvpView> {
        void initData();

        void addCustomSchedule(String content);
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<CustomReViewModel> data);

        void addCustomSchedule(int position);
    }
}
