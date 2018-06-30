package com.mdove.passwordguard.singleplan.utils;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;

/**
 * Created by MDove on 2018/6/24.
 */

public class ItemSinglePlanBgHelper {
    public static int getBg(int important, int urgent) {
        if (important > urgent && important >= 60) {
            return ContextCompat.getColor(App.getAppContext(), R.color.blue_500);
        } else if (urgent > important && urgent >= 60) {
            return ContextCompat.getColor(App.getAppContext(), R.color.red_500);
        } else if (important > urgent && important < 60 && important >= 30) {
            return ContextCompat.getColor(App.getAppContext(), R.color.blue_300);
        } else if (urgent > important && urgent < 60 && urgent >= 30) {
            return ContextCompat.getColor(App.getAppContext(), R.color.red_300);
        } else if (important < 30 && urgent < 30) {
            return ContextCompat.getColor(App.getAppContext(), R.color.gray_new_home);
        } else {
            return ContextCompat.getColor(App.getAppContext(), R.color.gray_new_home);
        }
    }

    public static boolean isWhiteBg(int bg) {
        if (bg != ContextCompat.getColor(App.getAppContext(), R.color.gray_new_home)) {
            return false;
        } else {
            return true;
        }
    }
}
