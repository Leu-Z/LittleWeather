package com.leu.littleweather.util;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
/**
 * 一个活动的管理类
 * Created by Leu on 2015/9/8.
 */
public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<Activity>();
    //添加一个活动
    public static void addActivity(Activity activity){
        if(!activities.contains(activity)){
            activities.add(activity);
        }
    }
    //移除一个活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //一键销毁所有活动
    public static void finishAll(){
        for (Activity activity:activities){
            //如果活动没有销毁，那么就进行销毁
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}