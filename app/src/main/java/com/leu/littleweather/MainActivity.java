package com.leu.littleweather;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //设置菜单回调
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        //设置toolbar的左上角图标
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        //设置抽屉里item的监听
        setupDrawerContent(mNavigationView);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_item_control_city:
                                Toast.makeText(MainActivity.this,"control_city",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.drawer_item_setting:
                                Toast.makeText(MainActivity.this,"setting",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.drawer_item_about:
                                Toast.makeText(MainActivity.this,"about",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    //菜单选项的回调
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_city:
                Toast.makeText(MainActivity.this,"添加城市",Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


}
