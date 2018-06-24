package com.mdove.passwordguard.singleplan.model;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanModelVM {
    public ObservableField<String> mSinglePlanTips = new ObservableField<>();
    public ObservableField<String> mSinglePlan = new ObservableField<>();
    public ObservableField<Long> mSinglePlanId = new ObservableField<>();

    public SinglePlanModelVM(SinglePlanModel singlePlanModel) {
        mSinglePlan.set(singlePlanModel.mSinglePlan);
        mSinglePlanId.set(singlePlanModel.mId);
    }
}
