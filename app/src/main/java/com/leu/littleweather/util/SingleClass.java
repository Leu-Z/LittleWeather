package com.leu.littleweather.util;

import android.content.Context;

import com.leu.littleweather.bean.GroupMemberBean;

import java.util.List;

/**
 * Created by Leu on 2015/10/30.
 */
public class SingleClass {
    //该单例自己指向自己，所以永远不会被回收
    private static SingleClass sCrimeLab;  // 静态变量以s前缀标示
    //这个comtext是application级别的
    private Context mAppContext;

    public List<GroupMemberBean> getGroupMemberBeanList() {
        return groupMemberBeanList;
    }

    public void setGroupMemberBeanList(List<GroupMemberBean> groupMemberBeanList) {
        this.groupMemberBeanList = groupMemberBeanList;
    }

    private List<GroupMemberBean> groupMemberBeanList;
    //一个私有的构造函数
    private SingleClass(Context appContext) {
        mAppContext = appContext;
    }
    //只能通过静态方法得到或者新建该单例
    public static SingleClass getSingleClass(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new SingleClass(c.getApplicationContext());
        }
        return sCrimeLab;
    }

}
