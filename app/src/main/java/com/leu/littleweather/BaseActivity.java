package com.leu.littleweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.balysv.materialmenu.MaterialMenuDrawable;

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

    /**
     * 根据int得出对应的图标状态
     * @param state
     * @return 图标状态，默认为汉堡型
     */
    protected MaterialMenuDrawable.IconState intToState(int state){
        switch (state){
            case 0:
                return MaterialMenuDrawable.IconState.BURGER;
            case 1:
                return MaterialMenuDrawable.IconState.ARROW;
            case 2:
                return MaterialMenuDrawable.IconState.X;
            case 3:
                return MaterialMenuDrawable.IconState.CHECK;
        }

        return MaterialMenuDrawable.IconState.BURGER;
    }
}
