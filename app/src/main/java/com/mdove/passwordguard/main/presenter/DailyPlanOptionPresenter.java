package com.mdove.passwordguard.main.presenter;

import android.support.annotation.IntDef;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.calendar.CalendarSmoothActivity;
import com.mdove.passwordguard.main.model.DailyPlanOptionInfo;
import com.mdove.passwordguard.main.presenter.contract.DailyPlanOptionContract;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/4/11.
 */

public class DailyPlanOptionPresenter implements DailyPlanOptionContract.Presenter {
    private List<DailyPlanOptionInfo> mData;
    private DailyPlanOptionContract.MvpView mView;
    public static final int DAILY_PLAN_OPTION_TYPE_CALENDER = 1;

    public DailyPlanOptionPresenter() {
        mData = new ArrayList<>();
    }

    //标记快捷操作的type
    @IntDef(value = {DAILY_PLAN_OPTION_TYPE_CALENDER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DailyOpenInfoType {
    }

    @Override
    public void subscribe(DailyPlanOptionContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void initDailyPlan() {
        DailyPlanOptionInfo account = new DailyPlanOptionInfo(DAILY_PLAN_OPTION_TYPE_CALENDER, "记录账号", "记录账号信息", R.drawable.bg_main_option_btn_1, R.mipmap.ic_btn_password);
        DailyPlanOptionInfo dailySelf = new DailyPlanOptionInfo(DAILY_PLAN_OPTION_TYPE_CALENDER, "随手记", "记录有趣", R.drawable.bg_main_option_btn_4, R.mipmap.ic_btn_password);
        DailyPlanOptionInfo selfTask = new DailyPlanOptionInfo(DAILY_PLAN_OPTION_TYPE_CALENDER, "我的工作", "记录我的工作", R.drawable.bg_main_option_btn_6, R.mipmap.ic_btn_self_task);
        DailyPlanOptionInfo deleteAccount = new DailyPlanOptionInfo(DAILY_PLAN_OPTION_TYPE_CALENDER, "删除记录", "账号信息", R.drawable.bg_main_option_btn_3, R.mipmap.ic_delete);
        mData.add(account);
        mData.add(dailySelf);
        mData.add(selfTask);
        mData.add(deleteAccount);

        mView.initDailyPlan(mData);
    }

    @Override
    public void onClickCalender() {
        CalendarSmoothActivity.start(mView.getContext());
    }
}
