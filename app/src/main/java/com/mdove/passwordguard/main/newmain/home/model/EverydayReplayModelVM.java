package com.mdove.passwordguard.main.newmain.home.model;


import android.databinding.ObservableField;

import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/5/3.
 */

public class EverydayReplayModelVM {
    public ObservableField<String> mTime = new ObservableField<>();
    public ObservableField<String> mAllTime = new ObservableField<>();
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mDay = new ObservableField<>();
    public ObservableField<String> mWeek = new ObservableField<>();
    public ObservableField<String> mYearMonth = new ObservableField<>();
    public ObservableField<Integer> mStatus = new ObservableField<>();
    public EverydayReplayModel mEverydayReplayModel;
    public int mPosition;

    public EverydayReplayModelVM(EverydayReplayModel everydayReplayModel, int position) {
        mAllTime.set(DateUtils.getDateChinese(everydayReplayModel.mTime));
        mTime.set(DateUtils.getHourM(everydayReplayModel.mTime));
        mDay.set(DateUtils.getDay(everydayReplayModel.mTime) + "");
        mWeek.set(DateUtils.getDayOfWeek(everydayReplayModel.mTime));
        mYearMonth.set(DateUtils.getYearMonth(everydayReplayModel.mTime));
        mContent.set(everydayReplayModel.mContent);
        mPosition = position;
        mStatus.set(everydayReplayModel.mStatus);
        mEverydayReplayModel = everydayReplayModel;
    }
}
