package com.leu.littleweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leu.littleweather.util.ActivityCollector;

/**
 *
 * Created by Leu on 2015/8/11.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每个继承该活动的子活动创建都都会打印出自己的名字
        Log.d("BaseActivity", getClass().getSimpleName());
        //每个继承的活动创建时都会把自己加入集合
        ActivityCollector.addActivity(this);
    }
    //销毁时把自己从集合中移除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}