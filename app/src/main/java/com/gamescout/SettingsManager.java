package com.gamescout;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String PREF_NAME = "gamescout_settings";

    public static void setTheme(Context context, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString("theme", value).apply();
    }

    public static String getTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString("theme", "system");
    }

    public static void setAccent(Context context, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString("accent", value).apply();
    }

    public static String getAccent(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString("accent", "blue");
    }
}
