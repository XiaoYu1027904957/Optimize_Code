package com.xiaoyu.rewritebaisi.essence.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaoyu.rewritebaisi.base.MyFragment;
import com.xiaoyu.rewritebaisi.essence.bean.TabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public class MyEssenceAdapter extends FragmentPagerAdapter {
    private List<TabBean.MenusBean.SubmenusBean> datas = null;
    private List<Fragment> list;

    public MyEssenceAdapter(FragmentManager fm, List<TabBean.MenusBean.SubmenusBean> list) {
        super(fm);
        datas = list;
        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            MyFragment fragment = new MyFragment(i);
            list.add(fragment);
        }

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
        return datas.get(position).getName();
    }
}
