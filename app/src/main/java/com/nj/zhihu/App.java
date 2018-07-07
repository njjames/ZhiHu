package com.nj.zhihu;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.nj.zhihu.utils.SpUtils;

/**
 * Created by Administrator on 2018-07-07.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        boolean nightMode = (boolean) SpUtils.get(mContext, "night_mode", false);
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
