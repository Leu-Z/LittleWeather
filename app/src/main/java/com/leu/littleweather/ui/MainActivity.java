package com.leu.littleweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.biz.ForecastBiz;
import com.leu.littleweather.dao.ForecastDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private ForecastDao mforecastDao;
    private ForecastBiz forecastBiz;
    private List<Forecast> mForecasts;
    private ViewPager mOutterViewPager;
    private ArrayList<OutterFragment> mFragmentList;
    private List<Integer> mIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mforecastDao=new ForecastDao(this);
        initView();
        initViewPager();
        forecastBiz=new ForecastBiz(this);

    }




    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //设置菜单回调
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        //设置toolbar的左上角图标
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

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_item_control_city:
                                break;
                            case R.id.drawer_item_setting:
                                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.drawer_item_about:
                                Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(MainActivity.this, CitySettingActivity.class);
                    startActivity(intent);
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

    private void initViewPager(){
        mOutterViewPager = (ViewPager) findViewById(R.id.outterViewpager);
        //设置viewpager切换动画
        mOutterViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        mFragmentList = new ArrayList<OutterFragment>();
        mForecasts=mforecastDao.getAllCity();

        for (int i = 0; i < mForecasts.size(); i++) {
            mFragmentList.add(OutterFragment.newInstance(mForecasts.get(i).getCity()));

        }

        mOutterViewPager.setAdapter(new OutterPagerAdapter(getSupportFragmentManager(), mFragmentList));
        mOutterViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //更换城市名称
                mToolbar.setTitle(mForecasts.get(position).getCity());

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }


}





