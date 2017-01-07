package com.xiaoyu.rewritebaisi.essence.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.essence.fragment.NoteFragment;
import com.xiaoyu.rewritebaisi.essence.fragment.PingLunFragment;
import com.xiaoyu.rewritebaisi.essence.fragment.ShareFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiaobai on 2017/1/7.
 */

public class Personal_ViewPagerAdapter extends FragmentPagerAdapter {

    private final CommonBean.ListBean listBean;
    private String mTitle[] = {"帖子", "分享", "评论"};

    private List<Fragment> list;

    public Personal_ViewPagerAdapter(FragmentManager mContext, CommonBean.ListBean listBean) {
        super(mContext);
        this.listBean =listBean;
        initFragment();
    }



    private void initFragment() {
        list = new ArrayList<>();
        list.add(new NoteFragment(listBean));
        list.add(new ShareFragment(listBean));
        list.add(new PingLunFragment(listBean));
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
