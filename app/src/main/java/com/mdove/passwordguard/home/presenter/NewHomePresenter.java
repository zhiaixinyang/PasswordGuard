package com.mdove.passwordguard.home.presenter;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.SelfTaskLabelDao;
import com.mdove.passwordguard.greendao.SinglePlanDao;
import com.mdove.passwordguard.greendao.entity.SelfTaskLabel;
import com.mdove.passwordguard.greendao.entity.SinglePlan;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.model.HomeTimeModelVM;
import com.mdove.passwordguard.home.presenter.contract.NewHomeContract;
import com.mdove.passwordguard.main.newmain.dailytask.dialog.model.DailyTaskLabelModel;
import com.mdove.passwordguard.task.LabelSettingActivity;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class NewHomePresenter implements NewHomeContract.Presenter {
    private NewHomeContract.MvpView mView;

    @Override
    public void subscribe(NewHomeContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initTime() {
        Long time = new Date().getTime();
        String year = DateUtils.getYear(time) + "年";
        String month = DateUtils.getSimpleMonthC(true);
        String day = DateUtils.getDay(time) + "";

        mView.initTime(new HomeTimeModelVM(year, month, day));
    }

    @Override
    public void onClickEtTodayPlan() {
        EtTodayPlanActivity.start(mView.getContext());
    }
}
