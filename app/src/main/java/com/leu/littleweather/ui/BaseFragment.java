package com.leu.littleweather.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.leu.littleweather.dao.ForecastDao;

/**
 * Created by Leu on 2015/9/4.
 */
public class BaseFragment extends Fragment{

    protected ForecastDao mFrecastDao;
    private final static String IMAGEURL="http://files.heweather.com/cond_icon/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrecastDao = new ForecastDao(getActivity());
    }

    public Uri getImageUri(String code){
         return Uri.parse(IMAGEURL+code+".png");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFrecastDao != null){
            //TODO:关闭helper
        }
    }

}
