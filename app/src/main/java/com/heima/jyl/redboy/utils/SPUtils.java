package com.heima.jyl.redboy.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class SPUtils {
    private static final String FILE_NAME = "config";

    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean commit = sharedPreferences.edit().putString(key, value).commit();
        return commit;
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, "");
        return string;
    }

    public static void putBoolean(Context context, String key, boolean flag) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, flag);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static boolean putSet(Context context, String key, Set<String> set) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putStringSet(key, set).commit();
    }

    public static Set<String> getSet(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        Set<String> set = new HashSet<>();
        return sharedPreferences.getStringSet(key, set);
    }
}
