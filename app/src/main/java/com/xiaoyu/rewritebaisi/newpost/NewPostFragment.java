package com.xiaoyu.rewritebaisi.newpost;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.flyco.tablayout.SlidingTabLayout;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.BaseFragment;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.essence.bean.TabBean;
import com.xiaoyu.rewritebaisi.newpost.adapter.MyNewsPostAdapter;

import java.util.List;


/**
 * Created by yuxiaobai on 2016/12/29.
 * 新帖的fragment
 *
 */

public class NewPostFragment extends BaseFragment {
    LayoutInflater inflater;
//    @InjectView(R.id.tl_8)
    SlidingTabLayout tl8;
//    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    private List<TabBean.MenusBean.SubmenusBean> list;
    private MyNewsPostAdapter adapter;


    @Override
    protected View initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_newpost, null);
//        ButterKnife.inject(this, view);
        tl8 = (SlidingTabLayout) view.findViewById(R.id.tl_8);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }


    public void getDataFromNet() {
        GetNet.get(ContantUtils.ESSENCE_TAB_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                paraseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "联网失败" + e);
            }
        });
    }

    private void paraseData(String json) {
        TabBean newsPostBean = processData(json);
        list = newsPostBean.getMenus().get(1).getSubmenus();
        if (list != null) {
            adapter = new MyNewsPostAdapter(getChildFragmentManager(), list);
            viewPager.setAdapter(adapter);
            tl8.setViewPager(viewPager);
        }

    }

    private TabBean processData(String json) {
        return JSON.parseObject(json, TabBean.class);
    }


}
