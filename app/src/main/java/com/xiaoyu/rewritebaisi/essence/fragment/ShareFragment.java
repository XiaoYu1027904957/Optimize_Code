package com.xiaoyu.rewritebaisi.essence.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.BaseFragment;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.NotesAdapter;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.essence.bean.TieZiBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2017/1/7.
 */

public class ShareFragment extends BaseFragment {
    private final CommonBean.ListBean listBean;
    @InjectView(R.id.recycler_share)
    RecyclerView recyclerShare;
    private NotesAdapter adapter;
    List<TieZiBean.ListBean> list;

    public ShareFragment(CommonBean.ListBean listBean) {
        this.listBean = listBean;
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_share, null);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        GetNet.get(ContantUtils.PERSONAL_SHARE_BASE + listBean.getU().getUid() + ContantUtils.PERSONAL_SHARE_FOOT_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                parseData(json);
                if (json != null) {
                    adapter = new NotesAdapter(mContext, list);
                    recyclerShare.setAdapter(adapter);
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    recyclerShare.setLayoutManager(manager);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    private void parseData(String json) {
        TieZiBean tieZiBean = processData(json);
        list = tieZiBean.getList();
    }

    private TieZiBean processData(String json) {
        return JSON.parseObject(json, TieZiBean.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
