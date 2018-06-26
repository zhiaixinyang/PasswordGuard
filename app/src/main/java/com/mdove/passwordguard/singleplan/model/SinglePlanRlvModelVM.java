package com.mdove.passwordguard.singleplan.model;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanRlvModelVM {
    public static final int BG_IS_MAIN_PLAN = 0;
    public static final int BG_IS_SECOND_PLAN = 1;

    public ObservableField<String> mSinglePlanTips = new ObservableField<>();
    public ObservableField<String> mSinglePlan = new ObservableField<>();
    public ObservableField<Integer> mIsMain = new ObservableField<>();

    public SinglePlanRlvModelVM(int isMian, String singlePlanTips, String singlePlan) {
        mSinglePlan.set(singlePlan);
        mSinglePlanTips.set(singlePlanTips);
        mIsMain.set(isMian);
    }
}
