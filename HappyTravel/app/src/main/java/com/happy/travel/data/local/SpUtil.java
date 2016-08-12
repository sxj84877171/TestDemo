package com.happy.travel.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.happy.travel.base.BaseActivity;

/**
 * Created by elvissun on 8/12/2016.
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
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }


}
