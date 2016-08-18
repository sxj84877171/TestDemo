package com.lenovo.quickapp.uitl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
    }

}
