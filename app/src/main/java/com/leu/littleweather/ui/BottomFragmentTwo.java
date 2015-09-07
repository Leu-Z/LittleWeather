package com.leu.littleweather.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leu.littleweather.R;

/**
 * Created by Leu on 2015/9/6.
 */
public class BottomFragmentTwo extends Fragment {
    private static final String ARG_TMPD = "day";
    private static final String ARG_TMPN = "night";
    private String mTmpD;
    private String mTmpN;

    public static BottomFragmentTwo newInstanceTwo(String tmpD,String tmpN) {

        BottomFragmentTwo fragment = new BottomFragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_TMPD, tmpD);
        args.putString(ARG_TMPN,tmpN);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mTmpD = getArguments().getString(ARG_TMPD);
            mTmpN = getArguments().getString(ARG_TMPN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_fragment_one, container, false);
        //根据传递进来的mNewsType生成特定的view
        TextView tmpD= (TextView) view.findViewById(R.id.tmp_d);
        TextView tmpN= (TextView) view.findViewById(R.id.tmp_n);
        tmpD.setText(mTmpD);
        tmpN.setText(mTmpN);

        return view;
    }



}
