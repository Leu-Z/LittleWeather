package com.leu.littleweather.ui.fragmentui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.leu.littleweather.R;
import com.leu.littleweather.biz.ForecastBiz;
import com.leu.littleweather.ui.BaseFragment;
import com.leu.littleweather.ui.MainActivity;
import com.leu.littleweather.util.CommonUtil;

import java.util.ArrayList;

/**
 * Created by Leu on 2015/9/4.
 */
public class OutterFragment extends BaseFragment implements ForecastBiz.UpdateClickListener, OnClickListener {
    private static final String ARG_CITY = "cityid";
    private static final String TAG = "OutterFragment";
    private String mCityId;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList;
    private BottomFragmentOne mBottomOne;
    private ImageButton mButtonLeft;
    private ImageButton mButtonRight;
    private ForecastBiz mForecastBiz;
    private BottomFragmentTwo mBottomTwo;
    private View view;
    private InnerPagerAdapter mInnerAdapter;
    private MiddleFragment middleFragment;
    private RightFragment rightFragment;
    private FragmentManager fm;
    private boolean mIsLoading = false;
    private View mLoading;

    public static OutterFragment newInstance(String cityid) {

        OutterFragment fragment = new OutterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, cityid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityId = getArguments().getString(ARG_CITY);
            mForecastBiz = new ForecastBiz(getActivity());
            mForecastBiz.setUpdateClickListener(this);
        }
        Log.d(TAG, mCityId + "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, mCityId + "onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.outter_fragment, container, false);
        //先获取城市天气，如果是空城市的话，就上网获取数据，先用小鸡当着，加载完了更新后再显示
        //如果是不是空城市就直接显示。
        boolean isEmptyCity =mForecastBiz.getCityForecast(mCityId, CommonUtil.IsNetAvailable(getActivity()));
        if (isEmptyCity){
            //先把小鸡显示出来，隐藏布局。因为如果该城市是空的话画面很丑。
            view.findViewById(R.id.layout).setVisibility(View.GONE);
            //这里用到了ViewStub,如果不加载他的话里面的布局是不会占资源的，很适用。
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.viewStub);
            mLoading=viewStub.inflate();
            mIsLoading=true;
            //再初始化，因为是空城市，这里的数据都是空的
            init();
            initViewPager();
        }else {
            //因为不是空的，数据库中已经有数据，初始化后已经把天气数据设置进布局中了。
            init();
            initViewPager();
        }


       /* //如果不是第一次创建的话，直接显示布局，隐藏小鸡
        if (!ifFirst) {
            view.findViewById(R.id.layout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.animation).setVisibility(View.GONE);
        }*/
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, mCityId + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, mCityId + "onResume");
        //获得天气数据后更新UI。
        // 更新碎片UI必须在onResume中进行。因为里面的fragment要在这个Resume之后再生成。。。
        //只有第一次初始化碎片的时候才去更新数据。否则不进来，因为里面已经有了，节省了资源。
       /* if (ifFirst) {
            mForecastBiz.getCityForecast(mCityId, CommonUtil.IsNetAvailable(getActivity()));
            ifFirst = false;
        }*/
    }
    //设置底下今天，四天的天气，直接根据城市id取出并设置进view中。
    private void init() {
        TextView update= (TextView) view.findViewById(R.id.update);
        //update.setText(mFrecastDao.ge);
        mButtonLeft = (ImageButton) view.findViewById(R.id.button_left);
        mButtonRight = (ImageButton) view.findViewById(R.id.button_right);
        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);
        mButtonRight.setAlpha(120);
        fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        mBottomOne = (BottomFragmentOne) fm.findFragmentByTag("bottomOne");
        mBottomTwo = (BottomFragmentTwo) fm.findFragmentByTag("bottomTwo");
        if (mBottomOne == null || mBottomTwo == null) {
            mBottomOne = BottomFragmentOne.newInstance(mCityId);
            mBottomTwo = BottomFragmentTwo.newInstance(mCityId);
            transaction.add(R.id.bottom_weather, mBottomTwo, "bottomTwo");
            transaction.add(R.id.bottom_weather, mBottomOne, "bottomOne");
        }
        transaction.show(mBottomOne);
        transaction.hide(mBottomTwo);
        transaction.commit();

        final ScrollView scrollView= (ScrollView) view.findViewById(R.id.scrollView);
        //让ScrollView在最上方的时候才可以刷新。
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {

                        MainActivity mainActivity = (MainActivity) getActivity();
                        // 顶部状态，就是没滚动过
                        if (scrollView.getScrollY() <= 0) {
                            //内部的ViewPager滑动的时候也要让下拉刷新停用。

                            mainActivity.enableSwipeRefresh();
                        }else {
                            mainActivity.DisableSwipeRefresh();
                        }

                        break;
                    }
                }
                return false;
            }
        });
    }
    //通过城市id取出天气数据设置进view中
    private void initViewPager() {
        //这里和MainActivity中的原理相同，方法之后容器就被CG掉了,
        //不必担心重复添加相同的碎片。
        mFragmentList = new ArrayList<Fragment>();
        mViewPager = (ViewPager) view.findViewById(R.id.innerViewpager);
        //碎片存在的情况下加入viewpager的碎片不会再次进行new,很智能。
        //这三个只会在第一次新建实例，之后在Viewpager里只会动用tag找现成的。
        //不必担心重复创建的问题。
        middleFragment = MiddleFragment.newInstance(mCityId);
        rightFragment = RightFragment.newInstance(mCityId);
        mFragmentList.add(middleFragment);
        mFragmentList.add(rightFragment);
        mInnerAdapter = new InnerPagerAdapter(fm, mFragmentList);
        mViewPager.setAdapter(mInnerAdapter);
        //mViewPager.setCurrentItem(1);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //内部的ViewPager滑动的时候也要让下拉刷新停用。
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.enableDisableSwipeRefresh(arg0 == ViewPager.SCROLL_STATE_IDLE);
            }
        });
    }

    /**
     * 获取天气情况，并设置UI
     */
    public void update() {
        mForecastBiz.getRefreshForecast(mCityId, CommonUtil.IsNetAvailable(getActivity()),false);
    }

    //在这里更新UI,仅更新这个Fragment里的数据。
    //不管是初始化还是再刷新都用这里的方法。
    @Override
    public void UpdateUI() {
        mBottomOne.setUI();
        mBottomTwo.setUI();
        //销毁视图，重建视图。
        mInnerAdapter.notifyDataSetChanged();
        //联网更新之后隐藏小鸡，显示天气。
        if (mIsLoading){
            mLoading.setVisibility(View.GONE);
            //view.findViewById(R.id.animation).setVisibility(View.GONE);
            view.findViewById(R.id.layout).setVisibility(View.VISIBLE);
            mIsLoading=false;
        }

    }

    //下方两个碎片之间切换
    @Override
    public void onClick(View v) {
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.button_left:
                mButtonRight.setAlpha(120);
                mButtonLeft.setAlpha(255);
                transaction.hide(mBottomTwo);
                transaction.show(mBottomOne);
                break;
            case R.id.button_right:
                mButtonRight.setAlpha(255);
                mButtonLeft.setAlpha(120);
                transaction.hide(mBottomOne);
                transaction.show(mBottomTwo);
                break;
        }
        // 事务提交
        transaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, mCityId + "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, mCityId + "onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, mCityId + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, mCityId + "onStop");
    }
}
