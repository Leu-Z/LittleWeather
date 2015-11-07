package com.leu.littleweather.ui.fragmentui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.ui.BaseFragment;

/**
 * Created by Leu on 2015/9/4.
 */
public class MiddleFragment extends BaseFragment {
    private static final String ARG_CITY = "cityid";
    private static final String TAG="MiddleFragment";
    private String mCityid;

    public static MiddleFragment newInstance(String cityid) {
        MiddleFragment fragment = new MiddleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, cityid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        Log.d(TAG, mCityid + "onStart");
        super.onStart();

    }

    @Override
    public void onResume() {
        Log.d(TAG, mCityid + "onResume");
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityid = getArguments().getString(ARG_CITY);
        }
        Log.d(TAG, mCityid + "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, mCityid+"onCreateView");
        View view = inflater.inflate(R.layout.middle_fragment, container, false);
        TextView nowTmp = (TextView) view.findViewById(R.id.now_tmp);
        SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.image_d);
        TextView describe = (TextView) view.findViewById(R.id.describe);
        TextView bodyTmp = (TextView) view.findViewById(R.id.body_tmp);
        TextView hum = (TextView) view.findViewById(R.id.hum);
        TextView wind = (TextView) view.findViewById(R.id.wind);
        TextView level = (TextView) view.findViewById(R.id.level);
        TextView update = (TextView) view.findViewById(R.id.update);
        //只要是第一次进来都不设置UI，必须要更新来设置UI

        Forecast forecast = mFrecastDao.getForecastByCityid(mCityid);
        nowTmp.setText(forecast.getTmp());
        image.setImageURI(getImageUri(forecast.getNow_code()));
        describe.setText(forecast.getNow_txt());
        bodyTmp.setText(forecast.getFl());
        hum.setText(forecast.getHum());
        wind.setText(forecast.getDir());
        level.setText(forecast.getSc());
        update.setText(forecast.getUpdate_time());

        return view;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, mCityid + "onDestroy");
        super.onDestroy();


    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, mCityid + "onDestroyView");
        super.onDestroyView();

    }

    public void onPause() {
        Log.d(TAG, mCityid + "onPause");
        super.onPause();

    }

    @Override
    public void onStop() {
        Log.d(TAG, mCityid + "onStop");
        super.onStop();

    }


}
