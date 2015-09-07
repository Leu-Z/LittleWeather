package com.leu.littleweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 *
 * Created by Leu on 2015/8/11.
 */
public class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每个继承自这个活动的子活动都会Log自己的名字
        Log.d("BaseActivity",getClass().getSimpleName());
    }

}
