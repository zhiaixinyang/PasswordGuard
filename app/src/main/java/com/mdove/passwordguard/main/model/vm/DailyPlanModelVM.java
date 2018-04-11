package com.mdove.passwordguard.main.model.vm;


import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.utils.DateUtil;

/**
 * Created by MDove on 2018/4/9.
 */

public class DailyPlanModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mDay = new ObservableField<>();
    public ObservableField<String> mWeek = new ObservableField<>();
    public ObservableField<String> mYearMonth = new ObservableField<>();
    public ObservableField<Integer> mStatus = new ObservableField<>();
    public int mPosition;

    public DailyPlanModelVM(DailyPlanModel dailyPlanModel, int position) {
        mTime.set(DateUtil.getHourM(dailyPlanModel.mTime));
        mDay.set(DateUtil.getDay(dailyPlanModel.mTime) + "");
        mWeek.set(DateUtil.getDayOfWeek(dailyPlanModel.mTime));
        mYearMonth.set(DateUtil.getYearMonth(dailyPlanModel.mTime));
        mContent.set(dailyPlanModel.mContent);
        mPosition = position;
        mStatus.set(dailyPlanModel.mStatus);
    }
}
