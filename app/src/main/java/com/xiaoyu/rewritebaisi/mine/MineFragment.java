package com.xiaoyu.rewritebaisi.mine;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.viewpagerindicator.CirclePageIndicator;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.LoginActivity;
import com.xiaoyu.rewritebaisi.app.ShowTuijianActivity;
import com.xiaoyu.rewritebaisi.base.BaseFragment;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.mine.adapter.ViewPagerAdapter;
import com.xiaoyu.rewritebaisi.mine.bean.TuijianBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2016/12/29.
 * 我的的fragment
 */

public class MineFragment extends BaseFragment {
    LayoutInflater inflater;
    @InjectView(R.id.image_user)
    ImageView imageUser;
    @InjectView(R.id.login_in)
    TextView loginIn;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.mine_listView)
    ListView mineListView;
    @InjectView(R.id.login_in_more)
    ImageView login_more;
    @InjectView(R.id.circle_barner)
    CirclePageIndicator circleBarner;

    private List<TuijianBean.RecTagsBean> list;
    private MyListViewAdapter adapter;
    private ViewPagerAdapter viewPageradapter;

    @Override
    protected View initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        intOnclick();
        getDataFromNet();
    }

    private void intOnclick() {
        login_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    public void getDataFromNet() {
        GetNet.get(ContantUtils.MINE_GROOM, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "联网成功");
                paraseData(json);
            }


            @Override
            public void onError(String e) {
                Log.e("TAG", "联网失败");

            }
        });
    }

    private void paraseData(String json) {
        TuijianBean tuijianBean = processData(json);
        list = tuijianBean.getRec_tags();

        if (json != null) {
            adapter = new MyListViewAdapter();
            mineListView.setAdapter(adapter);
        }
        viewPageradapter = new ViewPagerAdapter(getChildFragmentManager(), list);
        viewpager.setOffscreenPageLimit(0);
        viewpager.setAdapter(viewPageradapter);
        circleBarner.setViewPager(viewpager);

    }

    private TuijianBean processData(String json) {
        return JSON.parseObject(json, TuijianBean.class);
    }


    class MyListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_listview_mine, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            TuijianBean.RecTagsBean recTagsBean = list.get(position);
//            头像图片
            Glide.with(mContext).load(recTagsBean.getImage_list()).into(holder.mineDingyueIcon);
//            频道名称
            holder.name.setText(recTagsBean.getTheme_name());
//            订阅人数
            holder.mineDignyue.setText(recTagsBean.getSub_number() + "人订阅");
//           总贴数
            holder.mineSum.setText("总帖数" + recTagsBean.getPost_num());

            initListener(convertView);
            return convertView;
        }


    }

    private void initListener(View convertView) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ShowTuijianActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    static class ViewHolder {
        @InjectView(R.id.mine_dingyue_icon)
        ImageView mineDingyueIcon;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.mine_dignyue)
        TextView mineDignyue;
        @InjectView(R.id.mine_sum)
        TextView mineSum;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


}
