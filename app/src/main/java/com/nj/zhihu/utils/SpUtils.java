package com.nj.zhihu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;

import com.nj.zhihu.ui.activity.BaseActivity;

/**
 * SP工具类
 * Created by Administrator on 2018-06-29.
 */

public class SpUtils {

    /**
     * 保存数据
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        //此方法默认生成一个名为‘包名_preferences’的文件
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof String) {
            editor.putString(key, (String) value);
        }else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }

        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    /**
     * 获取保存的数据
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(Context context, String key, Object defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, (String) defaultValue);
        }else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        }else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    /**
     * 判断是否包含某个key
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.contains(key);
    }
}
