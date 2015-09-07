package com.leu.littleweather.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.leu.littleweather.bean.Forecast;

import java.sql.SQLException;

/**
 *
 * Created by Administrator on 2015/2/24.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "littleWeather.db";
    private static final int DATABASE_VERSION = 1;

    private static DataBaseHelper helper = null;

    private DataBaseHelper(Context context) {
        //数据库的名字和版本号
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 获取helper对象
     * @param context
     * @return helper对象
     */
    public static synchronized DataBaseHelper getHelper(Context context){
        if (helper == null){
            synchronized (DataBaseHelper.class)
            {
                if (helper == null)
                    helper = new DataBaseHelper(context);
            }
        }
        return helper;
    }

    //创建数据库表，得到dao实例
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            //创建天气预报表
            TableUtils.createTable(connectionSource, Forecast.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            //更新数据库，要先把原来的删除
            TableUtils.dropTable(connectionSource, Forecast.class, true);
            //重新创建一个
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //得到Dao实例
    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;

        dao =super.getDao(clazz);

        return dao;
    }






}
