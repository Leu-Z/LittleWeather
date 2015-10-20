package com.leu.littleweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Leu on 2015/8/28.
 */
public class JsonBean {


    /**
     * HeWeather data service 3.0 : [{"aqi":{"city":{"aqi":"49","co":"0","no2":"19","o3":"120","pm10":"49","pm25":"22","qlty":"优","so2":"2"}},"basic":{"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2015-08-28 19:44","utc":"2015-08-28 11:46"}},"daily_forecast":[{"astro":{"sr":"05:38","ss":"18:53"},"cond":{"code_d":"103","code_n":"101","txt_d":"晴间多云","txt_n":"多云"},"date":"2015-08-28","hum":"23","pcpn":"0.0","pop":"4","pres":"1007","tmp":{"max":"37","min":"21"},"vis":"10","wind":{"deg":"85","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:39","ss":"18:51"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-29","hum":"32","pcpn":"0.3","pop":"33","pres":"1007","tmp":{"max":"29","min":"20"},"vis":"10","wind":{"deg":"101","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:40","ss":"18:50"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-30","hum":"39","pcpn":"2.9","pop":"82","pres":"1008","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"165","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:40","ss":"18:48"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-31","hum":"61","pcpn":"13.7","pop":"75","pres":"1010","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"100","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:41","ss":"18:47"},"cond":{"code_d":"302","code_n":"101","txt_d":"雷阵雨","txt_n":"多云"},"date":"2015-09-01","hum":"39","pcpn":"0.1","pop":"67","pres":"1010","tmp":{"max":"27","min":"20"},"vis":"9","wind":{"deg":"112","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:42","ss":"18:45"},"cond":{"code_d":"101","code_n":"300","txt_d":"多云","txt_n":"阵雨"},"date":"2015-09-02","hum":"41","pcpn":"0.0","pop":"2","pres":"1013","tmp":{"max":"29","min":"20"},"vis":"10","wind":{"deg":"172","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:43","ss":"18:44"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-09-03","hum":"30","pcpn":"0.0","pop":"2","pres":"1011","tmp":{"max":"30","min":"21"},"vis":"10","wind":{"deg":"160","dir":"无持续风向","sc":"微风","spd":"7"}}],"hourly_forecast":[{"date":"2015-08-28 19:00","hum":"35","pop":"0","pres":"1007","tmp":"34","wind":{"deg":"209","dir":"西南风","sc":"微风","spd":"10"}},{"date":"2015-08-28 22:00","hum":"44","pop":"1","pres":"1008","tmp":"31","wind":{"deg":"245","dir":"西南风","sc":"微风","spd":"11"}}],"now":{"cond":{"code":"101","txt":"多云"},"fl":"29","hum":"62","pcpn":"0","pres":"1005","tmp":"24","vis":"10","wind":{"deg":"100","dir":"南风","sc":"5-6","spd":"7"}},"status":"ok","suggestion":{"comf":{"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"trav":{"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}}]
     */

    @SerializedName("HeWeather data service 3.0")
    private List<HeWeathery> heWeather;

    public void setHeWeather(List<HeWeathery> heWeather) {
        this.heWeather = heWeather;
    }

    public List<HeWeathery> getHeWeather() {
        return heWeather;
    }

