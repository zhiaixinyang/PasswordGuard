package com.mdove.passwordguard.calendar.presenter;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.calendar.presenter.contract.CalendarContract;
import com.mdove.passwordguard.collect.model.CollectDailySelfModel;
import com.mdove.passwordguard.collect.model.CollectDailySelfModelVM;
import com.mdove.passwordguard.collect.model.CollectPasswordModel;
import com.mdove.passwordguard.collect.model.CollectPasswordModelVM;
import com.mdove.passwordguard.collect.model.event.CollectDailySelfEvent;
import com.mdove.passwordguard.collect.model.event.CollectPasswordEvent;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.DailySelfDao;
import com.mdove.passwordguard.greendao.PasswordDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.greendao.entity.DailySelf;
import com.mdove.passwordguard.greendao.entity.Password;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.utils.ClipboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/10.
 */

public class CalendarPresenter implements CalendarContract.Presenter {
    private CalendarContract.MvpView mView;
    private List<DailyPlanModel> mData;
    private DailyPlanDao mDailyPlanDao;

    @Override
    public void subscribe(CalendarContract.MvpView view) {
        mView = view;

        mDailyPlanDao = App.getDaoSession().getDailyPlanDao();
    }

    @Override
    public void unSubscribe() {
    }


    @Override
    public void initData() {
        mData = new ArrayList<>();
        List<DailyPlan> data = mDailyPlanDao.queryBuilder().list();
        for (DailyPlan plan : data) {
            mData.add(new DailyPlanModel(plan));
        }

        mView.showData(mData);
    }
}
