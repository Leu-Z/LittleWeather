package com.leu.littleweather.ui.fragmentui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.bean.GroupMemberBean;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.ui.MainActivity;
import com.leu.littleweather.ui.cityaddui.CityAddActivity;
import com.leu.littleweather.util.SingleClass;

import java.util.List;

/**
 * Created by Leu on 2015/10/28.
 */
public class AddCityDialog extends DialogFragment implements View.OnClickListener {
    private List<GroupMemberBean> SourceDateList;
    private ForecastDao mforecastDao;
    private View view;
    private LocationManagerProxy mLocationManagerProxy;
    private Button ok;
    private String mCity;
    private String mCityId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mforecastDao=new ForecastDao(getActivity());
        view = inflater.inflate(R.layout.add_city_dialog, container);
        Button manual= (Button) view.findViewById(R.id.manual);
        Button auto= (Button) view.findViewById(R.id.auto);
        ok= (Button) view.findViewById(R.id.ok);
        // 初始化定位，只采用网络定位
        mLocationManagerProxy = LocationManagerProxy.getInstance(getActivity());
        //把gps定位设置为false
        manual.setOnClickListener(this);
        auto.setOnClickListener(this);
        ok.setOnClickListener(this);
        //得到List的数据bean
        SingleClass singleClass=SingleClass.getSingleClass(getActivity());
        if (singleClass!=null) {
            SourceDateList = singleClass.getGroupMemberBeanList();
        }

        return view;
    }

    public void onResume() {

        super.onResume();
        getDialog().getWindow().setLayout(900, 700);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.auto:
                final TextView textView= (TextView) view.findViewById(R.id.title);
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
                                    //地级市
                                    String city=amapLocation.getCity();
                                    //县级市
                                    String district=amapLocation.getDistrict();
                                    //可见
                                    ok.setVisibility(View.VISIBLE);
                                    textView.setVisibility(View.VISIBLE);
                                    TextView textView= (TextView) view.findViewById(R.id.location);
                                    textView.setText(district);
                                    //把定位获取的地址和本地城市列表进行对比，进行对应。
                                    //然后把定位到的城市加入数据库。
                                    for (int i = 0; i < SourceDateList.size(); i++) {
                                        //得到地级市名称
                                        String prefecture = SourceDateList.get(i).getPrefecture();
                                        boolean get = false;
                                        //地级市是否匹配,匹配进入
                                        //只要进入了，就一定会匹配到一个城市。
                                        if (city.contains(prefecture)){
                                            String c=null;
                                            int j=i;
                                            do {
                                                //得到县级市名称
                                                c=SourceDateList.get(j).getCity();
                                                if (district.contains(c)){
                                                    mCity=c;
                                                    mCityId=SourceDateList.get(j).getCityCode();
                                                    get=true;
                                                    Log.d("AddCityDialog","定位结束");
                                                    break;
                                                }
                                                j++;
                                                //判断下一个是不是还是属于同一个地级市,不是就退出循环了
                                            }while (prefecture.equals(SourceDateList.get(j).getPrefecture()));
                                            //如果在循环完该地级市之前就得到了城市，就直接退出大循环了。
                                            if (get){
                                                break;
                                            }
                                            //全部循环一遍都没有匹配到的话，那么就是和地级市同名了，第一个。
                                            //addNewCity(SourceDateList.get(i).getCityCode(),SourceDateList.get(i).getCity());
                                            mCity=SourceDateList.get(i).getCity();
                                            mCityId=SourceDateList.get(i).getCityCode();
                                            Log.d("AddCityDialog","定位结束");
                                            break;
                                        }
                                    }

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



                break;
            case R.id.manual:
                Intent intent = new Intent(getActivity(), CityAddActivity.class);
                MainActivity activity = (MainActivity) getActivity();
                activity.startActivityForResult(intent, 1);
                dismiss();
                break;
            case R.id.ok:
                addNewCity(mCityId,mCity);
                dismiss();
                break;
        }
    }
    //添加新城市，并刷新页面。
    private void addNewCity(String cityId,String city){
        if (!ifCityExist(cityId)){
            //往数据库中添加新的空城市。
            Forecast forecast=new Forecast();
            forecast.setCity_id(cityId);
            forecast.setCity(city);
            mforecastDao.addOrUpdate(forecast);

            MainActivity mainActivity= (MainActivity) getActivity();
            mainActivity.setmNewCityId(cityId);
            mainActivity.setmNewCityName(city);
            mainActivity.getmLocationCity().setText(city);
            //获取edit对象
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE).edit();
            //往editor对象中添加各种类型的数据。
            editor.putString("city", city);
            //提交
            editor.commit();


            mainActivity.updateUiAdd();

        }
    }

    /**
     *
     * @param cityId
     * @return
     */
    public boolean ifCityExist(String cityId){

        Forecast forecast=mforecastDao.getForecastByCityid(cityId);
        if (forecast!=null){
            Toast.makeText(getActivity(), "该城市已存在", Toast.LENGTH_SHORT).show();
            return true;
        }else {

            return false;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        // 在单次定位情况下，定位无论成功与否，
        // 都无需调用removeUpdates()方法移除请求，定位sdk内部会移除
        // 移除定位请求
        //mLocationManagerProxy.removeUpdates(this);
        // 销毁定位
        mLocationManagerProxy.destroy();
    }
}
