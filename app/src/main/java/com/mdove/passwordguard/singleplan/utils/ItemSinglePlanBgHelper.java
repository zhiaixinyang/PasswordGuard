package com.mdove.passwordguard.singleplan.utils;

import android.graphics.Color;

/**
 * Created by MDove on 2018/6/24.
 */

public class ItemSinglePlanBgHelper {
    public static int getBg(int important, int urgent) {
        int a = 255;
        int r = 0;
        int b = 0;
        int g = 0;
        r = (int) (255 * ((float) urgent / 100f));
        b = (int) (255 * ((float) important / 100f));
        return Color.argb(a, r, b, g);
    }
}
