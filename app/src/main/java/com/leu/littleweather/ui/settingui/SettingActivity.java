package com.leu.littleweather.ui.settingui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.leu.littleweather.R;
import com.leu.littleweather.ex.AutoUpdateSevice;

/**
 * Created by Leu on 2015/10/29.
 */
public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private CheckBoxPreference autoUpdate;
    private ListPreference updateGap;
    private static final String TAG = "SettingActivity";
    private AppCompatDelegate mDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
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
    private void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