    public static class HeWeathery {
        /**
         * aqi : {"city":{"aqi":"49","co":"0","no2":"19","o3":"120","pm10":"49","pm25":"22","qlty":"优","so2":"2"}}
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2015-08-28 19:44","utc":"2015-08-28 11:46"}}
         * daily_forecast : [{"astro":{"sr":"05:38","ss":"18:53"},"cond":{"code_d":"103","code_n":"101","txt_d":"晴间多云","txt_n":"多云"},"date":"2015-08-28","hum":"23","pcpn":"0.0","pop":"4","pres":"1007","tmp":{"max":"37","min":"21"},"vis":"10","wind":{"deg":"85","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:39","ss":"18:51"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-29","hum":"32","pcpn":"0.3","pop":"33","pres":"1007","tmp":{"max":"29","min":"20"},"vis":"10","wind":{"deg":"101","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:40","ss":"18:50"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-30","hum":"39","pcpn":"2.9","pop":"82","pres":"1008","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"165","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:40","ss":"18:48"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-08-31","hum":"61","pcpn":"13.7","pop":"75","pres":"1010","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"100","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:41","ss":"18:47"},"cond":{"code_d":"302","code_n":"101","txt_d":"雷阵雨","txt_n":"多云"},"date":"2015-09-01","hum":"39","pcpn":"0.1","pop":"67","pres":"1010","tmp":{"max":"27","min":"20"},"vis":"9","wind":{"deg":"112","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:42","ss":"18:45"},"cond":{"code_d":"101","code_n":"300","txt_d":"多云","txt_n":"阵雨"},"date":"2015-09-02","hum":"41","pcpn":"0.0","pop":"2","pres":"1013","tmp":{"max":"29","min":"20"},"vis":"10","wind":{"deg":"172","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"05:43","ss":"18:44"},"cond":{"code_d":"302","code_n":"302","txt_d":"雷阵雨","txt_n":"雷阵雨"},"date":"2015-09-03","hum":"30","pcpn":"0.0","pop":"2","pres":"1011","tmp":{"max":"30","min":"21"},"vis":"10","wind":{"deg":"160","dir":"无持续风向","sc":"微风","spd":"7"}}]
         * hourly_forecast : [{"date":"2015-08-28 19:00","hum":"35","pop":"0","pres":"1007","tmp":"34","wind":{"deg":"209","dir":"西南风","sc":"微风","spd":"10"}},{"date":"2015-08-28 22:00","hum":"44","pop":"1","pres":"1008","tmp":"31","wind":{"deg":"245","dir":"西南风","sc":"微风","spd":"11"}}]
         * now : {"cond":{"code":"101","txt":"多云"},"fl":"29","hum":"62","pcpn":"0","pres":"1005","tmp":"24","vis":"10","wind":{"deg":"100","dir":"南风","sc":"5-6","spd":"7"}}
         * status : ok
         * suggestion : {"comf":{"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"trav":{"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
         */

        private AqiEntity aqi;
        private BasicEntity basic;
        private NowEntity now;
        private String status;
        private SuggestionEntity suggestion;
        private List<DailyForecastEntity> daily_forecast;

        public void setAqi(AqiEntity aqi) {
            this.aqi = aqi;
        }

        public void setBasic(BasicEntity basic) {
            this.basic = basic;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }



        public AqiEntity getAqi() {
            return aqi;
        }

        public BasicEntity getBasic() {
            return basic;
        }

        public NowEntity getNow() {
            return now;
        }

