package com.leu.littleweather.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.leu.littleweather.bean.Forecast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leu on 2015/8/31.
 * 对天气预报的数据库操作,每个表一般我们都会单独写个Dao用于操作
 * 利用dao来对数据库进行添加，删除，查询等操作
 */
public class ForecastDao {
    //这里dao里面定义了很多对数据库操作的方法，直接拿来用就好了
    private Dao<Forecast,Integer> mForecastDao;
    private DataBaseHelper mDataBaseHelpter;

    @SuppressWarnings("unchecked")
    public ForecastDao(Context context) {
        try {
            mDataBaseHelpter = DataBaseHelper.getHelper(context);
            //得到了forecastRuntimeDao，用于各种数据库操作
            mForecastDao = mDataBaseHelpter.getDao(Forecast.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个Forecast,或者更新
     * @param forecast
     */
    public void addOrUpdate(Forecast forecast)
    {
        try
        {   //插入一条数据,如果指定id则更新
            mForecastDao.createOrUpdate(forecast);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 按照城市删除
     * @param   city 需要删除的城市
     * @return
     * @throws SQLException
     */
    public int deleteByCity(String city) {
        try
        {
             DeleteBuilder<Forecast, Integer> deleteBuilder = mForecastDao.deleteBuilder();
            //根据city列名查找并删除
            deleteBuilder.where().eq("city", city);
            return deleteBuilder.delete();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;

    }

    /**
     * 删除数据库中所有的Forecast,清缓存时用到
     */
    public void deleteAll()  {
        try
        {
            mForecastDao.delete(getAllCity());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据库中所有的Forecast,并返回
     */
    public List<Forecast> getAllCity()  {
        try
        {
            List<Forecast> weathers = mForecastDao.queryForAll();
            return weathers;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 按照城市查询,得到一个指定城市天气
     * @param city
     * @return
     * @throws SQLException
     */
    public Forecast getForecastByCity(String city)  {
        try
        {
            //把相同标题的都查出来
            List<Forecast> weathers = mForecastDao.queryBuilder().where().eq("city",city).query();
            //查到了就返回第一个
            if (weathers.size() > 0){
                return weathers.get(0);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据库里所有的id
     * @return
     */
    public List<Integer> getAllId(){
        List<Integer> ids=new ArrayList<Integer>();
      SQLiteDatabase db=mDataBaseHelpter.getReadableDatabase();
        Cursor cursor=db.query("tb_forecast", new String[]{"id"},null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(cursor.getColumnIndex("id"));
                ids.add(id);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return ids;
    }


    /**
     * 根据id查找天气
     * @param id
     * @return
     */
    public Forecast getForecastById(int id){
        try
        {
            //Forecast forecast= mForecastDao.queryForId();
            //把相同标题的都查出来
            List<Forecast> weathers = mForecastDao.queryBuilder().where().eq("id",id).query();
            //查到了就返回第一个
            if (weathers.size() > 0){
                return weathers.get(0);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();

        }
        return null;

    }









    /**
     * 得到数据库中的城市数量
     * @return
     */
    public int getCityCount()  {

        try {
            List<Forecast> forecasts=mForecastDao.queryForAll();
            if (forecasts!=null) {
                return forecasts.size();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



}
