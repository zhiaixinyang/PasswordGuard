package com.mdove.passwordguard.singleplan.model;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/6/24.
 */

public class EtSinglePlanModelVM {
    public ObservableField<String> mSinglePlanTips = new ObservableField<>();
    public ObservableField<String> mSinglePlan = new ObservableField<>();

    public EtSinglePlanModelVM(String singlePlanTips, String singlePlan) {
        mSinglePlan.set(singlePlan);
        mSinglePlanTips.set(singlePlanTips);
    }
}
