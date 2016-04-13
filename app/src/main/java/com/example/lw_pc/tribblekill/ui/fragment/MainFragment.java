package com.example.lw_pc.tribblekill.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LW-PC on 2016/4/12.
 * Main Fragment
 */
public class MainFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private Toolbar mToolBar;

    private List<Fragment> list_fragment;
    private List<String> list_title;


    public static Fragment newInstance() {
        return new MainFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_main, container, false);

        initControls(view);

        return view;
    }

    /**
     * 初始化各控件
     * @param view
     */
    private void initControls(View view) {
        mTabLayout = (TabLayout)view.findViewById(R.id.tabs);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(mToolBar);

        list_fragment = new ArrayList<>();
        list_fragment.add(new ListFragment());
        list_fragment.add(new ListFragment());
        list_fragment.add(new ListFragment());
        list_fragment.add(new ListFragment());

        list_title = new ArrayList<>();
        String[] tabTitle = getResources().getStringArray(R.array.tabLayout_item);
        for(String s: tabTitle) {
            list_title.add(s);
        }

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }


}
