package com.leu.littleweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.leu.littleweather.R;
import com.leu.littleweather.biz.ForecastBiz;
import com.leu.littleweather.util.CommonUtil;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by Leu on 2015/9/4.
 */
public class OutterFragment extends BaseFragment implements ForecastBiz.FTwoBtnClickListener, OnClickListener {
    private static final String ARG_CITY = "city";
    private static final String TAG = "OutterFragment";
    private String mCity;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private PtrFrameLayout frame;
    private MaterialHeader header;
    private BottomFragmentOne mBottomOne;
    private Button mButtonLeft;
    private Button mButtonRight;
    private ForecastBiz mForecastBiz;
    private BottomFragmentTwo mBottomTwo;
    private View view;
    private InnerPagerAdapter mInnerAdapter;
    private MiddleFragment middleFragment;
    private LeftFragment leftFragment;
    private RightFragment rightFragment;

    public static OutterFragment newInstance(String city) {

        OutterFragment fragment = new OutterFragment();
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
            mForecastBiz = new ForecastBiz(getActivity());
            mForecastBiz.setfTwoBtnClickListener(this);
            Log.d("OutterFragment","onCreate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.outter_fragment, container, false);
        //根据传递进来的mNewsType生成特定的view
        init();
        return view;
    }

    private void init() {
        final Activity parentActivity = getActivity();
        //这个用于下拉刷新
        frame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        //Material风格的Header
        header = new MaterialHeader(parentActivity.getBaseContext());
        header.setPadding(0, 20, 0, 20);
        header.setPtrFrameLayout(frame);

        //加载的最小时间，不能小于1s
        frame.setLoadingMinTime(1000);
        //头部回弹时间
        frame.setDurationToCloseHeader(300);
        frame.setHeaderView(header);
        //下拉刷新的 UI 接口,由header实现
        frame.addPtrUIHandler(header);
        //刷新时，保持内容不动，仅头部下移
        frame.setPinContent(true);
        //viewpager左右移动时无法下拉刷新
        frame.disableWhenHorizontalMove(true);
        //下拉刷新的功能接口
        frame.setPtrHandler(new PtrHandler() {
            @Override//判断是否可以下拉刷新。
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {

                return true;
            }

            @Override//在这里写自己的刷新功能实现，处理业务数据的刷新。
            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
                //刷新天气信息，并刷新UI
                getForecastAndSetUi(true);
                frame.refreshComplete();
            }
        });
        mButtonLeft = (Button) view.findViewById(R.id.button_left);
        mButtonRight = (Button) view.findViewById(R.id.button_right);
        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);

        //获取天气情况，并设置默认UI
        getForecastAndSetUi(false);
    }

    /**
     * 获取天气情况，并设置UI
     *
     * @param ifRefresh
     */
    private void getForecastAndSetUi(boolean ifRefresh) {
        //是否刷新,是的话从网络获取,
        if (ifRefresh) {
            mForecastBiz.getRefreshForecast(mCity, CommonUtil.IsNetAvailable(getActivity()));
        } else {
            //否则依情况是从数据库还是网络获取,得到后放入数据库
            mForecastBiz.getCityForecast(mCity, CommonUtil.IsNetAvailable(getActivity()));
        }
    }

    //在这个方法里面设置默认的UI
    @Override
    public void setDefaultUI() {   //今天的天气碎片

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        //防止父Fragment因为翻页而视图销毁时，导致Fragment重复生成实例的bug
        //父Fragment视图重新创建的时候，嵌套在其中的子Fragment也会自动重新创建并呈现
        //不需要再做别的操作,如add,commit之类的
        //重新启动的Fragment，原来指向他的引用已经没用了，
        // 就是说那个引用为null，但是fragment还是存在的。所以把之前的fragment全部销毁是最直接简单的解决方法。
        if (fm.getFragments() != null && fm.getFragments().size() > 0) {
            Log.d(TAG, "remove all child fragment");
            for (Fragment childFragment : fm.getFragments()) {
                transaction.remove(childFragment);
            }
        }

        Log.d("OutterFragment","新建了bottmfragment");
        //为什么这里不能直接new？
        //// TODO: 2015/9/13 这里创建了两次碎片
        mBottomOne = BottomFragmentOne().newInstance(mCity);
        mBottomTwo = BottomFragmentTwo().newInstance(mCity);

        transaction.add(R.id.bottom_weather, mBottomTwo);
        transaction.hide(mBottomTwo);
        transaction.add(R.id.bottom_weather, mBottomOne);

        transaction.commit();



        initViewPager();

    }

    //在这里更新UI
    @Override
    public void UpdateUI() {
        mBottomOne.setUI();
        mBottomTwo.setUI();
        //重新加载三个Fragment
        mInnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getChildFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();

        switch (v.getId()) {
            case R.id.button_left:
                transaction.hide(mBottomTwo);
                transaction.show(mBottomOne);
                break;
            case R.id.button_right:
                transaction.hide(mBottomOne);
                transaction.show(mBottomTwo);
                break;
        }
        // 事务提交
        transaction.commit();
    }


    private void initViewPager() {
        mViewPager = (ViewPager) view.findViewById(R.id.innerViewpager);
        //在方法里创建的对象，没有引用后，会被回收
        FragmentManager fm = getChildFragmentManager();


        if (middleFragment == null||leftFragment==null||rightFragment==null) {
            middleFragment = MiddleFragment.newInstance(mCity);
            leftFragment = LeftFragment.newInstance(mCity);
            rightFragment = RightFragment.newInstance(mCity);
            mFragmentList.add(leftFragment);
            mFragmentList.add(middleFragment);
            mFragmentList.add(rightFragment);
            mInnerAdapter = new InnerPagerAdapter(fm, mFragmentList);

        }
        //因为viewpager会因为视图重建而被销毁，所以必须进行重新设置
        mViewPager.setAdapter(mInnerAdapter);
        mViewPager.setCurrentItem(1);

    }


}
