package com.xiaoyu.rewritebaisi.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.essence.adapter.Personal_ViewPagerAdapter;
import com.xiaoyu.rewritebaisi.essence.bean.UserBean;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;
import com.xiaoyu.rewritebaisi.view.ImgListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListViewActivity extends AppCompatActivity {

    @InjectView(R.id.xListView)
    ImgListView personalListView;
    @InjectView(R.id.activity_list_view)
    LinearLayout activityListView;
    private MyListViewAdapter adapter;
    private List list;
    private int mTabCount = 3;
    private Personal_ViewPagerAdapter Viewpageradapter;
    private Context mContext;
    private CommonBean.ListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.inject(this);
        mContext = ListViewActivity.this;
        getInfo();
        initlist();
        initView();

    }

    private void getInfo() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listBean = (CommonBean.ListBean) bundle.getSerializable("object");

    }

    private void initlist() {
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("我是item" + i);
        }
    }

    /**
     * 添加头
     */
    private TextView fans;
    private TextView guanzhu;
    private TextView level;

    private void initView() {
        View view = View.inflate(this, R.layout.second_head, null);
        ImageView image = (ImageView) view.findViewById(R.id.imageView2);
        fans = (TextView) view.findViewById(R.id.fans);
        guanzhu = (TextView) view.findViewById(R.id.guanzhu);
        level = (TextView) view.findViewById(R.id.level);
        personalListView.addHeaderView(view);
        Glide.with(ListViewActivity.this).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(image);
        getDataFromNet();

    }

    private void getDataFromNet() {
        GetNet.get(ContantUtils.PERSONAL_HEAD + listBean.getU().getUid() + ContantUtils.PERSONAL_FOOT, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "__----------------__-->" + json);
                parseData(json);
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    private void parseData(String json) {
        UserBean userBean = processData(json);
        if (json != null) {
            adapter = new MyListViewAdapter();
            personalListView.setAdapter(adapter);
            fans.setText(userBean.getData().getFans_count() + " 粉丝");
            guanzhu.setText(userBean.getData().getFollow_count() + "关注");
            level.setText("等级: " + userBean.getData().getLevel());
        }
    }

    private UserBean processData(String json) {
        return JSON.parseObject(json, UserBean.class);
    }

    class MyListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(ListViewActivity.this, R.layout.text_listview_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            initDataForRecyclerView(holder);

            return convertView;
        }


    }

    private void initDataForRecyclerView(ViewHolder holder) {

        Viewpageradapter = new Personal_ViewPagerAdapter(getSupportFragmentManager(),listBean);

        holder.idViewpager.setAdapter(Viewpageradapter);

        holder.idTablayout.setupWithViewPager(holder.idViewpager);
    }

    static class ViewHolder {
        @InjectView(R.id.id_tablayout)
        TabLayout idTablayout;
        @InjectView(R.id.id_viewpager)
        ViewPager idViewpager;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }

}
