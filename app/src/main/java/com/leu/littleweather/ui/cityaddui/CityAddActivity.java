package com.leu.littleweather.ui.cityaddui;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.leu.littleweather.R;
import com.leu.littleweather.bean.Forecast;
import com.leu.littleweather.bean.GroupMemberBean;
import com.leu.littleweather.dao.ForecastDao;
import com.leu.littleweather.ui.BaseActivity;
import com.leu.littleweather.util.CharacterParser;
import com.leu.littleweather.util.PinyinComparator;
import com.leu.littleweather.util.SingleClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Leu on 2015/9/1.
 */
public class CityAddActivity extends BaseActivity implements SectionIndexer {
    private ForecastDao mforecastDao;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortGroupMemberAdapter adapter;

    private LinearLayout titleLayout;
    private TextView title;
    private TextView tvNofriends;
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<GroupMemberBean> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_add_layout);
        mforecastDao=new ForecastDao(this);

        initViews();

    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("选择城市");
        setSupportActionBar(toolbar);
        //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //这两个用于设置挤压效果
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) this.findViewById(R.id.title_layout_catalog);
        //搜索不到时进行显示，平时隐藏。
        tvNofriends = (TextView) this
                .findViewById(R.id.title_layout_no_friends);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        //自定义临时的比较规则
        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        //把dialog设置进sideBar，由他来控制dialog的显示。
        sideBar.setTextView(dialog);

        // 设置右侧触摸监听,让ListView跟随sideBar来显示。
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            //传入当前选中的字母
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    //设置ListView第一行显示的positon
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        //点击选中的城市后把该城市添加进数据库。
        //添加的是该城市的id和城市名。
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //得到该城市的id
                String cityId=((GroupMemberBean) adapter.getItem(position)).getCityCode();
                String city=((GroupMemberBean) adapter.getItem(position)).getCity();
                if (!ifCityExist(cityId)){
                    //往数据库中添加新的空城市。
                    Forecast forecast=new Forecast();
                    forecast.setCity_id(cityId);
                    forecast.setCity(city);
                    mforecastDao.addOrUpdate(forecast);

                    Intent intent=new Intent();
                    intent.putExtra("city_id",cityId);
                    intent.putExtra("city_name",city);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });
        //得到List的数据bean
        SingleClass singleClass=SingleClass.getSingleClass(this);
        if (singleClass!=null) {
            SourceDateList = singleClass.getGroupMemberBeanList();
        }
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        //新建适配器
        adapter = new SortGroupMemberAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                //第一个可见的item的首字母
                int section = getSectionForPosition(firstVisibleItem);
                //第二个可见的item的首字母
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                //第二个字母的第一个item的位置。
                int nextSecPosition = getPositionForSection(+nextSection);
                //lastFirstVisibleItem默认为-1
                //设置第一个出现的item的首字母在最上面的那个位置
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    //得到该首字母第一次出现的位置，然后设置他的首字母。。。
                    //其实就是设置第一个出现的item的首字母在最上面的那个位置
                    title.setText(SourceDateList.get(
                            getPositionForSection(section)).getSortLetters());
                }
                //如果下一个字母首次出现的位置等于第一个item+1
                //就是说第一个字母下面就是第二个字母时。设置挤压效果。
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        //得到第一个横条的高度
                        int titleHeight = titleLayout.getHeight();
                        //底部相对于父布局的距离
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        //当下面的字母行上移时，使首行的字母行动态得上移。
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }





    /**
     *
     * @param cityId
     * @return
     */
    public boolean ifCityExist(String cityId){

        Forecast forecast=mforecastDao.getForecastByCityid(cityId);
        if (forecast!=null){
            Toast.makeText(CityAddActivity.this,"该城市已存在",Toast.LENGTH_SHORT).show();
            return true;
        }else {

            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_addcity, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(info);
        //放大镜在搜索框内
        searchView.setIconifiedByDefault(false);


        //设置搜索框监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private String TAG = getClass().getSimpleName();

            /*
             * 在输入时触发的方法，当字符真正显示到searchView中才触发
             * @param queryText
             *
             * @return false if the SearchView should perform the default action
             * of showing any suggestions if available, true if the action was
             * handled by the listener.
             */
            @Override
            public boolean onQueryTextChange(String queryText) {
                // 这个时候不需要挤压效果 就把他隐藏掉
                titleLayout.setVisibility(View.GONE);
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(queryText);

                return true;
            }

            /*
             * 输入完成后，提交时触发的方法，一般情况是点击输入法中的搜索按钮才会触发。表示现在正式提交了
             *
             * @param queryText
             *
             * @return true to indicate that it has handled the submit request.
             * Otherwise return false to let the SearchView handle the
             * submission by launching any associated intent.
             */
            @Override
            public boolean onQueryTextSubmit(String queryText) {
                Log.d(TAG, "onQueryTextSubmit = " + queryText);
                if (searchView != null) {
                    // 得到输入管理对象
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                        // 输入法如果是显示状态，那么就隐藏输入法
                        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                    }
                    // 不获取焦点
                    searchView.clearFocus();

                }
                return true;
            }
        });


        return true;
    }



    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<GroupMemberBean> filterDateList = new ArrayList<GroupMemberBean>();
        //如果输入的是空的，就显示原来的列表
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
            tvNofriends.setVisibility(View.GONE);
        } else {
            filterDateList.clear();
            //遍历原来的数据，组成新的list
            for (GroupMemberBean sortModel : SourceDateList) {
                String city = sortModel.getCity();
                String prefecture=sortModel.getPrefecture();
                String province=sortModel.getProvince();
                //输入的字符串是否包含在数据String中
                // 或者输入的字符串的首字母是否与数据String的首字母相同。
                if (city.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(city).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }else if (prefecture.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(prefecture).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }/*else if(province.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(province).startsWith(
                        filterStr.toString())){
                    filterDateList.add(sortModel);
                }*/
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        //刷新listview的显示。
        adapter.updateListView(filterDateList);
        //如果没有匹配的，就在第一行进行提醒。
        if (filterDateList.size() == 0) {
            tvNofriends.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.search:
                //打开默认输入法
                InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                searchView.requestFocus();
                break;
        }

        return  true;
    }



}
