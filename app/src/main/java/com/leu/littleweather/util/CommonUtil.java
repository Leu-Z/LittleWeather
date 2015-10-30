package com.leu.littleweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;

/**
 * Created by Leu on 2015/9/4.
 */
public class CommonUtil {
    /**
     * 当前是否有网络连接
     * @return
     */
    public static boolean IsNetAvailable(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }

    /**
     *
     * @param cityId
     * @return
     */
    public static boolean ifCityExist(String cityId,Context context){

        ForecastDao mforecastDao=new ForecastDao(context);

        Forecast forecast=mforecastDao.getForecastByCityid(cityId);
        if (forecast!=null){
            Toast.makeText(context, "该城市已存在", Toast.LENGTH_SHORT).show();
            return true;
        }else {

            return false;
        }

    }



}
