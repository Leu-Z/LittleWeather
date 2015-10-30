package com.leu.littleweather.util;

import android.content.Context;
import android.os.AsyncTask;

import com.leu.littleweather.bean.GroupMemberBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leu on 2015/10/30.
 */
public class LoadingTask extends AsyncTask<Context,Void,Void> {



    @Override
    protected Void doInBackground(Context... params) {
        Context context=params[0];
        List<GroupMemberBean> groupMemberBeans=filledData(context);
        SingleClass singleClass=SingleClass.getSingleClass(context);
        singleClass.setGroupMemberBeanList(groupMemberBeans);

        return null;
    }



    /**
     * 从txt文件中获取所有城市,并进行组装。
     * @return
     */
    private List<GroupMemberBean> filledData(Context context){
        CharacterParser characterParser = CharacterParser.getInstance();
        List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open("city.txt") );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String[] str ;
            //从txt文件中的所有城市中一个一个得取出来进行匹配
            while((line = bufReader.readLine()) != null){
                //通过中间的空格把城市分成5部分。
                GroupMemberBean sortModel = new GroupMemberBean();
                str = line.split("\t");
                sortModel.setCityCode(str[0]);
                sortModel.setCity(str[2]);
                sortModel.setPrefecture(str[3]);
                sortModel.setProvince(str[4]);

                // 汉字转换成拼音
                String pinyin = characterParser.getSelling(str[2]);
                //获取拼音首字母
                String sortString = pinyin.substring(0, 1).toUpperCase();

                // 正则表达式，判断首字母是否是英文字母
                //是英文字母的话就进行设置，否则设置为#
                if (sortString.matches("[A-Z]")) {
                    sortModel.setSortLetters(sortString.toUpperCase());
                } else {
                    sortModel.setSortLetters("#");
                }
                mSortList.add(sortModel);
            }
            return mSortList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
