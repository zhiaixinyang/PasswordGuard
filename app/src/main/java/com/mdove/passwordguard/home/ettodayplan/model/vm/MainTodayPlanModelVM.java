package com.mdove.passwordguard.home.ettodayplan.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;

/**
 * Created by MDove on 2018/6/27.
 */

public class MainTodayPlanModelVM {
    public ObservableField<String> mTodayPlan = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public MainTodayPlanModelVM(MainTodayPlanModel plan) {
        mTodayPlan.set(plan.mTodayPlan);
        mTime.set(plan.mTime);
    }
}
