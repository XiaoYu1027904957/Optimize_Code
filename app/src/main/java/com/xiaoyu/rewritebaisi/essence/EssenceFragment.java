package com.xiaoyu.rewritebaisi.essence;

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
import com.xiaoyu.rewritebaisi.essence.adapter.MyEssenceAdapter;
import com.xiaoyu.rewritebaisi.essence.bean.TabBean;

import java.util.List;

/**
 * Created by yuxiaobai on 2016/12/29.
 * 精华的fragment
 */

public class EssenceFragment extends BaseFragment {
    //    @InjectView(R.id.tl_8)
    SlidingTabLayout tl8;
    //   @InjectView(R.id.viewPager)
    ViewPager viewPager;
    private LayoutInflater inflater;
    private MyEssenceAdapter adapter;
    private List<TabBean.MenusBean.SubmenusBean> list;


    @Override
    protected View initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_essence, null);
//        ButterKnife.inject(this, view);
        tl8 = (SlidingTabLayout) view.findViewById(R.id.tl_8);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        return view;
    }

    /**
     * 装载数据
     */
    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    public void getDataFromNet() {

        GetNet.get(ContantUtils.ESSENCE_TAB_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                ParaseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "联网失败" + e);
            }
        });
    }


    /**
     * 解析Json数据
     */

    private void ParaseData(String json) {
        TabBean tabBean = parseData(json);
        list = tabBean.getMenus().get(0).getSubmenus();
        if (list != null) {

            adapter = new MyEssenceAdapter(getFragmentManager(), list);
            viewPager.setAdapter(adapter);
            tl8.setViewPager(viewPager);
        }


    }

    private TabBean parseData(String json) {
        return JSON.parseObject(json, TabBean.class);
    }
}
