package com.mdove.passwordguard.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mdove.passwordguard.App;

/**
 * Created by MDove on 2018/2/15.
 */

public class AppConfig implements IAppConfig{
    private static final String PREFS_FILE = "password_guard_prefs";
    private static SharedPreferences sPrefs;

    private static SharedPreferences initSharedPreferences() {
        if (sPrefs == null) {
            sPrefs = App.getAppContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        return sPrefs;
    }

    public static void setAppOrderTodayTime(long time){
        SharedPreferences.Editor editor = initSharedPreferences().edit();
        editor.putLong(KEY_ORDER_TODAY_TIME, time);
        editor.apply();
    }
    public static long getAppOrderTodayTime(){
        SharedPreferences preferences = initSharedPreferences();
        return preferences.getLong(KEY_ORDER_TODAY_TIME, 0);
    }
}
