package com.mdove.passwordguard.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.mdove.passwordguard.App;


/**
 * Created by MDove on 2018/2/11.
 */

public class AppLockConfig {
    public static final int LOCK_MODE_SCREEN_OFF = 0;
    public static final int LOCK_MODE_SCREEN_OFF_3_MIN = 1;
    public static final int LOCK_MODE_EXIT = 2;

    private static SharedPreferences sPrefs;
    private static final String PREFS_FILE = "app_lock_prefs";
    private static final String PREFS_IS_LOCK = "app_is_lock";
    private static final String PASS_CODE = "PASS_CODE";
    private static final String AUTH_WITH_FINGER = "AUTH_WITH_FINGER";
    private static final String HAS_SET_FINGER = "HAS_SET_FINGER";
    private static final String LOCK_MODE = "LOCK_MODE";
    private static final String UNLOCK_ALL_ONCE = "UNLOCK_ALL_ONCE";
    private static final String LAST_UNLOCK_TIME = "LAST_UNLOCK_TIME";
    private static final String LAST_SCREEN_OFF_TIME = "LAST_SCREEN_OFF_TIME";

    private static SharedPreferences initPreference() {
        if (sPrefs == null) {
            sPrefs = App.getAppContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        }
        return sPrefs;
    }

    public static void setLock(boolean isLock){
        SharedPreferences preferences = initPreference();
        preferences.edit().putBoolean(PREFS_IS_LOCK, isLock).apply();
    }

    public static Boolean isLock(){
        SharedPreferences preferences = initPreference();
        return preferences.getBoolean(PREFS_IS_LOCK, false);
    }

    public static void setPassCode(String passCode) {
        SharedPreferences preferences = initPreference();
        preferences.edit().putString(PASS_CODE, passCode).apply();
    }

    public static String getPassCode() {
        SharedPreferences preferences = initPreference();
        return preferences.getString(PASS_CODE, null);
    }

    public static void setAuthWithFinger(boolean authWithFinger) {
        SharedPreferences preferences = initPreference();
        preferences.edit().putBoolean(AUTH_WITH_FINGER, authWithFinger).apply();
    }

    public static boolean isAuthWithFinger() {
        SharedPreferences preferences = initPreference();
        return preferences.getBoolean(AUTH_WITH_FINGER, false);
    }

    public static void setSetWithFinger() {
        SharedPreferences preferences = initPreference();
        preferences.edit().putBoolean(HAS_SET_FINGER, true).apply();
    }

    public static boolean hasSetWithFinger() {
        SharedPreferences preferences = initPreference();
        return preferences.getBoolean(HAS_SET_FINGER, false);
    }

    public static void setLockMode(int lockMode) {
        SharedPreferences preferences = initPreference();
        preferences.edit().putInt(LOCK_MODE, lockMode).apply();
    }

    public static int getLockMode() {
        SharedPreferences preferences = initPreference();
        return preferences.getInt(LOCK_MODE, LOCK_MODE_EXIT);
    }

    public static void setUnlockAllOnce(boolean unlockAllOnce) {
        SharedPreferences preferences = initPreference();
        preferences.edit().putBoolean(UNLOCK_ALL_ONCE, unlockAllOnce).apply();
    }

    public static boolean getUnlockAllOnce() {
        SharedPreferences preferences = initPreference();
        return preferences.getBoolean(UNLOCK_ALL_ONCE, false);
    }

    public static void setLastUnlockTime() {
        SharedPreferences preferences = initPreference();
        preferences.edit().putLong(LAST_UNLOCK_TIME, System.currentTimeMillis()).apply();
    }

    public static long getGapBetweenLastUnlockTime() {
        SharedPreferences preferences = initPreference();
        long lastUnlockTime = preferences.getLong(LAST_UNLOCK_TIME, 0);
        return System.currentTimeMillis() - lastUnlockTime;
    }

    public static void setLastScreenOffTime() {
        SharedPreferences preferences = initPreference();
        preferences.edit().putLong(LAST_SCREEN_OFF_TIME, System.currentTimeMillis()).apply();
    }

    public static long getLastScreenOffTime() {
        SharedPreferences preferences = initPreference();
        return preferences.getLong(LAST_SCREEN_OFF_TIME, 0);
    }

}
