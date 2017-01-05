package com.xiaoyu.rewritebaisi.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2017/1/3.
 */

public class MyFragment extends BaseFragment {
    private final int position;
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private RecyclerViewAdapter adapter;
    private List<CommonBean.ListBean> list;

    public MyFragment(int position) {
        this.position = position;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.item_recycle, null);
        ButterKnife.inject(this, view);
        refresh.setSunStyle(true);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(position);
        initMaterialRefreash();
    }

    public void initMaterialRefreash() {
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromNet(position);
                        materialRefreshLayout.finishRefresh();

                    }
                }, 3000);

            }

            @Override
            public void onfinish() {
                Toast.makeText(mContext, "finish", Toast.LENGTH_LONG).show();
            }


        });
    }

    public void getDataFromNet(int position) {

        switch (position) {
            case 0:
                GetNet.get(ContantUtils.ESSENCE, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;

            case 1:
                GetNet.get(ContantUtils.ESSENCE_VIDEO, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 2:
                GetNet.get(ContantUtils.ESSENCE_PICTURE, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 3:
                GetNet.get(ContantUtils.ESSENCE_FIELD, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 4:
                GetNet.get(ContantUtils.ESSENCE_RANKING, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 5:
                GetNet.get(ContantUtils.ESSENCE_sociology, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 6:
                GetNet.get(ContantUtils.ESSENCE_BEAUTY, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 7:
                GetNet.get(ContantUtils.ESSENCE_KNOW, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 8:
                GetNet.get(ContantUtils.ESSENCE_GAME, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
            case 9:
                GetNet.get(ContantUtils.ESSENCE_NET_HOT, null, new OnGetListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.e("TAG", "联网成功");
                        parseData(json);
                    }

                    @Override
                    public void onError(String e) {
                        Log.e("TAG", "联网失败");

                    }
                });
                break;
        }


    }

    private void parseData(String json) {
        CommonBean commonBean = processData(json);
        list = commonBean.getList();
        if (list != null) {
//        设置适配器
            adapter = new RecyclerViewAdapter(mContext, list,commonBean);
            adapter.notifyDataSetChanged();
            recycler.setAdapter(adapter);
            LinearLayoutManager manger = new LinearLayoutManager(mContext);
            recycler.setLayoutManager(manger);


        }
    }

    private CommonBean processData(String json) {
        return JSON.parseObject(json, CommonBean.class);
    }


}
