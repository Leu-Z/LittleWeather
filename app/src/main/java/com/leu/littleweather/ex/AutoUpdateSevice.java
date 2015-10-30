package com.leu.littleweather.ex;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.biz.ForecastBiz;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.util.CommonUtil;

import java.util.List;

/**
 * Created by Leu on 2015/10/30.
 */
public class AutoUpdateSevice extends Service {

    private ForecastDao mForecastDao;
    private ForecastBiz mForecastBiz;
    private boolean first=true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mForecastDao = new ForecastDao(this);
        mForecastBiz = new ForecastBiz(this);
        Log.d("AutoUpdateSevice","自动更新服务开启");
    }

    //配合一个广播接收器，实现定时循环
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //得到设置的时间
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int update =Integer.parseInt(sharedPreferences.getString("update_gap", "2"));

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //设定一个小时
        int anHour = update*60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        //定时到后开启一个广播,这里也没注册广播什么的，应该也没进行发送，就是定时到后直接启动了广        //播接收器了
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        //每循环一遍就会更新一次数据,除了第一次进入。
        if (first) {
            first=false;
        }else {
            updateAllWeather();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateAllWeather(){
          List<Forecast> forecasts=mForecastDao.getAllCity();
        //把所有的城市数据都更新一遍。
        for (int i = 0; i <forecasts.size() ; i++) {
            String id=forecasts.get(i).getCity_id();
            mForecastBiz.getRefreshForecast(id, CommonUtil.IsNetAvailable(this),true);
        }
    }

}
