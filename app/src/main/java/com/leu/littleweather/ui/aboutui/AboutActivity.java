package com.leu.littleweather.ui.aboutui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.leu.littleweather.R;
import com.leu.littleweather.ui.BaseActivity;
import com.leu.littleweather.util.StatusBarCompat;

/**
 * Created by Leu on 2015/10/31.
 */
public class AboutActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        StatusBarCompat.compat(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("关于小天气");
        setSupportActionBar(mToolbar);
        //设置菜单回调
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
    }
    //按下返回键结束活动
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    //ToolBar上面菜单选项的回调
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.share:
                         share();
                    break;
            }
            return true;
        }
    };

    public void share(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.setPackage("com.sina.weibo");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "小天气是一个相当简洁的天气，没有任何多余的功能，让天气" +
                "应用回归本质。本应用由Leu_Z个人开发，如果使用中有任何想与开发者交流的问题，请加QQ：512179501");
        intent.putExtra(Intent.EXTRA_TITLE, "我是标题");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "请选择"));
    }
}
