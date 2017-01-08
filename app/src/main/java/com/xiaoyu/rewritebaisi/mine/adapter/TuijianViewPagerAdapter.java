package com.xiaoyu.rewritebaisi.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaoyu.rewritebaisi.mine.fragment.OneFragment;
import com.xiaoyu.rewritebaisi.mine.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yuxiaobai on 2017/1/8.
 */

public class TuijianViewPagerAdapter extends FragmentPagerAdapter {

    private String mTitle[] = {"最热", "最新"};
    private List<Fragment> list;

    public TuijianViewPagerAdapter(FragmentManager mComtext) {
        super(mComtext);
        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TwoFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
