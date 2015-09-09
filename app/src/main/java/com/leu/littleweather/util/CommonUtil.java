package com.leu.littleweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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



}
