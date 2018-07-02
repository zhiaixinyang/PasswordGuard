package com.mdove.passwordguard.home.todayreview.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.MainTodayPlanDao;
import com.mdove.passwordguard.greendao.ScheduleDao;
import com.mdove.passwordguard.greendao.SecondTodayPlanDao;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.Schedule;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.dialog.TimeBottomSheetDialog;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.home.todayreview.model.vm.ScheduleReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.contract.ActivityTodayReViewContract;
import com.mdove.passwordguard.home.todayreview.presenter.contract.TodayReViewContract;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/7/2.
 */

public class ActivityTodayReViewPresenter implements ActivityTodayReViewContract.Presenter {
    private ActivityTodayReViewContract.MvpView mView;

    @Override
    public void subscribe(ActivityTodayReViewContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }
}
