package com.mdove.passwordguard.main.model.event;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MDove on 2018/4/5.
 */

public class HideItemMainEvent {
    public static final int TYPE_HIDE_ITEM_MAIN_SEARCH = 0;
    public static final int TYPE_HIDE_ITEM_MAIN_GROUP = 1;
    public static final int TYPE_HIDE_ITEM_MAIN_TIME_TOP = 2;
    public static final int TYPE_HIDE_ITEM_MAIN_OPTION = 3;

    public int mTypeHide = -1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {TYPE_HIDE_ITEM_MAIN_OPTION, TYPE_HIDE_ITEM_MAIN_TIME_TOP,
            TYPE_HIDE_ITEM_MAIN_GROUP, TYPE_HIDE_ITEM_MAIN_SEARCH})
    public @interface TypeHide {
    }

    public HideItemMainEvent(@TypeHide int typeHide) {
        mTypeHide = typeHide;
    }
}
