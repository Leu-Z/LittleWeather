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
 * Created by Leu on 2015/9/13.
 */
public class BottomFragmentOne extends BaseFragment {
    private static final String ARG_CITY = "cityid";
    private static final String TAG="BottomFragmentOne";
    private String mCityId;
    private SimpleDraweeView imageD;
    private TextView tmpD;
    private SimpleDraweeView imageN;
    private TextView tmpN;

    public static BottomFragmentOne newInstance(String cityid) {
        BottomFragmentOne fragment = new BottomFragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, cityid);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onStart() {
        Log.d(TAG, mCityId + "onStart");
        super.onStart();

    }

    @Override
    public void onResume() {
        Log.d(TAG, mCityId + "onResume");
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityId = getArguments().getString(ARG_CITY);

        }
        Log.d(TAG, mCityId + "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, mCityId+"onCreateView");
        View view = inflater.inflate(R.layout.bottom_fragment_one, container, false);

        imageD = (SimpleDraweeView) view.findViewById(R.id.image_d);
        tmpD = (TextView) view.findViewById(R.id.tmp_d);
        imageN = (SimpleDraweeView) view.findViewById(R.id.image_n);
        tmpN = (TextView) view.findViewById(R.id.tmp_n);

        setUI();
        return view;
    }

    public void setUI() {
        Forecast forecast = mFrecastDao.getForecastByCityid(mCityId);
        imageD.setImageURI(getImageUri(forecast.getDaily_1_code_d()));
        tmpD.setText(forecast.getDaily_1_max());
        imageN.setImageURI(getImageUri(forecast.getDaily_1_code_n()));
        tmpN.setText(forecast.getDaily_1_min());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, mCityId + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, mCityId + "onDestroyView");
        super.onDestroyView();
    }
    @Override
    public void onPause() {
        Log.d(TAG, mCityId + "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, mCityId+"onStop");
        super.onStop();
    }
}
