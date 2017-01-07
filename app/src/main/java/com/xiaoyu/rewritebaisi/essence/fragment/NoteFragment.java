package com.xiaoyu.rewritebaisi.essence.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class NoteFragment extends BaseFragment {
    private final CommonBean.ListBean listBean;
    @InjectView(R.id.recycler_notes)
    RecyclerView recyclerNotes;
    private NotesAdapter adapter;
    List<TieZiBean.ListBean> list;

    public NoteFragment(CommonBean.ListBean listBean) {
        this.listBean = listBean;
    }

    @Override
    protected View initView() {

        View view = View.inflate(mContext, R.layout.fragment_notes, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        GetNet.get(ContantUtils.PERSONAL_TIEZI_BASE + listBean.getU().getUid() + ContantUtils.PERSONAL_TIEZI_FOOT_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                parseData(json);
                if (json != null) {
                    adapter = new NotesAdapter(mContext, list);
                    recyclerNotes.setAdapter(adapter);
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    recyclerNotes.setLayoutManager(manager);
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


}
