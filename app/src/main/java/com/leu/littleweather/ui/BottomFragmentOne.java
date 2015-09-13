package com.leu.littleweather.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;

/**
 * Created by Leu on 2015/9/13.
 */
public class BottomFragmentOne extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private SimpleDraweeView imageD;
    private TextView tmpD;
    private SimpleDraweeView imageN;
    private TextView tmpN;

    public static BottomFragmentOne newInstance(String city) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment_one, container, false);

        imageD = (SimpleDraweeView) view.findViewById(R.id.image_d);
        tmpD = (TextView) view.findViewById(R.id.tmp_d);
        imageN = (SimpleDraweeView) view.findViewById(R.id.image_n);
        tmpN = (TextView) view.findViewById(R.id.tmp_n);

        Log.d("Bottom Fragment One", "run");
        setUI();
        return view;
    }

    public void setUI() {
        Forecast forecast = mFrecastDao.getForecastByCity(mCity);
        imageD.setImageURI(getImageUri(forecast.getDaily_1_code_d()));
        tmpD.setText(forecast.getDaily_1_max());
        imageN.setImageURI(getImageUri(forecast.getDaily_1_code_n()));
        tmpN.setText(forecast.getDaily_1_min());
    }
}
