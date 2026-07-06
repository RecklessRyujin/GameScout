package com.gamescout;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeManager {

    private static final String PREF = "gamescout_settings";
    private static final String KEY_THEME = "theme";

    // SAVE THEME
    public static void setTheme(Context context, String theme) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_THEME, theme).apply();
    }

    // GET THEME
    public static String getTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return prefs.getString(KEY_THEME, "system");
    }

    // APPLY THEME (THIS FIXES YOUR MISSING init ERROR)
    public static void applyTheme(Context context) {
        String theme = getTheme(context);

        switch (theme) {
            case "dark":
                context.setTheme(android.R.style.Theme_Material_NoActionBar);
                break;

            case "light":
                context.setTheme(android.R.style.Theme_Material_Light_NoActionBar);
                break;

            default:
                // system default
                context.setTheme(android.R.style.Theme_DeviceDefault);
                break;
        }
    }

    // OPTIONAL HELPER
    public static boolean isDark(String theme) {
        return "dark".equals(theme);
    }
}
