package com.leu.littleweather.ui.settingui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.leu.littleweather.R;
import com.leu.littleweather.ex.AutoUpdateSevice;

/**
 * Created by Leu on 2015/10/29.
 */
public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private CheckBoxPreference autoUpdate;
    private ListPreference updateGap;
    private static final String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        //获取各个Preference
        autoUpdate = (CheckBoxPreference) findPreference("auto_update");
        updateGap = (ListPreference) findPreference("update_gap");
        //为各个Preference注册监听接口
        autoUpdate.setOnPreferenceChangeListener(this);
        updateGap.setOnPreferenceChangeListener(this);
        updateGap.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (preference == autoUpdate) {
            Log.d(TAG, "是否自动更新改变了");
            SharedPreferences sharedPreferences = preference.getSharedPreferences();
            boolean s = sharedPreferences.getBoolean("auto_update", true);
            Intent intent = new Intent(this, AutoUpdateSevice.class);
            //如果之前自动更新是true,那么就关闭服务，停止自动更新。
            if (s){
                stopService(intent);
            }else {
                //如果之前是关闭的，就开启自动更新
                startService(intent);
            }

        } else if (preference == updateGap) {
            Log.d(TAG, "更新频率改变了");

        } else {
            //如果返回false表示不允许被改变
            return false;
        }
        //返回true表示允许改变
        return true;
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == autoUpdate) {
            Log.d(TAG, "是否自动更新被点击了");
        } else if (preference == updateGap) {
            Log.d(TAG, "更新频率被点击了");
            /*SharedPreferences sharedPreferences = preference.getSharedPreferences();
            String update = sharedPreferences.getString("update_gap", "2");
            preference.setSummary(update + "小时");*/
        }
        //不再调用另外一个点击事件
        return true;
    }
}
