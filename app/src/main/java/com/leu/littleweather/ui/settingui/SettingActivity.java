package com.leu.littleweather.ui.settingui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leu.littleweather.R;
import com.leu.littleweather.ui.BaseActivity;
import com.leu.littleweather.util.StatusBarCompat;

/**
 * Created by Leu on 2015/10/29.
 */
public class SettingActivity extends BaseActivity{
    private static final String TAG = "SettingActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        StatusBarCompat.compat(this);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);


        getFragmentManager().beginTransaction().replace(R.id.setting, new SettingFragment()).commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
