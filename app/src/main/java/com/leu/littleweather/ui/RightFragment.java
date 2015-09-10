package com.leu.littleweather.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;

/**
 * Created by Leu on 2015/9/4.
 */
public class RightFragment extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private ViewPager mViewPager;

    public static RightFragment newInstance(String city) {
        RightFragment fragment = new RightFragment();
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
        View view = inflater.inflate(R.layout.right_fragment, container, false);

        TextView qlty= (TextView) view.findViewById(R.id.qlty);
        TextView aqi= (TextView) view.findViewById(R.id.aqi);
        TextView pm25= (TextView) view.findViewById(R.id.pm25);
        TextView pm10= (TextView) view.findViewById(R.id.pm10);
        TextView so2= (TextView) view.findViewById(R.id.so2);
        TextView no2= (TextView) view.findViewById(R.id.no2);

        Forecast forecast =mFrecastDao.getForecastByCity(mCity);
        qlty.setText(forecast.getQlty());
        aqi.setText(forecast.getAqi());
        pm25.setText(forecast.getPm25());
        pm10.setText(forecast.getPm10());
        so2.setText(forecast.getSo2());
        no2.setText(forecast.getNo2());


        return view;

    }
}
