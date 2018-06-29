package com.mdove.passwordguard.home.todayreview.presenter.contract;

import com.mdove.passwordguard.base.BasePresenter;
import com.mdove.passwordguard.base.BaseView;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;

import java.util.List;

/**
 * Created by MDove on 2018/6/29.
 */

public interface TodayReViewContract {
    interface Presenter extends BasePresenter<TodayReViewContract.MvpView> {
        void initData(long mainTodayPlanId);

        void addMainTodayReView(MainTodayPlan todayPlan);

        void addSecondTodayReView(SecondTodayPlan todayPlan);

        void onClickTodayReViewSuc(BaseTodayReViewVM vm);

        void onClickBack();

        void onClickTime();
    }

    interface MvpView extends BaseView<Presenter> {
        void initData(List<BaseTodayReViewModel> data);

        void finish();

        void addMainTodayReViewReturn(MainTodayReViewModel model);

        void addMainTodayReViewReturn(SecondTodayReViewModel model);

        void onClickTodayReViewSuc(int position);
    }
}
