package com.leu.littleweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Leu on 2015/9/6.
 */
public class BottomFragmentTwo extends BaseFragment {
    private static final String ARG_CITY = "city";
    private String mCity;
    private TextView day1;
    private TextView tmpD1;
    private TextView tmpN1;
    private SimpleDraweeView image1;
    private TextView day2;
    private TextView tmpD2;
    private TextView tmpN2;
    private SimpleDraweeView image2;
    private TextView day3;
    private TextView tmpD3;
    private TextView tmpN3;
    private SimpleDraweeView image3;
    private TextView day4;
    private TextView tmpD4;
    private TextView tmpN4;
    private SimpleDraweeView image4;

    public static BottomFragmentTwo newInstance(String city) {
        BottomFragmentTwo fragment = new BottomFragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mCity = getArguments().getString(ARG_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_fragment_two, container, false);

        day1=(TextView) view.findViewById(R.id.day1);
        tmpD1= (TextView) view.findViewById(R.id.tmp_d1);
        tmpN1 = (TextView) view.findViewById(R.id.tmp_n1);
        image1= (SimpleDraweeView) view.findViewById(R.id.image1);

        day2=(TextView) view.findViewById(R.id.day2);
        tmpD2= (TextView) view.findViewById(R.id.tmp_d2);
        tmpN2 = (TextView) view.findViewById(R.id.tmp_n2);
        image2= (SimpleDraweeView) view.findViewById(R.id.image2);

        day3=(TextView) view.findViewById(R.id.day3);
        tmpD3= (TextView) view.findViewById(R.id.tmp_d3);
        tmpN3 = (TextView) view.findViewById(R.id.tmp_n3);
        image3= (SimpleDraweeView) view.findViewById(R.id.image3);

        day4=(TextView) view.findViewById(R.id.day4);
        tmpD4= (TextView) view.findViewById(R.id.tmp_d4);
        tmpN4 = (TextView) view.findViewById(R.id.tmp_n4);
        image4= (SimpleDraweeView) view.findViewById(R.id.image4);
        setUI();

        return view;
    }

    public void setUI(){
        Forecast forecast = mFrecastDao.getForecastByCity(mCity);
        tmpD1.setText(forecast.getDaily_2_max());
        tmpN1.setText(forecast.getDaily_2_min());
        day1.setText(dateToWeek(forecast.getDaily_2_date()));
        image1.setImageURI(getImageUri(forecast.getDaily_2_code_d()));

        tmpD2.setText(forecast.getDaily_3_max());
        tmpN2.setText(forecast.getDaily_3_min());
        day2.setText(dateToWeek(forecast.getDaily_3_date()));
        image2.setImageURI(getImageUri(forecast.getDaily_3_code_d()));

        tmpD3.setText(forecast.getDaily_4_max());
        tmpN3.setText(forecast.getDaily_4_min());
        day3.setText(dateToWeek(forecast.getDaily_4_date()));
        image3.setImageURI(getImageUri(forecast.getDaily_4_code_d()));

        tmpD4.setText(forecast.getDaily_5_max());
        tmpN4.setText(forecast.getDaily_5_min());
        day4.setText(dateToWeek(forecast.getDaily_5_date()));
        image4.setImageURI(getImageUri(forecast.getDaily_5_code_d()));



    }

    /**
     * 从日期推出星期
     * @param date
     * @return
     */
    private String dateToWeek(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        String week=null;
        switch (dayForWeek){
            case 1: week="周一";
                break;
            case 2:week="周二";
                break;
            case 3:week="周三";
                break;
            case 4:week="周四";
                break;
            case 5:week="周五";
                break;
            case 6:week="周六";
                break;
            case 7:week="周日";
                break;
        }
        return week;

    }



}
