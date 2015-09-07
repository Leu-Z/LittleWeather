package com.leu.littleweather.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Leu on 2015/9/4.
 */
public class OutterPagerAdapter extends FragmentPagerAdapter {
    List<OutterFragment> mList;
    public OutterPagerAdapter(FragmentManager fm,List<OutterFragment> list) {
        super(fm);
       mList=list;


    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }
}
