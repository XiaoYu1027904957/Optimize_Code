package com.xiaoyu.rewritebaisi.newpost.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.xiaoyu.rewritebaisi.essence.bean.TabBean;
import com.xiaoyu.rewritebaisi.newpost.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public class MyNewsPostAdapter extends FragmentPagerAdapter {
    private final List<TabBean.MenusBean.SubmenusBean> datas;
    private List<Fragment> list;

    public MyNewsPostAdapter(FragmentManager fm, List<TabBean.MenusBean.SubmenusBean> datas) {
        super(fm);
        this.datas = datas;
        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            NewsFragment fragment = new NewsFragment(i);
            list.add(fragment);
        }


    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        Log.e("TAG", "list-->" + list.size());
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getName();
    }


}
