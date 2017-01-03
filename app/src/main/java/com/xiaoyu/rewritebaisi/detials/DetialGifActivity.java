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


public class DetialGifActivity extends AppCompatActivity {

    @InjectView(R.id.rank_back)
    TextView rankBack;
    @InjectView(R.id.more_main)
    TextView moreMain;
    @InjectView(R.id.pinglun)
    LinearLayout pinglun;
    @InjectView(R.id.title_bar)
    TextView titleBar;
    @InjectView(R.id.recycler_time)
    TextView recyclerTime;
    @InjectView(R.id.icon_mine)
    ImageView iconMine;
    @InjectView(R.id.rl_item)
    RelativeLayout rlItem;
    @InjectView(R.id.videocontroller1)
    ImageView videocontroller1;
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
    @InjectView(R.id.show_info)
    ListView showInfo;
    @InjectView(R.id.text_intriduce)
    TextView textIntriduce;
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

    private String position;
    List<CommentBean.NormalBean.ListBeanX> list;
    private MyListadapter adapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_gif);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        mContext = getApplicationContext();
        Intent intent = getIntent();
        position = intent.getStringExtra("position");

        getDaTaFromNet();
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
            DetialActivity.ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(DetialGifActivity.this, R.layout.list_comment, null);
                holder = new DetialActivity.ViewHolder(convertView);
                holder.commentIcon = (ImageView) convertView.findViewById(R.id.comment_icon);
                convertView.setTag(holder);
            } else {
                holder = (DetialActivity.ViewHolder) convertView.getTag();
            }

            CommentBean.NormalBean.ListBeanX listBeanX = list.get(position);

            Glide.with(mContext).load(listBeanX.getUser().getProfile_image()).transform(new GlideCircleTransform(DetialGifActivity.this)).into(holder.commentIcon);
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
