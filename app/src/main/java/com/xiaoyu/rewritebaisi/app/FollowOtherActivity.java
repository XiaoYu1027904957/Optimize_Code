package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.follow.NewRecyclerAdapter;
import com.xiaoyu.rewritebaisi.follow.NewRecyclerPosAdapter;
import com.xiaoyu.rewritebaisi.follow.bean.ListViewBean;
import com.xiaoyu.rewritebaisi.follow.bean.RecyclerPosViewBean;
import com.xiaoyu.rewritebaisi.follow.bean.RecyclerViewBean;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FollowOtherActivity extends AppCompatActivity {


    @InjectView(R.id.tv_cancle)
    TextView tvCancle;
    @InjectView(R.id.iv_shousuo)
    ImageView ivShousuo;
    @InjectView(R.id.rl_recommend)
    RelativeLayout rlRecommend;
    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;
    @InjectView(R.id.activity_recommend)
    PercentRelativeLayout activityRecommend;
    private MyListView listViewadapter;
    private List<ListViewBean.ListBean> list;
    private List<RecyclerViewBean.ListBean> datas;
    private NewRecyclerAdapter recycleradapter;
    private NewRecyclerPosAdapter recyclerposadapter;
    TextView textView;
    private List<RecyclerPosViewBean.TopListBean> dataspost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_other);
        ButterKnife.inject(this);
        initData();

        textView = new TextView(this);
        textView.setText("推荐");
        textView.setGravity(Gravity.CENTER);
        lvLeft.addHeaderView(textView);

    }

    private void initData() {
        GetNet.get(ContantUtils.LEFTLISTVIEW, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "内容获取成功---------->" + json);
                parseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "联网失败");
            }
        });

    }

    private void parseData(String json) {
        ListViewBean listViewBean = processData(json);
        list = listViewBean.getList();

        if (json != null) {
            listViewadapter = new MyListView();
            lvLeft.setAdapter(listViewadapter);
        }

        initDataPosRecyclerView(0);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    initDataPosRecyclerView(0);
                } else {

                    initDataRecyclerView(position);
                }

            }
        });


    }

    private void initDataPosRecyclerView(int position) {
        GetNet.get(ContantUtils.TUIJINA_FOLLOW, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "--->" + json);
                parsePosRecyclerData(json);
            }

            @Override
            public void onError(String e) {

            }
        });
    }


    private void initDataRecyclerView(int position) {
        GetNet.get(ContantUtils.FOLLOW_BASE + list.get(position-1).getId() + ContantUtils.FOLLOW_FOOT_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "--->" + json);
                parseRecyclerData(json);
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    private void parsePosRecyclerData(String json) {
        RecyclerPosViewBean recyclerposViewBean = processPosRecyclerData(json);
        dataspost = recyclerposViewBean.getTop_list();
        if (json != null) {
            recyclerposadapter = new NewRecyclerPosAdapter(FollowOtherActivity.this, dataspost);
            recyclerposadapter.notifyDataSetChanged();
            rvRight.setAdapter(recyclerposadapter);
            LinearLayoutManager manager = new LinearLayoutManager(FollowOtherActivity.this);
            rvRight.setLayoutManager(manager);
        }
    }

    private RecyclerPosViewBean processPosRecyclerData(String json) {

        return JSON.parseObject(json, RecyclerPosViewBean.class);
    }

    private void parseRecyclerData(String json) {
        RecyclerViewBean recyclerViewBean = processRecyclerData(json);
        datas = recyclerViewBean.getList();
        if (json != null) {
            recycleradapter = new NewRecyclerAdapter(FollowOtherActivity.this, datas);
            recycleradapter.notifyDataSetChanged();
            rvRight.setAdapter(recycleradapter);
            LinearLayoutManager manager = new LinearLayoutManager(FollowOtherActivity.this);
            rvRight.setLayoutManager(manager);
        }
    }

    private RecyclerViewBean processRecyclerData(String json) {
        return JSON.parseObject(json, RecyclerViewBean.class);
    }


    private ListViewBean processData(String json) {
        return JSON.parseObject(json, ListViewBean.class);
    }

    @OnClick({R.id.tv_cancle, R.id.iv_shousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                finish();
                break;
            case R.id.iv_shousuo:
                Toast.makeText(FollowOtherActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    class MyListView extends BaseAdapter {
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
                convertView = View.inflate(FollowOtherActivity.this, R.layout.listview_left_show, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ListViewBean.ListBean listBean = list.get(position);
            holder.textView.setText(listBean.getName());

            return convertView;
        }

        class ViewHolder {
            TextView textView;
        }
    }


}
