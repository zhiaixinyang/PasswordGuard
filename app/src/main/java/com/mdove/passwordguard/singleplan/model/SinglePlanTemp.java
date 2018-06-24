package com.mdove.passwordguard.singleplan.model;

import com.mdove.passwordguard.greendao.entity.SinglePlan;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanTemp {
    public String mTask;
    public long mTime;
    public int mIsSuc;//0表示没有点击完成
    public int mIsSee;//0表示不在首页展示
    public int mUrgent;
    public int mImportant;

    public long mLabelId;
    public String mLabel;
}
