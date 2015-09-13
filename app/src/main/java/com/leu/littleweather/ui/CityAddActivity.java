package com.leu.littleweather.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.dao.ForecastDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Leu on 2015/9/1.
 */
public class CityAddActivity extends BaseActivity{
    private AutoCompleteTextView mAutoCompleteTextView;
    private ForecastDao mforecastDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_add_layout);

        mforecastDao=new ForecastDao(this);

        mAutoCompleteTextView= (AutoCompleteTextView) findViewById(R.id.sch_autotext);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getCityArray());
        mAutoCompleteTextView.setAdapter(adapter);
        //点击下拉的提示项后触发
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //得到点击城市的名字
                TextView textview = (TextView) view;
                //查看城市是否存在,不存在的话加入数据库
                if (!ifCityExist((String) textview.getText())){
                    Forecast forecast=new Forecast();
                    forecast.setCity((String) textview.getText());
                    mforecastDao.addOrUpdate(forecast);

                    finish();
                }
            }
        });




    }

    /**
     * 从txt文件中获取所有城市的数组
     * @return
     */
    private String[] getCityArray(){
        ArrayList<String> cityList=new ArrayList<String>();
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open("city.txt") );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String[] str = new String[5];
            //从txt文件中的所有城市中一个一个得取出来进行匹配
            while((line = bufReader.readLine()) != null){
                //通过中间的空格把城市分成5部分。
                str = line.split("\t");
                cityList.add(str[2]);
            }

            int size=cityList.size();
            String[] cityArray = (String[])cityList.toArray(new String[size]);
            return cityArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param city
     * @return
     */
    public boolean ifCityExist(String city){
        Forecast forecast=mforecastDao.getForecastByCity(city);
        if (forecast!=null){
            Toast.makeText(CityAddActivity.this,"该城市已存在",Toast.LENGTH_SHORT).show();
            return true;
        }else {

            return false;
        }



    }



}
