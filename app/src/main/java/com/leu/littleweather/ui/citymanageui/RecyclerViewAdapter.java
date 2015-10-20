package com.leu.littleweather.ui.citymanageui;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemTouchHelperAdapter
{
    private final OnStartDragListener mDragStartListener;
    private final Context mContext;
    private  ForecastDao mForecastDao;
    private List<Forecast> mDatas;
    private LayoutInflater mInflater;
    public List<Integer> mDeleteItem;

    //构造器，获得一个用于构造的list数据
    public RecyclerViewAdapter(Context context, List<Forecast> datas)
    {   mContext=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mForecastDao=new ForecastDao(context);
        mDragStartListener = (OnStartDragListener)context;
        mDeleteItem=new ArrayList<Integer>();
    }
    //创建一个自定义的ViewHolder，设置其默认的item布局,得到内部子视图的引用
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.card_view, parent, false));
        return holder;
    }
    //设置每个item的属性值，position会一直变化，这里的position和pos应该是一样的,从0开始递增
    //感觉holder应该会随着item的更新而变化。
    // 并设置每个item 的点击事件，点击之后回调活动中的方法
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        //给每个子视图设置数据绑定
        holder.tmp.setText(mDatas.get(position).getTmp());
        holder.cityName.setText(mDatas.get(position).getCity());
        holder.weather.setText(mDatas.get(position).getNow_txt());
        //点击这个图标回调onStartDrag方法。
        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //判定动作为按下动作。
            if (MotionEventCompat.getActionMasked(event) ==
                    MotionEvent.ACTION_DOWN) {
                //调用活动中的方法，那里的方法再调用callback中实际的方法。
                mDragStartListener.onStartDrag(holder);
            }
            return false;
        }
    });
    }
    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
    //上下拖动item结束后的回调，处理数据。将会被ItemTouchHelper的callback使用,和下面一样
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //把容器中的两个位置互换
        Collections.swap(mDatas, fromPosition, toPosition);
        //通知适配器改变位置。
        notifyItemMoved(fromPosition, toPosition);
        //把数据库中的城市位置也进行调换。
        mForecastDao.deleteAll();
        for (int i = 0; i < mDatas.size(); i++) {
            mForecastDao.addOrUpdate(mDatas.get(i));
        }
    }
    //左右滑动后结束后的回调,
    @Override
    public void onItemDismiss(int position) {
       //从数据库中彻底删除该城市
        Forecast forecast=mDatas.get(position);
        mForecastDao.deleteByCity(forecast.getCity());
        //容器和适配器都改变
        mDatas.remove(position);
        notifyItemRemoved(position);
        //把删除的城市序列进行回传。
        mDeleteItem.add(position);
        /*Intent intent=new Intent();
        intent.putExtra("city_delete", mDeleteItem.toArray());
        CityManageActivity.instance.setResult(MainActivity.REQUEST_DETAIL, intent);*/

    }

    //自定义ViewHolder，需要设置属性值的内部子视图的引用.这里传进来的view即是子视图引用
    class MyViewHolder extends ViewHolder
    {
        TextView tmp;
        TextView cityName;
        TextView weather;
        ImageView imageView;

        public MyViewHolder(View view)
        {
            super(view);
            imageView= (ImageView) view.findViewById(R.id.image_tag);
            tmp = (TextView) view.findViewById(R.id.tmp);
            cityName= (TextView) view.findViewById(R.id.city_name);
            weather= (TextView) view.findViewById(R.id.weather);

        }


    }
}
