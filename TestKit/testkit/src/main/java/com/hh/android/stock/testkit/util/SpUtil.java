package com.hh.android.stock.testkit.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

/**
 * SharePereference工具
 * Created by litj on 2016/11/23.
 */

public class SpUtil {

    public static final String SP_NAME = "sp_test_kit";
    public static final String TAG = "SpUtil";
    public static final int INVALID = -1;

    public static void saveSharedPreferences(Context context, String key, Object value) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor hexinEditor = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        if (value instanceof String) {
            hexinEditor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            hexinEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            hexinEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            hexinEditor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            hexinEditor.putFloat(key, (Float) value);
        } else {
            Log.d(TAG, "saveSharedPreferences -> type error ");
        }

        hexinEditor.commit();
    }

    public static void saveSharedPreferences(Context context, String[] keys, String[] values) {
        if (context ==null || keys == null || values == null || keys.length != values.length) {
            return;
        }
        SharedPreferences.Editor hexinEditor = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            String value = values[i];
            hexinEditor.putString(key, value);
        }
        hexinEditor.commit();
    }

    public static String getStringValue(Context context, String key) {
        return getStringValue(context, key, "");
    }

    public static String getStringValue(Context context, String key, String defaultValue) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "getStringValue error");
            return defaultValue;
        }

        SharedPreferences hexinSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        if (hexinSharedPreferences != null) {
            defaultValue = hexinSharedPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public static int getIntValue(Context context, String key) {
        return getIntValue(context, key, INVALID);
    }

    public static int getIntValue(Context context, String key, int defaultValue) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "getIntValue error");
            return defaultValue;
        }

        SharedPreferences hexinSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        if (hexinSharedPreferences != null) {
            defaultValue = hexinSharedPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public static float getFloatValue(Context context, String key) {
        return getFloatValue(context, key, INVALID);
    }

    public static float getFloatValue(Context context, String key, float defaultValue) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "getFloatValue error");
            return defaultValue;
        }

        SharedPreferences hexinSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        if (hexinSharedPreferences != null) {
            defaultValue = hexinSharedPreferences.getFloat(key, defaultValue);
        }
        return defaultValue;
    }

    public static long getLongValue(Context context, String key) {
        return getLongValue(context, key, INVALID);
    }

    public static long getLongValue(Context context, String key, long defaultValue) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "getLongValue error");
            return defaultValue;
        }

        SharedPreferences hexinSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        if (hexinSharedPreferences != null) {
            defaultValue = hexinSharedPreferences.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    public static boolean getBooleanValue(Context context, String key) {
        return getBooleanValue(context, key, false);
    }

    public static boolean getBooleanValue(Context context, String key, boolean defaultValue) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "getBooleanValue error");
            return defaultValue;
        }

        SharedPreferences hexinSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        if (hexinSharedPreferences != null) {
            defaultValue = hexinSharedPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    public static void reomveValue(Context context, String key) {
        if (context == null || key.isEmpty()) {
            Log.d(TAG, "reomveValue error");
            return;
        }
        SharedPreferences.Editor hexinEditor = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit();
        if (hexinEditor != null) {
            hexinEditor.remove(key);
        }
        hexinEditor.commit();
    }

}
