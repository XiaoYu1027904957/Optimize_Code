package com.xiaoyu.rewritebaisi.detials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.essence.bean.CommentBean;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class DetialActivity extends AppCompatActivity {


    @InjectView(R.id.rank_back)
    TextView rankBack;
    @InjectView(R.id.more_main)
    TextView moreMain;
    @InjectView(R.id.pinglun)
    LinearLayout pinglun;
    @InjectView(R.id.show_info)
    ListView showInfo;
    @InjectView(R.id.voice_mian)
    ImageView voiceMian;
    @InjectView(R.id.weite_mian)
    EditText weiteMian;
    @InjectView(R.id.picture_mian)
    ImageView pictureMian;
    @InjectView(R.id.send_jignhua)
    Button sendJignhua;
    @InjectView(R.id.writepinglun)
    LinearLayout writepinglun;
    @InjectView(R.id.activity_random)
    RelativeLayout activityRandom;
    @InjectView(R.id.videocontroller1)
    JCVideoPlayer videocontroller1;
    @InjectView(R.id.video_duration)
    TextView videoDuration;
    @InjectView(R.id.rl_1)
    RelativeLayout rl1;
    @InjectView(R.id.text_zan)
    TextView textZan;
    @InjectView(R.id.text_sun)
    TextView textSun;
    @InjectView(R.id.text_zhuan)
    TextView textZhuan;
    @InjectView(R.id.text_news)
    TextView textNews;
    @InjectView(R.id.text_pinglun)
    LinearLayout textPinglun;
    @InjectView(R.id.text_intriduce)
    TextView textIntriduce;
    private MyListadapter adapter;
    List<CommentBean.NormalBean.ListBeanX> list;
    private Context mContext;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        ButterKnife.inject(this);
        mContext = getApplicationContext();
        setListViewHeightBasedOnChildren(showInfo);
        Intent intent = getIntent();
        position = intent.getStringExtra("position");
        initData();

    }

    private void setListViewHeightBasedOnChildren(ListView showInfo) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = showInfo.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, showInfo);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = showInfo.getLayoutParams();
        params.height = totalHeight + (showInfo.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        showInfo.setLayoutParams(params);
    }

    private void initData() {
        getDaTaFromNet();
    }

    private void getDaTaFromNet() {
        GetNet.get(ContantUtils.DETIAL_BASE + position + ContantUtils.FOOT_URL, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                parseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "联网失败");

            }
        });
    }

    private void parseData(String json) {
        CommentBean commentBean = peocessData(json);
        list = commentBean.getNormal().getList();
        if (json != null) {
            adapter = new MyListadapter();
            showInfo.setAdapter(adapter);
        }
    }

    private CommentBean peocessData(String json) {

        return JSON.parseObject(json, CommentBean.class);
    }

    @OnClick({R.id.rank_back, R.id.more_main, R.id.voice_mian, R.id.weite_mian, R.id.picture_mian, R.id.send_jignhua})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rank_back:
                finish();
                break;
            case R.id.more_main:
                break;
            case R.id.voice_mian:
                break;
            case R.id.weite_mian:
                break;
            case R.id.picture_mian:
                break;
            case R.id.send_jignhua:
                break;
        }
    }


    class MyListadapter extends BaseAdapter {
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
                convertView = View.inflate(DetialActivity.this, R.layout.list_comment, null);
                holder = new ViewHolder(convertView);
                holder.commentIcon = (ImageView) convertView.findViewById(R.id.comment_icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CommentBean.NormalBean.ListBeanX listBeanX = list.get(position);

            Glide.with(mContext).load(listBeanX.getUser().getProfile_image()).transform(new GlideCircleTransform(DetialActivity.this)).into(holder.commentIcon);
            holder.userLevel.setText(listBeanX.getUser().getTotal_cmt_like_count());
            holder.sex.setText(listBeanX.getUser().getSex());
            holder.name.setText(listBeanX.getUser().getUsername());
            holder.content.setText(listBeanX.getContent());

            return convertView;
        }


    }

    static class ViewHolder {
        //        @InjectView(R.id.comment_icon)
        ImageView commentIcon;
        @InjectView(R.id.user_level)
        TextView userLevel;
        @InjectView(R.id.sex)
        TextView sex;
        @InjectView(R.id.comment_zan)
        ImageView commentZan;
        @InjectView(R.id.edit_zan)
        TextView editZan;
        @InjectView(R.id.comment_cai)
        ImageView commentCai;
        @InjectView(R.id.edit_cai)
        TextView editCai;
        @InjectView(R.id.comment_name)
        TextView name;
        @InjectView(R.id.comment_content)
        TextView content;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
