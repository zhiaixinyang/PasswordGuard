package com.mdove.passwordguard.main.model;

import android.support.annotation.IntDef;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MDove on 2018/2/9.
 */

public class BaseMainModel implements Serializable {
    //判断Item类型，0为操作型Item（sys），1为数据型Item
    public int mType;
    //具体系统类型
    public @SysType
    int mSysType = -1;
    public String mTime;

    public static final int MAIN_ITEM_SYS_TYPE_GROUP = 0;
    public static final int MAIN_ITEM_SYS_TYPE_SEARCH = 1;
    public static final int MAIN_ITEM_SYS_TYPE_TOP_TIME = 2;
    public static final int MAIN_ITEM_SYS_TYPE_OPTION = 4;
    public static final int MAIN_ITEM_SYS_TYPE_DAILY_PLAN = 5;
    public static final int MAIN_ITEM_SYS_TYPE_DAILY_SELF = 6;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {MAIN_ITEM_SYS_TYPE_DAILY_SELF, MAIN_ITEM_SYS_TYPE_DAILY_PLAN, MAIN_ITEM_SYS_TYPE_OPTION,
            MAIN_ITEM_SYS_TYPE_GROUP, MAIN_ITEM_SYS_TYPE_SEARCH, MAIN_ITEM_SYS_TYPE_TOP_TIME})
    public @interface SysType {
    }
}
