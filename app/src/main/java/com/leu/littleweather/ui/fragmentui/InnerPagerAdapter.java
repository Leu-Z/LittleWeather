package com.leu.littleweather.ui.fragmentui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Leu on 2015/9/3.
 */
public class InnerPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public InnerPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        fragments=list;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
