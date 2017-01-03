package com.xiaoyu.rewritebaisi.mine.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.BaseFragment;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.mine.adapter.PagerOneAdapter;
import com.xiaoyu.rewritebaisi.mine.adapter.PagerTwnPager;
import com.xiaoyu.rewritebaisi.mine.bean.PagerOneBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2016/12/30.
 */

public class PagerTwoFragment extends BaseFragment {
    LayoutInflater inflater;
    @InjectView(R.id.recycle_mine)
    RecyclerView recycleMine;
    private List<PagerOneBean.SquareListBean> list;
    private PagerTwnPager adapter;

    @Override
    protected View initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_mine_pager_twn, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();

    }

    private void initClickListener() {
        adapter.setOnItemClickListener(new PagerOneAdapter.OnItemClickListener() {
            @Override
            public void getPosition(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(mContext, "彩铃", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(mContext, "美女写真", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(mContext, "超人塔防", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(mContext, "传世H5", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(mContext, "下载视频", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(mContext, "头条新闻", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(mContext, "爬塔三国", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(mContext, "更多", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }


    private void getDataFromNet() {
        GetNet.get(ContantUtils.MINE_GRID, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "成功");
                paraseData(json);

            }

            @Override
            public void onError(String e) {

            }
        });

    }

    private void paraseData(String json) {
        PagerOneBean pagerOneBean = processData(json);
        list = pagerOneBean.getSquare_list();
        if (json != null) {
            adapter = new PagerTwnPager(mContext, list);
            recycleMine.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 5);
            recycleMine.setLayoutManager(manager);
            initClickListener();
        }
    }

    private PagerOneBean processData(String json) {
        return JSON.parseObject(json, PagerOneBean.class);
    }
}
