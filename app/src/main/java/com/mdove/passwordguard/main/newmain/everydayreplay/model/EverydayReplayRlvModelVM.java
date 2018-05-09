package com.mdove.passwordguard.main.newmain.everydayreplay.model;


import android.databinding.ObservableField;

import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/5/9.
 */

public class EverydayReplayRlvModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mAllTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mDay = new ObservableField<>();
    public ObservableField<String> mWeek = new ObservableField<>();
    public ObservableField<String> mYearMonth = new ObservableField<>();
    public ObservableField<Integer> mStatus = new ObservableField<>();
    public DailyPlanModel mDailyPlanModel;
    public int mPosition;

    public EverydayReplayRlvModelVM(DailyPlanModel dailyPlanModel, int position) {
        mAllTime.set(DateUtils.getDateChinese(dailyPlanModel.mTime));
        mTime.set(DateUtils.getHourM(dailyPlanModel.mTime));
        mDay.set(DateUtils.getDay(dailyPlanModel.mTime) + "");
        mWeek.set(DateUtils.getDayOfWeek(dailyPlanModel.mTime));
        mYearMonth.set(DateUtils.getYearMonth(dailyPlanModel.mTime));
        mContent.set(dailyPlanModel.mContent);
        mPosition = position;
        mStatus.set(dailyPlanModel.mStatus);
        mDailyPlanModel = dailyPlanModel;
    }
}
