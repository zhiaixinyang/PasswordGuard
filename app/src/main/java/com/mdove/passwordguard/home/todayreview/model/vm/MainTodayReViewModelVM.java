package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;

/**
 * Created by MDove on 2018/6/29.
 */

public class MainTodayReViewModelVM extends BaseTodayReViewVM {
    public ObservableField<String> mTodayPlan = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public MainTodayReViewModelVM(MainTodayReViewModel model) {
        mId.set(model.mId);

        mTodayPlan.set(model.mTodayPlan);
        mTime.set(model.mTime);
        mSucEnable.set(model.mIsSuc);
        if (model.mIsSuc == 0) {
            mIsSuc.set(false);
        } else {
            mIsSuc.set(true);
        }
    }
}
