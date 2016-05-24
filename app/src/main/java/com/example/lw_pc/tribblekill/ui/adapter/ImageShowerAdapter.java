package com.example.lw_pc.tribblekill.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by LW-PC on 2016/5/23.
 */
public class ImageShowerAdapter extends PagerAdapter {
    private ImageView[] mImageViews;

    public ImageShowerAdapter(ImageView[] imageViews) {
        mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

    }

    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    @Override
    public Object instantiateItem(View container, int position) {
        try {
            ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
        }catch(Exception e){
            //handler something
        }
        return mImageViews[position % mImageViews.length];
    }



}







