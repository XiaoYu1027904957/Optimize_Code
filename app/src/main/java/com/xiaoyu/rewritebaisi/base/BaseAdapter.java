package com.xiaoyu.rewritebaisi.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public class BaseAdapter extends FragmentPagerAdapter {

    /**
     * 装载fragment的集合
     * @param fragmentManager
     */
    private List<Fragment> fragments;
    public BaseAdapter(FragmentManager fm) {
        super(fm);
        inifragment();
    }

    private void inifragment() {
        fragments = new ArrayList<>();


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     *  设置标题
     *  针对TabLayout来进行设置
     */
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titles[position];
//    }


}
