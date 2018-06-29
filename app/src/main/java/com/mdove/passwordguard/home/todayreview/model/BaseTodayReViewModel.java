package com.mdove.passwordguard.home.todayreview.model;

/**
 * Created by MDove on 2018/6/29.
 */

public abstract class BaseTodayReViewModel {
    public long mId;
    public int mIsSuc;//0表示没有点击完成

    public static final int DEFAULT_SUC_AT_TIME = 1;
    public static final int DEFAULT_SUC_NO_AT_TIME_PRE = 2;
    public static final int DEFAULT_SUC_NO_AT_TIME_LAST = 3;
    public static final int DEFAULT_NOT_SUC = 0;
}
