package com.leu.littleweather.bean;

/**
 * Created by Leu on 2015/10/18.
 */
public class GroupMemberBean {
    private String city;   //县级市
    private String prefecture;//地级市
    private String province;//省
    private String sortLetters;  //显示数据拼音的首字母
    private String cityCode;//城市编码

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
