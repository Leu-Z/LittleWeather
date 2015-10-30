package com.leu.littleweather.ex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Leu on 2015/10/30.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //在广播接收器里面又重新打开服务，再次定时一小时，形成了循环
        Intent i = new Intent(context, AutoUpdateSevice.class);
        context.startService(i);
    }
}
