package com.leu.littleweather.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;

import java.util.List;

/**
 * Created by Leu on 2015/9/4.
 */
public class CityManageActivity extends BaseActivity implements OnStartDragListener{


    private RecyclerView mRecyclerView;
    private ForecastDao mForecastDao;
    private List<Forecast> mForecasts;
    private Toolbar mToolbar;
    private ItemTouchHelper touchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_manager);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("管理城市");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(CityManageActivity.this,MainActivity.class));
            }
        });

        mForecastDao = new ForecastDao(this);
        mForecasts = mForecastDao.getAllCity();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //固定的大小
        mRecyclerView.setHasFixedSize(true);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter adapter=new HomeAdapter(this, mForecasts);
        //设置adapter
        mRecyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //创建自定义的callback
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}
