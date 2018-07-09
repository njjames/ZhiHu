package com.nj.zhihu.ui.fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.nj.zhihu.App;
import com.nj.zhihu.R;

/**
 * Created by Administrator on 2018-07-08.
 */

public class SettingFragment extends PreferenceFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        Preference clearCache = findPreference("clear_cache");
        CheckBoxPreference noImageMode = (CheckBoxPreference) findPreference("NO_IMAGE_MODE");
        //这里也不用写，会自动根据存储的显示
//        if ((boolean) SpUtils.get(App.getContext(), "NO_IMAGE_MODE", false)) {
//            noImageMode.setDefaultValue(true);
//        }else {
//            noImageMode.setDefaultValue(false);
//        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        switch (preference.getKey()) {
            case "clear_cache":
                Toast.makeText(App.getContext(), "清除缓存成功", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
