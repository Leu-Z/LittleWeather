package com.leu.littleweather.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leu.littleweather.R;

/**
 * Created by Leu on 2015/9/4.
 */
public class MiddleFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;
    private ViewPager mViewPager;

    public static MiddleFragment newInstance(int newsType) {
        MiddleFragment fragment = new MiddleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, newsType);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return  inflater.inflate(R.layout.middle_fragment, container, false);

    }


}
