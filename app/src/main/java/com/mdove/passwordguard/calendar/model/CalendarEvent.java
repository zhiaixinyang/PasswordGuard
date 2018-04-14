package com.mdove.passwordguard.calendar.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MDove on 2018/4/14.
 */

public class CalendarEvent {
    public static final int UPDATE_TYPE_NORMAL=0;
    public static final int UPDATE_TYPE_GET=1;
    public static final int UPDATE_TYPE_LOST=2;
    public int type;
    public long mId;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {UPDATE_TYPE_GET,UPDATE_TYPE_LOST,UPDATE_TYPE_NORMAL})
    public @interface UpdateType{
    }

    public CalendarEvent(@UpdateType int type,long id) {
        this.type=type;
        mId=id;
    }
}
