package com.leu.littleweather.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.leu.littleweather.biz.ForecastBiz;

/**
 * Created by Leu on 2015/9/4.
 */
public class BaseFragment extends Fragment{

    protected ForecastBiz mForecastBiz;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecastBiz = new ForecastBiz(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mForecastBiz != null){
            //TODO:关闭helper
        }
    }

}
