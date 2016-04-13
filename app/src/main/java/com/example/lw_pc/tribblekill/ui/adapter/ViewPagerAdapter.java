package com.example.lw_pc.tribblekill.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LW-PC on 2016/4/12.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;                              //tab名的列表

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return list_Title.get(position % list_Title.size());
    }
}
