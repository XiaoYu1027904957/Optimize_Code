package com.xiaoyu.rewritebaisi.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaoyu.rewritebaisi.mine.bean.TuijianBean;
import com.xiaoyu.rewritebaisi.mine.fragment.PagerOneFragment;
import com.xiaoyu.rewritebaisi.mine.fragment.PagerTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiaobai on 2016/12/30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<TuijianBean.RecTagsBean> datas;
    private List<Fragment> list;


    public ViewPagerAdapter(FragmentManager fm, List<TuijianBean.RecTagsBean> datas) {
        super(fm);
        this.datas = datas;
        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        list.add(new PagerOneFragment());
        list.add(new PagerTwoFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
