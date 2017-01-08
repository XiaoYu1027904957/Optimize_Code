package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.mine.adapter.TuijianViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShowTuijianActivity extends AppCompatActivity {

    @InjectView(R.id.mine_listView_show)
    ListView mineListViewShow;
    @InjectView(R.id.activity_show_tuijian)
    LinearLayout activityShowTuijian;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private TuijianViewPagerAdapter Viewpageradapter;
    private MyListViewadapter myListViewadapter;
    View view;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tuijian);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        view = View.inflate(this, R.layout.show_tuijian, null);
        Viewpageradapter = new TuijianViewPagerAdapter(getSupportFragmentManager());


        mineListViewShow.addHeaderView(view);
        myListViewadapter = new MyListViewadapter();
        mineListViewShow.setAdapter(myListViewadapter);
        initMaterialRefreash();
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("我是item" + i);
        }
    }

    class MyListViewadapter extends BaseAdapter {
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
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(ShowTuijianActivity.this, R.layout.showtuijian_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            initDataForRecyclerView(viewHolder);
            return convertView;
        }


    }

    private void initDataForRecyclerView(ViewHolder holder) {

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

    public void initMaterialRefreash() {
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        materialRefreshLayout.finishRefresh();

                    }
                }, 3000);

            }

            @Override
            public void onfinish() {
                Toast.makeText(ShowTuijianActivity.this, "finish", Toast.LENGTH_LONG).show();
            }


        });
    }
}
