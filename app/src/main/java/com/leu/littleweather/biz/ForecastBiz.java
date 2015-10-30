package com.leu.littleweather.biz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.bean.JsonBean;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.util.GsonRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 主要用于处理天气信息,获取和存入数据库
 * Created by Leu on 2015/9/2.
 */
public class ForecastBiz {

    private final static String HTTPURL = "https://api.heweather.com/x3/weather?cityid=";
    private final static String KRY = "&key=7213d4bcf1a344bc988de12dd7199e3e";
    private static final int OUTOFTIME_MINUTE = 30;     //天气过期时间（分钟）
    private Context mContext;

    private ForecastDao mForecastDao;

    private RequestQueue mQueue;
    private UpdateClickListener fTwoBtnClickListener ;

    public interface UpdateClickListener
    {
       // void setDefaultUI();
        void UpdateUI();
    }
    //设置回调接口
    public  void setUpdateClickListener(UpdateClickListener fTwoBtnClickListener)
    {
        this.fTwoBtnClickListener = fTwoBtnClickListener;
    }

    public ForecastBiz(Context context) {
        mContext = context;
        mForecastDao = new ForecastDao(context);
        mQueue = Volley.newRequestQueue(context);

    }

    /**
     * 查看对象是否过期,作用于Forecast，通过比较更新时间
     * 当前时间30分钟之前的Forecast就算过期了
     *
     * @param t
     * @param <T>
     * @return 如果未过期则返回大于0的数，如果过期则返回小与0的数
     */
    public <T> int isOutOfTime(T t) {


        if (t instanceof Forecast) {
            String string = ((Forecast) t).getUpdate_time();
            //天气的更新时间和当前时间往前调30分钟对比
            return stringToDate(string).compareTo(getUnOutOfTimeDate());
        }

        return -1;
    }

