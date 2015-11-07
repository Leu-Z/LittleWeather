package com.leu.littleweather.ui.fragmentui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leu on 2015/9/4.
 */
public class OutterPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG ="OutterPagerAdapter";
    private int mSize;
    private List<String> mCityIds;
    //记录每个Fragment的tag
    private List<String> tagList;
    private long baseId = 0;
    public OutterPagerAdapter(FragmentManager fm,List<String> list) {
        super(fm);
        mCityIds=list;
        mSize = list == null ? 0 : list.size();
        tagList=new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return mSize;
    }
    //只有通过tag查找碎片的时候没找到，才会调用这个方法。从这个方法里得到fragment然后add进
    //ViewPager中。
    @Override
    public Fragment getItem(int position) {
        //传入城市id新建一个碎片
        return OutterFragment.newInstance(mCityIds.get(position));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //把fragment对应的标签存储在taglist集合里
        String tag=makeFragmentName(container.getId(), (int) getItemId(position));
        //如果list中不存在该tag
        if (!tagList.contains(tag)){
            //如果该位置还是空的，那么就加入新的tag
            if (position>=tagList.size()){
                tagList.add(tag);
            }else {
            //如果该位置不是空的，那么就以旧换新。
             tagList.set(position,tag);
            }
        }
        return super.instantiateItem(container, position);
    }
    //FragmentPageAdapter源码里打fragment标签的方法
    public static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }
    //通过序列找到那个Fragment的tag。
    public String getFragmentTag(int item){
        return tagList.get(item);
    }
    //当删除了某个碎片后，用于查找碎片的tag当然也要做相应的删除。
    public void deleteListTag(int index){
        tagList.remove(index);
    }
    //this is called when notifyDataSetChanged() is called
    //重建所有item，新建item的时候会调用instantiateItem。
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }
    //每次在instantiateItem中调用这个的时候，都会是不同的id。所以都会重新调用getItem
    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }
    /**
     * Notify that the position of a fragment has been changed.
     * Create a new ID for each position to force recreation of the fragment
     *
     */
    public void notifyChangeInPosition() {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += getCount() ;
    }
    // 自己写的一个方法用来添加数据
    public void setList(List<String> list) {
        this.mCityIds = list;
        mSize = list == null ? 0 : list.size();
    }


}
