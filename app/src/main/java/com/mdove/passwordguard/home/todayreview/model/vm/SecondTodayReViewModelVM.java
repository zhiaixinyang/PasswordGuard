package com.mdove.passwordguard.home.todayreview.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;

/**
 * Created by MDove on 2018/6/29.
 */

public class SecondTodayReViewModelVM extends BaseTodayReViewVM{
    public ObservableField<String> mTodayPlan = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public SecondTodayReViewModelVM(SecondTodayReViewModel model) {
        mId.set(model.mId);

        mTime.set(model.mTime);
        mTodayPlan.set(model.mTodayPlan);
        mSucEnable.set(model.mIsSuc);

        if (model.mIsSuc == 0) {
            mIsSuc.set(false);
        } else {
            mIsSuc.set(true);
        }
    }
}
