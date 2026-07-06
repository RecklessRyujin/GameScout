package com.gamescout;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeManager {

    private static final String PREF = "gamescout_settings";
    private static final String KEY_THEME = "theme";

    public static void setTheme(Context context, String theme) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_THEME, theme).apply();
    }

    public static String getTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString(KEY_THEME, "system");
    }

    // Optional helper (use later in MainActivity)
    public static boolean isDark(String theme) {
        return theme.equals("dark");
    }
}
