package com.mdove.passwordguard.singleplan.utils;

import android.graphics.Color;

/**
 * Created by MDove on 2018/6/24.
 */

public class ItemSinglePlanBgHelper {
    public static String getBg(int important, int urgent) {
        if (important > urgent && important >= 60) {
            return "#FF1744";
        } else if (urgent > important && urgent >= 60) {
            return "#1976d2";
        } else if (important > urgent && important < 60 && important >= 30) {
            return "#E91E63";
        } else if (urgent > important && urgent < 60 && urgent >= 30) {
            return "#2196f3";
        } else if (important < 30 && urgent < 30) {
            return "#e2e2e2";
        }else{
            return "#e2e2e2";
        }
    }
}
