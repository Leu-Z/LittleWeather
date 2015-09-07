package com.leu.littleweather.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Leu on 2015/8/31.
 * 其实就是数据库中的一张表,这里会与数据库的表相映射
 * cloumnName:指定字段名,不指定则变量名作为字段名,白指定了。。。
 */
@DatabaseTable(tableName = "tb_forecast")
public class Forecast {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "city_id")
    private String city_id;
    @DatabaseField(columnName = "update_time")
    private String update_time;
    //空气质量指数
    @DatabaseField(columnName = "aqi")
    private String aqi;
    @DatabaseField(columnName = "no2")
    private String no2;
    @DatabaseField(columnName = "pm10")
    private String pm10;
    @DatabaseField(columnName = "pm25")
    private String pm25;
    //空气质量类别
    @DatabaseField(columnName = "qlty")
    private String qlty;
    @DatabaseField(columnName = "so2")
    private String so2;
    //舒适指数
    @DatabaseField(columnName = "Comf_brf")
    private String Comf_brf;
    @DatabaseField(columnName = "Comf_txt")
    private String Comf_txt;
    //洗车指数
    @DatabaseField(columnName = "Cw_brf")
    private String Cw_brf;
    @DatabaseField(columnName = "Cw_txt")
    private String Cw_txt;
    //穿衣指数
    @DatabaseField(columnName = "Drsg_brf")
    private String Drsg_brf;
    @DatabaseField(columnName = "Drsg_txt")
    private String Drsg_txt;
    //感冒指数
    @DatabaseField(columnName = "Flu_brf")
    private String Flu_brf;
    @DatabaseField(columnName = "Flu_txt")
    private String Flu_txt;
    //运动指数
    @DatabaseField(columnName = "Sport_brf")
    private String Sport_brf;
    @DatabaseField(columnName = "Sport_txt")
    private String Sport_txt;
    //旅游指数
    @DatabaseField(columnName = "Trav_brf")
    private String Trav_brf;
    @DatabaseField(columnName = "Trav_txt")
    private String Trav_txt;
    //紫外线指数
    @DatabaseField(columnName = "Uv_brf")
    private String Uv_brf;
    @DatabaseField(columnName = "Uv_txt")
    private String Uv_txt;
    //now天气状况
    @DatabaseField(columnName = "now_code")
    private String now_code;
    @DatabaseField(columnName = "now_txt")
    private String now_txt;
    //now体感温度
    @DatabaseField(columnName = "fl")
    private String fl;
    //now湿度
    @DatabaseField(columnName = "hum")
    private String hum;
    //now当前温度
    @DatabaseField(columnName = "tmp")
    private String tmp;
    //now风向
    @DatabaseField(columnName = "dir")
    private String dir;
    //now风力等级
    @DatabaseField(columnName = "sc")
    private String sc;
    //日期
    @DatabaseField(columnName = "daily_1_date")
    private String daily_1_date;
    //白天天气
    @DatabaseField(columnName = "daily_1_code_d")
    private String daily_1_code_d;
    //晚上天气
    @DatabaseField(columnName = "daily_1_code_n")
    private String daily_1_code_n;
    @DatabaseField(columnName = "daily_1_max")
    private String daily_1_max;
    @DatabaseField(columnName = "daily_1_min")
    private String daily_1_min;
    @DatabaseField(columnName = "daily_2_date")
    private String daily_2_date;
    @DatabaseField(columnName = "daily_2_code_d")
    private String daily_2_code_d;
    @DatabaseField(columnName = "daily_2_code_n")
    private String daily_2_code_n;
    @DatabaseField(columnName = "daily_2_max")
    private String daily_2_max;
    @DatabaseField(columnName = "daily_2_min")
    private String daily_2_min;
    @DatabaseField(columnName = "daily_3_date")
    private String daily_3_date;
    @DatabaseField(columnName = "daily_3_code_d")
    private String daily_3_code_d;
    @DatabaseField(columnName = "daily_3_code_n")
    private String daily_3_code_n;
    @DatabaseField(columnName = "daily_3_max")
    private String daily_3_max;
    @DatabaseField(columnName = "daily_3_min")
    private String daily_3_min;
    @DatabaseField(columnName = "daily_4_date")
    private String daily_4_date;
    @DatabaseField(columnName = "daily_4_code_d")
    private String daily_4_code_d;
    @DatabaseField(columnName = "daily_4_code_n")
    private String daily_4_code_n;
    @DatabaseField(columnName = "daily_4_max")
    private String daily_4_max;
    @DatabaseField(columnName = "daily_4_min")
    private String daily_4_min;
    @DatabaseField(columnName = "daily_5_date")
    private String daily_5_date;
    @DatabaseField(columnName = "daily_5_code_d")
    private String daily_5_code_d;
    @DatabaseField(columnName = "daily_5_code_n")
    private String daily_5_code_n;
    @DatabaseField(columnName = "daily_5_max")
    private String daily_5_max;
    @DatabaseField(columnName = "daily_5_min")
    private String daily_5_min;
    @DatabaseField(columnName = "status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getComf_brf() {
        return Comf_brf;
    }

    public void setComf_brf(String comf_brf) {
        Comf_brf = comf_brf;
    }

    public String getComf_txt() {
        return Comf_txt;
    }

    public void setComf_txt(String comf_txt) {
        Comf_txt = comf_txt;
    }

    public String getCw_brf() {
        return Cw_brf;
    }

    public void setCw_brf(String cw_brf) {
        Cw_brf = cw_brf;
    }

    public String getCw_txt() {
        return Cw_txt;
    }

    public void setCw_txt(String cw_txt) {
        Cw_txt = cw_txt;
    }

    public String getDrsg_brf() {
        return Drsg_brf;
    }

    public void setDrsg_brf(String drsg_brf) {
        Drsg_brf = drsg_brf;
    }

    public String getDrsg_txt() {
        return Drsg_txt;
    }

    public void setDrsg_txt(String drsg_txt) {
        Drsg_txt = drsg_txt;
    }

    public String getFlu_brf() {
        return Flu_brf;
    }

    public void setFlu_brf(String flu_brf) {
        Flu_brf = flu_brf;
    }

    public String getFlu_txt() {
        return Flu_txt;
    }

    public void setFlu_txt(String flu_txt) {
        Flu_txt = flu_txt;
    }

    public String getSport_brf() {
        return Sport_brf;
    }

    public void setSport_brf(String sport_brf) {
        Sport_brf = sport_brf;
    }

    public String getSport_txt() {
        return Sport_txt;
    }

    public void setSport_txt(String sport_txt) {
        Sport_txt = sport_txt;
    }

    public String getTrav_brf() {
        return Trav_brf;
    }

    public void setTrav_brf(String trav_brf) {
        Trav_brf = trav_brf;
    }

    public String getTrav_txt() {
        return Trav_txt;
    }

    public void setTrav_txt(String trav_txt) {
        Trav_txt = trav_txt;
    }

    public String getUv_brf() {
        return Uv_brf;
    }

    public void setUv_brf(String uv_brf) {
        Uv_brf = uv_brf;
    }

    public String getUv_txt() {
        return Uv_txt;
    }

    public void setUv_txt(String uv_txt) {
        Uv_txt = uv_txt;
    }

    public String getNow_code() {
        return now_code;
    }

    public void setNow_code(String now_code) {
        this.now_code = now_code;
    }

    public String getNow_txt() {
        return now_txt;
    }

    public void setNow_txt(String now_txt) {
        this.now_txt = now_txt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getDaily_1_date() {
        return daily_1_date;
    }

    public void setDaily_1_date(String daily_1_date) {
        this.daily_1_date = daily_1_date;
    }

    public String getDaily_1_code_d() {
        return daily_1_code_d;
    }

    public void setDaily_1_code_d(String daily_1_code_d) {
        this.daily_1_code_d = daily_1_code_d;
    }

    public String getDaily_1_code_n() {
        return daily_1_code_n;
    }

    public void setDaily_1_code_n(String daily_1_code_n) {
        this.daily_1_code_n = daily_1_code_n;
    }

    public String getDaily_1_max() {
        return daily_1_max;
    }

    public void setDaily_1_max(String daily_1_max) {
        this.daily_1_max = daily_1_max;
    }

    public String getDaily_1_min() {
        return daily_1_min;
    }

    public void setDaily_1_min(String daily_1_min) {
        this.daily_1_min = daily_1_min;
    }

    public String getDaily_2_date() {
        return daily_2_date;
    }

    public void setDaily_2_date(String daily_2_date) {
        this.daily_2_date = daily_2_date;
    }

    public String getDaily_2_code_d() {
        return daily_2_code_d;
    }

    public void setDaily_2_code_d(String daily_2_code_d) {
        this.daily_2_code_d = daily_2_code_d;
    }

    public String getDaily_2_code_n() {
        return daily_2_code_n;
    }

    public void setDaily_2_code_n(String daily_2_code_n) {
        this.daily_2_code_n = daily_2_code_n;
    }

    public String getDaily_2_max() {
        return daily_2_max;
    }

    public void setDaily_2_max(String daily_2_max) {
        this.daily_2_max = daily_2_max;
    }

    public String getDaily_2_min() {
        return daily_2_min;
    }

    public void setDaily_2_min(String daily_2_min) {
        this.daily_2_min = daily_2_min;
    }

    public String getDaily_3_date() {
        return daily_3_date;
    }

    public void setDaily_3_date(String daily_3_date) {
        this.daily_3_date = daily_3_date;
    }

    public String getDaily_3_code_d() {
        return daily_3_code_d;
    }

    public void setDaily_3_code_d(String daily_3_code_d) {
        this.daily_3_code_d = daily_3_code_d;
    }

    public String getDaily_3_code_n() {
        return daily_3_code_n;
    }

    public void setDaily_3_code_n(String daily_3_code_n) {
        this.daily_3_code_n = daily_3_code_n;
    }

    public String getDaily_3_max() {
        return daily_3_max;
    }

    public void setDaily_3_max(String daily_3_max) {
        this.daily_3_max = daily_3_max;
    }

    public String getDaily_3_min() {
        return daily_3_min;
    }

    public void setDaily_3_min(String daily_3_min) {
        this.daily_3_min = daily_3_min;
    }

    public String getDaily_4_date() {
        return daily_4_date;
    }

    public void setDaily_4_date(String daily_4_date) {
        this.daily_4_date = daily_4_date;
    }

    public String getDaily_4_code_d() {
        return daily_4_code_d;
    }

    public void setDaily_4_code_d(String daily_4_code_d) {
        this.daily_4_code_d = daily_4_code_d;
    }

    public String getDaily_4_code_n() {
        return daily_4_code_n;
    }

    public void setDaily_4_code_n(String daily_4_code_n) {
        this.daily_4_code_n = daily_4_code_n;
    }

    public String getDaily_4_max() {
        return daily_4_max;
    }

    public void setDaily_4_max(String daily_4_max) {
        this.daily_4_max = daily_4_max;
    }

    public String getDaily_4_min() {
        return daily_4_min;
    }

    public void setDaily_4_min(String daily_4_min) {
        this.daily_4_min = daily_4_min;
    }

    public String getDaily_5_date() {
        return daily_5_date;
    }

    public void setDaily_5_date(String daily_5_date) {
        this.daily_5_date = daily_5_date;
    }

    public String getDaily_5_code_d() {
        return daily_5_code_d;
    }

    public void setDaily_5_code_d(String daily_5_code_d) {
        this.daily_5_code_d = daily_5_code_d;
    }

    public String getDaily_5_code_n() {
        return daily_5_code_n;
    }

    public void setDaily_5_code_n(String daily_5_code_n) {
        this.daily_5_code_n = daily_5_code_n;
    }

    public String getDaily_5_max() {
        return daily_5_max;
    }

    public void setDaily_5_max(String daily_5_max) {
        this.daily_5_max = daily_5_max;
    }

    public String getDaily_5_min() {
        return daily_5_min;
    }

    public void setDaily_5_min(String daily_5_min) {
        this.daily_5_min = daily_5_min;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