    /**
     * 把string转换为date格式返回，错误返回null
     *
     * @param string
     * @return
     */
    public Date stringToDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到未过期的最迟时间（即修改时间小于此时间为过期）
     *
     * @return 过期时间
     */
    public Date getUnOutOfTimeDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //把分钟往前调30分
        calendar.add(Calendar.MINUTE, -OUTOFTIME_MINUTE);
        return calendar.getTime();
    }


    /**
     * 从网络获取天气信息,成功的话,写入数据库,并更新UI,失败就什么也不做了
     *
     * @return
     */
    public  void getInfoFromIntnet(final Forecast forecast , final boolean ifAutoUpdate) {
        String url = HTTPURL + forecast.getCity_id() + KRY;
        GsonRequest<JsonBean> gsonRequest = new GsonRequest<JsonBean>(
                url, JsonBean.class,
                new Response.Listener<JsonBean>() {
                    @Override
                    public void onResponse(JsonBean weather) {
                        //把从网络取回的信息全都设置进本地
                        forecast.setStatus(weather.getHeWeather().get(0).getStatus());
                        forecast.setNow_txt(weather.getHeWeather().get(0).getNow().getCond().getTxt());
                        forecast.setUpdate_time(weather.getHeWeather().get(0).getBasic().getUpdate().getLoc());
                        //forecast.setAqi(weather.getHeWeather().get(0).getAqi().getCity().getAqi());
                        forecast.setCity_id(weather.getHeWeather().get(0).getBasic().getId());
                        forecast.setComf_brf(weather.getHeWeather().get(0).getSuggestion().getComf().getBrf());
                        forecast.setComf_txt(weather.getHeWeather().get(0).getSuggestion().getComf().getTxt());
                        forecast.setCw_brf(weather.getHeWeather().get(0).getSuggestion().getCw().getBrf());
                        forecast.setCw_txt(weather.getHeWeather().get(0).getSuggestion().getCw().getTxt());
                        forecast.setDir(weather.getHeWeather().get(0).getNow().getWind().getDir());
                        forecast.setFl(weather.getHeWeather().get(0).getNow().getFl());
                        forecast.setFlu_txt(weather.getHeWeather().get(0).getSuggestion().getFlu().getTxt());
                        forecast.setFlu_brf(weather.getHeWeather().get(0).getSuggestion().getFlu().getBrf());
                        forecast.setDrsg_brf(weather.getHeWeather().get(0).getSuggestion().getDrsg().getBrf());
                        forecast.setDrsg_txt(weather.getHeWeather().get(0).getSuggestion().getDrsg().getTxt());
                        forecast.setHum(weather.getHeWeather().get(0).getNow().getHum());
                       // forecast.setNo2(weather.getHeWeather().get(0).getAqi().getCity().getNo2());
                        forecast.setNow_code(weather.getHeWeather().get(0).getNow().getCond().getCode());
                        forecast.setNow_txt(weather.getHeWeather().get(0).getNow().getCond().getTxt());
                        //forecast.setPm10(weather.getHeWeather().get(0).getAqi().getCity().getPm10());
                        //forecast.setPm25(weather.getHeWeather().get(0).getAqi().getCity().getPm25());.setQlty(weather.getHeWeather().get(0).getAqi().getCity().getQlty());
                        //forecast.setQlty(weather.getHeWeather().get(0).getAqi().getCity().getQlty());
                        forecast.setSc(weather.getHeWeather().get(0).getNow().getWind().getSc());
                        //forecast.setSo2(weather.getHeWeather().get(0).getAqi().getCity().getSo2());
                        forecast.setSport_brf(weather.getHeWeather().get(0).getSuggestion().getSport().getBrf());
                        forecast.setSport_txt(weather.getHeWeather().get(0).getSuggestion().getSport().getTxt());
                        forecast.setTrav_brf(weather.getHeWeather().get(0).getSuggestion().getTrav().getBrf());
                        forecast.setTrav_txt(weather.getHeWeather().get(0).getSuggestion().getTrav().getTxt());
                        forecast.setUv_brf(weather.getHeWeather().get(0).getSuggestion().getUv().getBrf());
                        forecast.setUv_txt(weather.getHeWeather().get(0).getSuggestion().getUv().getTxt());
                        forecast.setTmp(weather.getHeWeather().get(0).getNow().getTmp());


                        forecast.setDaily_1_code_d(weather.getHeWeather().get(0).getDaily_forecast().get(0).getCond().getCode_d());
                        forecast.setDaily_1_code_n(weather.getHeWeather().get(0).getDaily_forecast().get(0).getCond().getCode_n());
                        forecast.setDaily_1_date(weather.getHeWeather().get(0).getDaily_forecast().get(0).getDate());
                        forecast.setDaily_1_max(weather.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMax());
                        forecast.setDaily_1_min(weather.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMin());
                        forecast.setDaily_2_code_d(weather.getHeWeather().get(0).getDaily_forecast().get(1).getCond().getCode_d());
                        forecast.setDaily_2_code_n(weather.getHeWeather().get(0).getDaily_forecast().get(1).getCond().getCode_n());
                        forecast.setDaily_2_date(weather.getHeWeather().get(0).getDaily_forecast().get(1).getDate());
                        forecast.setDaily_2_max(weather.getHeWeather().get(0).getDaily_forecast().get(1).getTmp().getMax());
                        forecast.setDaily_2_min(weather.getHeWeather().get(0).getDaily_forecast().get(1).getTmp().getMin());
                        forecast.setDaily_3_code_d(weather.getHeWeather().get(0).getDaily_forecast().get(2).getCond().getCode_d());
                        forecast.setDaily_3_code_n(weather.getHeWeather().get(0).getDaily_forecast().get(2).getCond().getCode_n());
                        forecast.setDaily_3_date(weather.getHeWeather().get(0).getDaily_forecast().get(2).getDate());
                        forecast.setDaily_3_max(weather.getHeWeather().get(0).getDaily_forecast().get(2).getTmp().getMax());
                        forecast.setDaily_3_min(weather.getHeWeather().get(0).getDaily_forecast().get(2).getTmp().getMin());
                        forecast.setDaily_4_code_d(weather.getHeWeather().get(0).getDaily_forecast().get(3).getCond().getCode_d());
                        forecast.setDaily_4_code_n(weather.getHeWeather().get(0).getDaily_forecast().get(3).getCond().getCode_n());
                        forecast.setDaily_4_date(weather.getHeWeather().get(0).getDaily_forecast().get(3).getDate());
                        forecast.setDaily_4_max(weather.getHeWeather().get(0).getDaily_forecast().get(3).getTmp().getMax());
                        forecast.setDaily_4_min(weather.getHeWeather().get(0).getDaily_forecast().get(3).getTmp().getMin());
                        forecast.setDaily_5_code_d(weather.getHeWeather().get(0).getDaily_forecast().get(4).getCond().getCode_d());
                        forecast.setDaily_5_code_n(weather.getHeWeather().get(0).getDaily_forecast().get(4).getCond().getCode_n());
                        forecast.setDaily_5_date(weather.getHeWeather().get(0).getDaily_forecast().get(4).getDate());
                        forecast.setDaily_5_max(weather.getHeWeather().get(0).getDaily_forecast().get(4).getTmp().getMax());
                        forecast.setDaily_5_min(weather.getHeWeather().get(0).getDaily_forecast().get(4).getTmp().getMin());

                        //api是否发生异常,正常情况下才能写入数据库,并设置UI
                        if (!ifApiError(forecast.getStatus())) {
                                mForecastDao.addOrUpdate(forecast);
                                if (!ifAutoUpdate) {
                                    fTwoBtnClickListener.UpdateUI();
                                }
                        }else {
                            Log.e("TAG", "api异常");
                        }
                        //有异常的话就什么也不做了
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        Log.d("TAG","网络请求失败");
                    }
                });
        //网络连接是异步执行的，所以不知道什么时候才能进入上面的回调函数，立马返回的话可能网络连接还没执行好。
        mQueue.add(gsonRequest);
    }

    /**
     * 判断api是否发生异常
     *
     * @param info
     * @return
     */
    public boolean ifApiError(String info) {
        if ("ok".equals(info)) {
            Log.d("apiErrorInfo", "接口正常");
            return false;
        } else if ("invalid key".equals(info)) {
            Log.d("apiErrorInfo", "错误的用户 key");
            return true;
        } else if ("unknown city".equals(info)) {
            Log.d("apiErrorInfo", "未知城市");
            return true;
        } else if ("no more requests".equals(info)) {
            Log.d("apiErrorInfo", "超过访问次数");
            return true;
        } else if ("anr".equals(info)) {
            Log.d("apiErrorInfo", "服务无响应或超时");
            return true;
        } else if ("permission denied".equals(info)) {
            Log.d("apiErrorInfo", "没有访问权限");
            return true;
        } else {
            Log.d("apiErrorInfo", "未知的api错误");
            return true;
        }
    }


    /**
     * 获取城市天气，成功返回true,失败返回false,无网络或者api出错
     * @param cityid
     * @param netAvailable
     * @return
     */
    public boolean getCityForecast(String cityid, boolean netAvailable) {
        Forecast forecast = mForecastDao.getForecastByCityid(cityid);
        //城市是否有天气,有的话啥都不做
        if ("ok".equals(forecast.getStatus())) {
            //fTwoBtnClickListener.UpdateUI();
            return false;
        }
        //城市无天气但有网络，就从网络获取
        else if (netAvailable) {
            getInfoFromIntnet(forecast,false);
            return true;
        }else {
            //城市没有天气又没有网络，那就什么也不做了
            Toast.makeText(mContext, "请连接网络", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    /**
     * 刷新天气,失败返回null,无网络，未过期，api出错，返回null
     * @param cityid
     * @param netAvailable
     * @return
     */
    public void getRefreshForecast(String cityid, boolean netAvailable,boolean ifAutoUpdate) {
        //有必要重新从数据库拿数据
        Forecast forecast = mForecastDao.getForecastByCityid(cityid);
        //是否有网络,无网络就进行提示
        if (!netAvailable) {
            Toast.makeText(mContext, "请连接网络", Toast.LENGTH_SHORT).show();
        }
        //有网络时，城市是否有天气,没天气的话直接从网络获取,因为没更新时间可看。
        else if (!"ok".equals(forecast.getStatus())) {
            getInfoFromIntnet(forecast,ifAutoUpdate);
        }
        //如果过期了，从网络获取
        else if(!(this.isOutOfTime(forecast) > 0)){
            getInfoFromIntnet(forecast,ifAutoUpdate);
        }//如果未过期，就什么也不做

    }




    /**
     * 清除缓存数据库
     */

    public void clearCache() {
        mForecastDao.deleteAll();
    }

}
