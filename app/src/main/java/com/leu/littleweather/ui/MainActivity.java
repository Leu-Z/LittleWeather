package com.leu.littleweather.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.ui.cityaddui.CityAddActivity;
import com.leu.littleweather.ui.citymanageui.CityManageActivity;
import com.leu.littleweather.ui.fragmentui.OutterFragment;
import com.leu.littleweather.ui.fragmentui.OutterPagerAdapter;
import com.leu.littleweather.util.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    protected Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private ForecastDao mforecastDao;
    private ViewPager mOutterViewPager;
    private final static String TAG = "MainActivity";
    private boolean mNewCityAdd;
    private OutterPagerAdapter mAdapter;
    private String mNewCityName;
    private android.support.v4.app.FragmentManager fm;
    private boolean mCityDeltete;
    private int[] mDeleteCityArry;
    private ArrayList<String> mCityIds;
    public SwipeRefreshLayout mSwipeLayout;
    private ArrayList<String> mCityName;
    private String mNewCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        //StatusBarCompat.compat(this);
        mforecastDao = new ForecastDao(this);
        initView();
        initViewPager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        //顺便可以用来刷新界面
        if (mNewCityAdd) {
            updateUiAdd();
            mNewCityAdd = false;
        }
        if (mCityDeltete) {
            updateUIDelete();
            mCityDeltete = false;
        }

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        List<Forecast> forecasts = mforecastDao.getAllCity();
        mCityIds = new ArrayList<String>();
        mCityName=new ArrayList<String>();

        //得到所有的城市id
        for (int i = 0; i < forecasts.size(); i++) {
            mCityIds.add(forecasts.get(i).getCity_id());
            mCityName.add(forecasts.get(i).getCity());
        }
        //如果城市为0，就跳转到添加城市页面
        if (mCityIds.size()<=0){
            Intent intent = new Intent(MainActivity.this, CityAddActivity.class);
            startActivityForResult(intent, 1);
        }
        //设置初始的Toolbar城市名
        if (mCityIds.size() != 0) {
            mToolbar.setTitle(mCityName.get(0));
        }
        setSupportActionBar(mToolbar);
        //设置菜单回调
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        //设置toolbar的左上角点击弹出图标
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        //设置抽屉里item的监听
        setupDrawerContent(mNavigationView);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        //设置下拉刷新监听事件
        mSwipeLayout.setOnRefreshListener(this);
        //设置进度条的颜色
        mSwipeLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        //设置进度条背景颜色
        //mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.DKGRAY);
        //设置下拉多少距离之后开始刷新数据
        mSwipeLayout.setDistanceToTriggerSync(50);


    }

    //下拉刷新操作
    @Override
    public void onRefresh() {
        mSwipeLayout.setRefreshing(true);
        //得到当前页的fragment实例
        String fragmentTag = mAdapter.getFragmentTag(mOutterViewPager.getCurrentItem());
        OutterFragment outterFragment = (OutterFragment) fm.findFragmentByTag(fragmentTag);
        //从网络获取数据后刷新界面。
        outterFragment.update();
        //刷新结束
        mSwipeLayout.setRefreshing(false);
    }

    //抽屉点击事件
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_item_control_city:
                                Intent intent = new Intent(MainActivity.this, CityManageActivity.class);
                                startActivityForResult(intent, 2);
                                break;
                            case R.id.drawer_item_setting:
                                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.drawer_item_about:
                                Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
    }

    //ToolBar上面菜单选项的回调
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_city:
                    Intent intent = new Intent(MainActivity.this, CityAddActivity.class);
                    startActivityForResult(intent, 1);
                    break;
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initViewPager() {
        mOutterViewPager = (ViewPager) findViewById(R.id.outterViewpager);
        //设置viewpager切换动画
        mOutterViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        fm = getSupportFragmentManager();
        mAdapter = new OutterPagerAdapter(fm, mCityIds);
        mOutterViewPager.setAdapter(mAdapter);
        mOutterViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //更换城市名称
                mToolbar.setTitle(mCityName.get(position));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //即滚动的时候，设置下拉刷新不可用，限制没进行横向滚动的时候可用刷新
                enableDisableSwipeRefresh(arg0 == ViewPager.SCROLL_STATE_IDLE);
            }
        });
    }
    //ViewPager闲置时，即没有滑动的时候使能下拉刷新。滑动的时候就禁用下拉刷新了。
    public void enableDisableSwipeRefresh(boolean enable) {
        if (mSwipeLayout != null) {
            mSwipeLayout.setEnabled(enable);
        }
    }
    public void enableSwipeRefresh(){
        mSwipeLayout.setEnabled(true);
    }
    public void DisableSwipeRefresh(){
        mSwipeLayout.setEnabled(false);
    }


    //这个是增加一个城市的更新方法。
    private void updateUiAdd() {
        mCityIds.add(mNewCityId);
        mCityName.add(mNewCityName);
        mAdapter.setList(mCityIds);
        mAdapter.notifyDataSetChanged();
        //增加城市后，立马切换到那个城市页面上去。
        mOutterViewPager.setCurrentItem(mCityIds.size() - 1);
    }

    //这个是删除一个城市的更新方法。
    private void updateUIDelete() {
        //移除选中的城市。
        for (int i = 0; i < mDeleteCityArry.length; i++) {
            mCityIds.remove(mDeleteCityArry[i]);
            mCityName.remove(mDeleteCityArry[i]);
        }
        //如果删除之后的城市没了，就跳转增加
        if (mCityIds.size()<=0){
            Intent intent = new Intent(MainActivity.this, CityAddActivity.class);
            startActivityForResult(intent, 1);
        }
        mAdapter.setList(mCityIds);
        mAdapter.notifyChangeInPosition(1);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    //CityAddActivity销毁之后回调这个方法。
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从CityAddActivity返回的数据
            case 1:
                if (resultCode == RESULT_OK) {
                    mNewCityAdd = true;
                    mNewCityName = data.getStringExtra("city_name");
                    mNewCityId=data.getStringExtra("city_id");
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    mCityDeltete = true;
                    mDeleteCityArry = data.getIntArrayExtra("city_delete");
                }
                break;
        }
    }

}





