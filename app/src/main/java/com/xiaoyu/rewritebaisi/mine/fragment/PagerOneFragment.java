package com.xiaoyu.rewritebaisi.mine.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.BaiJiaJieActivity;
import com.xiaoyu.rewritebaisi.app.ContentPrizeActivity;
import com.xiaoyu.rewritebaisi.app.LoginActivity;
import com.xiaoyu.rewritebaisi.app.RandomActivity;
import com.xiaoyu.rewritebaisi.app.RankActivity;
import com.xiaoyu.rewritebaisi.base.BaseFragment;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.mine.adapter.PagerOneAdapter;
import com.xiaoyu.rewritebaisi.mine.bean.PagerOneBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2016/12/30.
 */

public class PagerOneFragment extends BaseFragment {
    @InjectView(R.id.recycle_mine)
    RecyclerView recycleMine;
    private PagerOneAdapter adapter;
    private View view;
    private List<PagerOneBean.SquareListBean> list;
    private Intent intent;

    @Override
    protected View initView() {
        view = View.inflate(mContext, R.layout.item_mine_pager_one, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
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
            adapter = new PagerOneAdapter(mContext, list);
            recycleMine.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 5);
            recycleMine.setLayoutManager(manager);
            initClickListener();
        }

    }

    private PagerOneBean processData(String json) {
        return JSON.parseObject(json, PagerOneBean.class);
    }


    private void initClickListener() {
        adapter.setOnItemClickListener(new PagerOneAdapter.OnItemClickListener() {
            @Override
            public void getPosition(int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(mContext, "内容贡献榜", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, ContentPrizeActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(mContext, "排行榜", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, RankActivity.class);
                        mContext.startActivity(intent);

                        break;
                    case 3:
//                        Toast.makeText(mContext, "我的收藏", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 4:
//                        Toast.makeText(mContext, "随机穿越", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, RandomActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 5:
//                        Toast.makeText(mContext, "败家姐", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext,BaiJiaJieActivity.class);
                        break;
                    case 6:
//                        Toast.makeText(mContext, "百思帮派", Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                        break;
                    case 7:
                        Toast.makeText(mContext, "游戏大厅", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        Toast.makeText(mContext, "美女直播", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }
}
