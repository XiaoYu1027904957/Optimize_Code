package com.xiaoyu.rewritebaisi.detials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.essence.adapter.RecyclerDetialAdapter;
import com.xiaoyu.rewritebaisi.essence.bean.DetialsBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DetialShowActivity extends AppCompatActivity {

    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.rank_back)
    TextView rankBack;
    private RecyclerDetialAdapter adapter;
    private String position;
    List<DetialsBean.NormalBean.ListBeanX> list;
    private String url;
    private String image;
    private String name;
    private String time;
    private String text;
    private String imageurl;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_show);
        ButterKnife.inject(this);
        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        position = intent.getStringExtra("position");
        url = intent.getStringExtra("url");
        image = intent.getStringExtra("image");
        name = intent.getStringExtra("name");
        time = intent.getStringExtra("time");
        text = intent.getStringExtra("text");
        imageurl = intent.getStringExtra("imageurl");
        type = intent.getStringExtra("type");
        getDataFromNet();
    }

    public void getDataFromNet() {
        GetNet.get(ContantUtils.DETIAL_BASE + position + ContantUtils.FOOT_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "详情界面联网成功");
                paraseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "详情界面联网失败");

            }
        });
    }

    private void paraseData(String json) {
        DetialsBean detialsBean = processData(json);
        list = detialsBean.getNormal().getList();
        if (json != null) {
            adapter = new RecyclerDetialAdapter(this, list, url, image, name, time, text, imageurl,type);
            recycler.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(DetialShowActivity.this);
            recycler.setLayoutManager(manager);
            recycler.scrollToPosition(1);

        }

    }

    private DetialsBean processData(String json) {
        return JSON.parseObject(json, DetialsBean.class);
    }

    @OnClick(R.id.rank_back)
    public void onClick() {
        finish();
    }
}
