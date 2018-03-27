package com.mdove.passwordguard.task.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.mdove.passwordguard.R;

/**
 * Created by MDove on 2018/3/27.
 */

public class SelfTaskPriorityHelper {
    public static final int PRIORITY_IS_NORMAL = 0;
    public static final int PRIORITY_IS_YELLOW = 1;
    public static final int PRIORITY_IS_RED = 2;

    public static int getPriorityBtnColor(Context context, int priority) {
        int color;
        switch (priority) {
            case PRIORITY_IS_NORMAL: {
                color = ContextCompat.getColor(context, R.color.commonColor);
                break;
            }
            case PRIORITY_IS_YELLOW: {
                color = ContextCompat.getColor(context, R.color.self_task_priority_yellow);
                break;
            }
            case PRIORITY_IS_RED: {
                color = ContextCompat.getColor(context, R.color.self_task_priority_red);
                break;
            }
            default: {
                color = ContextCompat.getColor(context, R.color.commonColor);
                break;
            }
        }
        return color;
    }

    public static int getPriorityTextColor(Context context, int priority) {
        int color;
        switch (priority) {
            case PRIORITY_IS_NORMAL: {
                color = ContextCompat.getColor(context, R.color.black);
                break;
            }
            case PRIORITY_IS_YELLOW: {
                color = ContextCompat.getColor(context, R.color.self_task_priority_yellow);
                break;
            }
            case PRIORITY_IS_RED: {
                color = ContextCompat.getColor(context, R.color.self_task_priority_red);
                break;
            }
            default: {
                color = ContextCompat.getColor(context, R.color.commonColor);
                break;
            }
        }
        return color;
    }
}