        public String getStatus() {
            return status;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public List<DailyForecastEntity> getDaily_forecast() {
            return daily_forecast;
        }



        public static class AqiEntity {
            /**
             * city : {"aqi":"49","co":"0","no2":"19","o3":"120","pm10":"49","pm25":"22","qlty":"优","so2":"2"}
             */

            private CityEntity city;

            public void setCity(CityEntity city) {
                this.city = city;
            }

            public CityEntity getCity() {
                return city;
            }

            public static class CityEntity {
                /**
                 * aqi : 49
                 * co : 0
                 * no2 : 19
                 * o3 : 120
                 * pm10 : 49
                 * pm25 : 22
                 * qlty : 优
                 * so2 : 2
                 */

                //private String aqi;
               // private String no2;
               // private String pm10;
                //private String pm25;
               // private String qlty;
                //private String so2;

             /*   public void setAqi(String aqi) {
                    this.aqi = aqi;
                }*/

               /* public void setNo2(String no2) {
                    this.no2 = no2;
                }*/

               /* public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }*/

               /* public void setSo2(String so2) {
                    this.so2 = so2;
                }*/

               /* public String getAqi() {
                    return aqi;
                }*/

               /* public String getNo2() {
                    return no2;
                }*/

             /*   public String getPm10() {
                    return pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public String getQlty() {
                    return qlty;
                }*/

                /*public String getSo2() {
                    return so2;
                }*/
            }
        }

        public static class BasicEntity {
            /**
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.904000
             * lon : 116.391000
             * update : {"loc":"2015-08-28 19:44","utc":"2015-08-28 11:46"}
             */

            private String city;
            private String id;
            private UpdateEntity update;

            public void setCity(String city) {
                this.city = city;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setUpdate(UpdateEntity update) {
                this.update = update;
            }

            public String getCity() {
                return city;
            }

            public String getId() {
                return id;
            }

            public UpdateEntity getUpdate() {
                return update;
            }

            public static class UpdateEntity {
                /**
                 * loc : 2015-08-28 19:44
                 * utc : 2015-08-28 11:46
                 */

                private String loc;

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getLoc() {
                    return loc;
                }
            }
        }

        public static class NowEntity {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 29
             * hum : 62
             * pcpn : 0
             * pres : 1005
             * tmp : 24
             * vis : 10
             * wind : {"deg":"100","dir":"南风","sc":"5-6","spd":"7"}
             */

            private CondEntity cond;
            private String fl;
            private String hum;
            private String tmp;
            private WindEntity wind;

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getFl() {
                return fl;
            }

            public String getHum() {
                return hum;
            }

            public String getTmp() {
                return tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public static class CondEntity {
                /**
                 * code : 101
                 * txt : 多云
                 */

                private String code;
                private String txt;

                public void setCode(String code) {
                    this.code = code;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getCode() {
                    return code;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class WindEntity {
                private String dir;
                private String sc;

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }
            }
        }

        public static class SuggestionEntity {
            /**
             * comf : {"brf":"较舒适","txt":"白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。"}
             * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             * drsg : {"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}
             * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
             * sport : {"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"}
             * trav : {"brf":"一般","txt":"温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。"}
             * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             */

            private ComfEntity comf;
            private CwEntity cw;
            private DrsgEntity drsg;
            private FluEntity flu;
            private SportEntity sport;
            private TravEntity trav;
            private UvEntity uv;

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public ComfEntity getComf() {
                return comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public static class ComfEntity {
                /**
                 * brf : 较舒适
                 * txt : 白天有降雨，但会使人们感觉有些热，不过大部分人仍会有比较舒适的感觉。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class CwEntity {
                /**
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class DrsgEntity {
                /**
                 * brf : 热
                 * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class FluEntity {
                /**
                 * brf : 少发
                 * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class SportEntity {
                /**
                 * brf : 较不宜
                 * txt : 有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class TravEntity {
                /**
                 * brf : 一般
                 * txt : 温度适宜，风不大，有降水，旅游指数一般，外出请尽量避开降雨时段，若外出，请注意防雷防雨。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            public static class UvEntity {
                /**
                 * brf : 弱
                 * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
                 */

                private String brf;
                private String txt;

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }
        }

        public static class DailyForecastEntity {
            private CondEntity cond;
            private String date;
            private TmpEntity tmp;

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public CondEntity getCond() {
                return cond;
            }

            public String getDate() {
                return date;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public static class CondEntity {
                /**
                 * code_d : 103
                 * code_n : 101
                 * txt_d : 晴间多云
                 * txt_n : 多云
                 */

                private String code_d;
                private String code_n;

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getCode_d() {
                    return code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

            }

            public static class TmpEntity {
                /**
                 * max : 37
                 * min : 21
                 */

                private String max;
                private String min;

                public void setMax(String max) {
                    this.max = max;
                }

                public void setMin(String min) {
                    this.min = min;
                }

                public String getMax() {
                    return max;
                }

                public String getMin() {
                    return min;
                }
            }
        }


    }
}
