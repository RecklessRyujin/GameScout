package com.gamescout;

import android.content.Context;
import android.content.SharedPreferences;

public class AccentManager {

    private static final String PREF = "gamescout_settings";
    private static final String KEY_ACCENT = "accent";

    public static void setAccent(Context context, String color) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_ACCENT, color).apply();
    }

    public static String getAccent(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString(KEY_ACCENT, "blue");
    }
}
