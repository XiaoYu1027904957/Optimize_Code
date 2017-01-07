package com.xiaoyu.rewritebaisi.app;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.base.GetNet;
import com.xiaoyu.rewritebaisi.base.OnGetListener;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;
import com.xiaoyu.rewritebaisi.mine.adapter.MineCommonAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class RankActivity extends AppCompatActivity {

    @InjectView(R.id.rank_back)
    TextView rankBack;
    @InjectView(R.id.rank_recyclerView)
    RecyclerView rankRecyclerView;
    @InjectView(R.id.activity_rank)
    LinearLayout activityRank;
    List<CommonBean.ListBean> list;
    @InjectView(R.id.bangdan_all)
    TextView bangdanAll;
    @InjectView(R.id.bangdan_day)
    TextView bangdanDay;
    @InjectView(R.id.bangdan_most)
    TextView bangdanMost;
    private MineCommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.inject(this);
        initData();
        OnClickListener();
    }

    private void OnClickListener() {
        rankBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bangdanAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        bangdanDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDayPopupWindow();
            }
        });
        bangdanMost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMostPopupWindow();
            }
        });

    }

    private void initData() {
        getDataFromNet();
    }

    @OnClick(R.id.rank_back)
    public void onClick() {
        finish();
    }

    public void getDataFromNet() {
        GetNet.get(ContantUtils.MINE_CHART, null, new OnGetListener() {
            @Override
            public void onSuccess(String json) {
                Log.e("TAG", "----------->" + json);
                parseData(json);
            }

            @Override
            public void onError(String e) {
                Log.e("TAG", "error" + e);

            }
        });
    }

    private void parseData(String json) {
        CommonBean commonBean = processData(json);
        list = commonBean.getList();
        if (json != null) {
            adapter = new MineCommonAdapter(this, list);
            rankRecyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            rankRecyclerView.setLayoutManager(manager);
//
        }
    }

    private CommonBean processData(String json) {
        return JSON.parseObject(json, CommonBean.class);
    }




    private void showPopupWindow() {
//        1.使用layoutInflate獲得view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_show_edit_forall, null);
//          2.得到宽高getWindow().getDecorView().getWith;设置popupwindow的宽高
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//         设置参数
//         设置popupwindow可点击，必须进行如下设置
        window.setFocusable(true);
//        实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
//        设置popupwindow显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        final Button  login_cancel = (Button) view.findViewById(R.id.login_canceller);
        final Button  login_phone = (Button) view.findViewById(R.id.login_phone);
        final Button login_sina = (Button) view.findViewById(R.id.login_sina);
        final Button login_tencen = (Button) view.findViewById(R.id.login_tencen);
        final Button login_voice = (Button) view.findViewById(R.id.login_voice);


//         实例化布局文件

//        显示设置
        window.showAtLocation(RankActivity.this.findViewById(R.id.toolbar), Gravity.RIGHT, 0, 0);

//         设置点击取消
        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "段子", Toast.LENGTH_SHORT).show();
                bangdanAll.setText(login_cancel.getText());
                window.dismiss();
            }
        });
        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "全部", Toast.LENGTH_SHORT).show();
                bangdanAll.setText(login_phone.getText());
                window.dismiss();
            }
        });
        login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "视频", Toast.LENGTH_SHORT).show();
                bangdanAll.setText(login_sina.getText());
                window.dismiss();
            }
        });
        login_tencen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "图片", Toast.LENGTH_SHORT).show();
                bangdanAll.setText(login_tencen.getText());
                window.dismiss();
            }
        });
        login_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "声音", Toast.LENGTH_SHORT).show();
                bangdanAll.setText(login_voice.getText());
                window.dismiss();
            }
        });
    }

    private void showDayPopupWindow() {
//        1.使用layoutInflate獲得view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_show_edit_forday, null);
//          2.得到宽高getWindow().getDecorView().getWith;设置popupwindow的宽高
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//         设置参数
//         设置popupwindow可点击，必须进行如下设置
        window.setFocusable(true);
//        实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
//        设置popupwindow显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        final Button  login_cancel = (Button) view.findViewById(R.id.login_canceller);
        final Button  login_phone = (Button) view.findViewById(R.id.login_phone);
        final Button login_sina = (Button) view.findViewById(R.id.login_sina);
        final Button login_tencen = (Button) view.findViewById(R.id.login_tencen);
        final Button login_voice = (Button) view.findViewById(R.id.login_voice);


//         实例化布局文件

//        显示设置
        window.showAtLocation(RankActivity.this.findViewById(R.id.toolbar), Gravity.RIGHT, 0, 0);

//         设置点击取消
        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "段子", Toast.LENGTH_SHORT).show();
                bangdanDay.setText(login_cancel.getText());
                window.dismiss();
            }
        });
        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "全部", Toast.LENGTH_SHORT).show();
                bangdanDay.setText(login_phone.getText());
                window.dismiss();
            }
        });
        login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "视频", Toast.LENGTH_SHORT).show();
                bangdanDay.setText(login_sina.getText());
                window.dismiss();
            }
        });
        login_tencen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "图片", Toast.LENGTH_SHORT).show();
                bangdanDay.setText(login_tencen.getText());
                window.dismiss();
            }
        });
        login_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "声音", Toast.LENGTH_SHORT).show();
                bangdanDay.setText(login_voice.getText());
                window.dismiss();
            }
        });
    }
    private void showMostPopupWindow() {
//        1.使用layoutInflate獲得view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_show_edit_formost, null);
//          2.得到宽高getWindow().getDecorView().getWith;设置popupwindow的宽高
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//         设置参数
//         设置popupwindow可点击，必须进行如下设置
        window.setFocusable(true);
//        实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
//        设置popupwindow显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        final Button  login_phone = (Button) view.findViewById(R.id.login_phone);
        final Button login_sina = (Button) view.findViewById(R.id.login_sina);
        final Button login_tencen = (Button) view.findViewById(R.id.login_tencen);


//         实例化布局文件

//        显示设置
        window.showAtLocation(RankActivity.this.findViewById(R.id.toolbar), Gravity.RIGHT, 0, 0);

//         设置点击取消
        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "全部", Toast.LENGTH_SHORT).show();
                bangdanMost.setText(login_phone.getText());
                window.dismiss();
            }
        });
        login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "视频", Toast.LENGTH_SHORT).show();
                bangdanMost.setText(login_sina.getText());
                window.dismiss();
            }
        });
        login_tencen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RankActivity.this, "图片", Toast.LENGTH_SHORT).show();
                bangdanMost.setText(login_tencen.getText());
                window.dismiss();
            }
        });
    }



}
