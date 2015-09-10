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
public class LeftFragment extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private ViewPager mViewPager;

    public static LeftFragment newInstance(String city) {
        LeftFragment fragment = new LeftFragment();
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
        View view = inflater.inflate(R.layout.left_fragment, container, false);

        TextView comfBrf = (TextView) view.findViewById(R.id.comf_brf);
        TextView comfTxt= (TextView) view.findViewById(R.id.comf_txt);
        TextView cwBrf = (TextView) view.findViewById(R.id.cw_brf);
        TextView cwTxt= (TextView) view.findViewById(R.id.cw_txt);
        TextView drsgBrf = (TextView) view.findViewById(R.id.drsg_brf);
        TextView drsgTxt= (TextView) view.findViewById(R.id.drsg_txt);
        TextView flufBrf = (TextView) view.findViewById(R.id.flu_brf);
        TextView flufTxt= (TextView) view.findViewById(R.id.flu_txt);
        TextView sportfBrf = (TextView) view.findViewById(R.id.sport_brf);
        TextView sportTxt= (TextView) view.findViewById(R.id.sport_txt);
        TextView travBrf = (TextView) view.findViewById(R.id.trav_brf);
        TextView travTxt= (TextView) view.findViewById(R.id.trav_txt);
        TextView uvBrf = (TextView) view.findViewById(R.id.uv_brf);
        TextView uvTxt= (TextView) view.findViewById(R.id.uv_txt);

        Forecast forecast =mFrecastDao.getForecastByCity(mCity);
        comfBrf.setText(forecast.getComf_brf());
        comfTxt.setText(forecast.getComf_txt());
        cwBrf.setText(forecast.getCw_brf());
        cwTxt.setText(forecast.getCw_txt());
        drsgBrf.setText(forecast.getDrsg_brf());
        drsgTxt.setText(forecast.getDrsg_txt());
        flufBrf.setText(forecast.getFlu_brf());
        flufTxt.setText(forecast.getFlu_txt());
        sportfBrf.setText(forecast.getSport_brf());
        sportTxt.setText(forecast.getSport_txt());
        travBrf.setText(forecast.getTrav_brf());
        travTxt.setText(forecast.getTrav_txt());
        uvBrf.setText(forecast.getUv_brf());
        uvTxt.setText(forecast.getUv_txt());

        return view;

    }
}
