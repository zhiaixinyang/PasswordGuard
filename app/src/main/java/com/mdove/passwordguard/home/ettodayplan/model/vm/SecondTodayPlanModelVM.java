package com.mdove.passwordguard.home.ettodayplan.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;

/**
 * Created by MDove on 2018/6/27.
 */

public class SecondTodayPlanModelVM {
    public ObservableField<String> mTodayPlan = new ObservableField<>();

    public SecondTodayPlanModelVM(SecondTodayPlanModel model) {
        mTodayPlan.set(model.mTodayPlan);
    }
}
