package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RandomActivity extends AppCompatActivity {

    @InjectView(R.id.rank_back)
    TextView rankBack;
    @InjectView(R.id.tl_8)
    SlidingTabLayout tl8;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.activity_random)
    LinearLayout activityRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.rank_back)
    public void onClick() {
        finish();
    }
}
