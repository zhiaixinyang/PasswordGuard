package com.mdove.passwordguard.main.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.mdove.passwordguard.App;

/**
 * Created by MDove on 2018/4/5.
 */

public class MainConfig implements IMainConfig{
    private static final String PREFS_FILE = "m_dove_guard";
    private static SharedPreferences sPrefs;


    private static SharedPreferences initSharedPreferences() {
        if (sPrefs == null) {
            sPrefs = App.getAppContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        return sPrefs;
    }

    public static boolean isHideSysItemGroup() {
        SharedPreferences preferences = initSharedPreferences();
        return preferences.getBoolean(HIDE_MAIN_SYS_ITEM_GROUP, false);
    }

    public static void setHideSysItemGroup(boolean isHide) {
        SharedPreferences.Editor editor = initSharedPreferences().edit();
        editor.putBoolean(HIDE_MAIN_SYS_ITEM_GROUP, isHide);
        editor.apply();
    }

    public static boolean isHideSysItemSearch() {
        SharedPreferences preferences = initSharedPreferences();
        return preferences.getBoolean(HIDE_MAIN_SYS_ITEM_SEARCH, false);
    }

    public static void setHideSysItemSearch(boolean isHide) {
        SharedPreferences.Editor editor = initSharedPreferences().edit();
        editor.putBoolean(HIDE_MAIN_SYS_ITEM_SEARCH, isHide);
        editor.apply();
    }

    public static boolean isHideSysItemOption() {
        SharedPreferences preferences = initSharedPreferences();
        return preferences.getBoolean(HIDE_MAIN_SYS_ITEM_OPTION, false);
    }

    public static void setHideSysItemOption(boolean isHide) {
        SharedPreferences.Editor editor = initSharedPreferences().edit();
        editor.putBoolean(HIDE_MAIN_SYS_ITEM_OPTION, isHide);
        editor.apply();
    }

    public static boolean isHideSysItemTimeTop() {
        SharedPreferences preferences = initSharedPreferences();
        return preferences.getBoolean(HIDE_MAIN_SYS_ITEM_TIME_TOP, false);
    }

    public static void setHideSysItemTimeTop(boolean isHide) {
        SharedPreferences.Editor editor = initSharedPreferences().edit();
        editor.putBoolean(HIDE_MAIN_SYS_ITEM_TIME_TOP, isHide);
        editor.apply();
    }
}
