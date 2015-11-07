package com.leu.littleweather.ui.citymanageui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.ui.BaseActivity;
import com.leu.littleweather.util.StatusBarCompat;

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
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_manager);
        StatusBarCompat.compat(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("管理城市");
        setSupportActionBar(mToolbar);



        mForecastDao = new ForecastDao(this);
        mForecasts = mForecastDao.getAllCity();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //固定的大小
        mRecyclerView.setHasFixedSize(true);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter=new RecyclerViewAdapter(this, mForecasts);
        //设置adapter
        mRecyclerView.setAdapter(adapter);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //创建自定义的ItemTouchHelper.Callback,设置给RecycleView
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }
    //也是在适配器中实现，即适配器中调用活动里的方法。手动触发拖拽动作
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            sendbackMessage();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            sendbackMessage();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void sendbackMessage(){
        List<Integer> deleteItem =adapter.getmDeleteItem();
        if (deleteItem.size()>0) {
            int[] a = new int[deleteItem.size()];
            Intent intent = new Intent();
            //list无法直接转为array，需要手动转换。
            for (int i = 0; i < deleteItem.size(); i++) {
                a[i] = deleteItem.get(i);
            }
            intent.putExtra("city_delete", a);
            setResult(RESULT_OK, intent);
        }
    }
}
