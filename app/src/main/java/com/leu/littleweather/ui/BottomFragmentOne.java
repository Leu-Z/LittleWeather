package com.leu.littleweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;

/**
 * Created by Leu on 2015/9/6.
 */
public class BottomFragmentOne extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private TextView tmpD;
    private TextView tmpN;
    private SimpleDraweeView imageD;
    private SimpleDraweeView imageN;

    public static BottomFragmentOne newInstanceOne(String city) {

        BottomFragmentOne fragment = new BottomFragmentOne();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_fragment_one, container, false);
        tmpD= (TextView) view.findViewById(R.id.tmp_d);
         tmpN = (TextView) view.findViewById(R.id.tmp_n);
        imageD= (SimpleDraweeView) view.findViewById(R.id.image_d);
        imageN= (SimpleDraweeView) view.findViewById(R.id.image_n);
        setUI();
        return view;
    }
    //用来设置UI，更新UI时重新从数据库获取数据，进行设置
    public void setUI(){
        Forecast forecast = mFrecastDao.getForecastByCity(mCity);
        tmpD.setText(forecast.getDaily_1_max());
        tmpN.setText(forecast.getDaily_1_min());
        imageD.setImageURI(getImageUri(forecast.getDaily_1_code_d()));
        imageN.setImageURI(getImageUri(forecast.getDaily_1_code_n()));
    }





}
