package com.example.smartandgreensociety.UserAuth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SP {
    public static final String MY_PREFS_NAME = "MYSP";

    public static void setSP(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getSP(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String value = prefs.getString(key, null);
        if (value != null) {
            return value;
        }
        return "0";
    }
}