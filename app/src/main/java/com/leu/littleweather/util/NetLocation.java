package com.leu.littleweather.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by Leu on 2015/10/18.
 */
public class NetLocation  {

    public static void getLocation(Context context){
       // 初始化定位，只采用网络定位
        LocationManagerProxy mLocationManagerProxy = LocationManagerProxy.getInstance(context);
       //把gps定位设置为false
       mLocationManagerProxy.setGpsEnable(false);

       //注册监听的方法
       //参数1为定位方式，这里用高德定位方式，参数2是位置变化的通知时间，单位为毫秒。如果为-1，定位只定位一次。
       //参数3是位置变化通知距离，这里网络定位没意义。参数4是listener:定位监听者。
       mLocationManagerProxy.requestLocationData(
               LocationProviderProxy.AMapNetwork, -1, 15, new AMapLocationListener() {
                   @Override
                   public void onLocationChanged(AMapLocation amapLocation) {
                       if (amapLocation != null
                               && amapLocation.getAMapException().getErrorCode() == 0) {
                          /* //地级市
                           mLocationCityTextView.setText(amapLocation.getCity());
                           //县级市
                           mLocationCountyTextView.setText(amapLocation.getDistrict());*/

                       } else {
                           Log.e("AmapErr", "Location ERR:" + amapLocation.getAMapException().getErrorCode());
                       }
                   }

                   @Override
                   public void onLocationChanged(Location location) {

                   }

                   @Override
                   public void onStatusChanged(String provider, int status, Bundle extras) {

                   }

                   @Override
                   public void onProviderEnabled(String provider) {

                   }

                   @Override
                   public void onProviderDisabled(String provider) {

                   }
               });


   }
}
