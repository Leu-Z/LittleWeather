package com.leu.littleweather.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;

/**
 * Created by Leu on 2015/9/4.
 */
public class MiddleFragment extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private ViewPager mViewPager;

    public static MiddleFragment newInstance(String city) {
        MiddleFragment fragment = new MiddleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = getArguments().getString(ARG_CITY);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.middle_fragment, container, false);
        TextView nowTmp= (TextView) view.findViewById(R.id.now_tmp);
        ImageView image= (ImageView) view.findViewById(R.id.image_d);
        TextView describe= (TextView) view.findViewById(R.id.describe);
        TextView bodyTmp= (TextView) view.findViewById(R.id.body_tmp);
        TextView hum= (TextView) view.findViewById(R.id.hum);
        TextView wind= (TextView) view.findViewById(R.id.wind);
        TextView level= (TextView) view.findViewById(R.id.level);

        Forecast forecast =mFrecastDao.getForecastByCity(mCity);
        nowTmp.setText(forecast.getTmp());
        image.setImageURI(getImageUri(forecast.getNow_code()));
        describe.setText(forecast.getNow_txt());
        bodyTmp.setText(forecast.getFl());
        hum.setText(forecast.getHum());
        wind.setText(forecast.getDir());
        level.setText(forecast.getSc());

        return view;
    }


}
