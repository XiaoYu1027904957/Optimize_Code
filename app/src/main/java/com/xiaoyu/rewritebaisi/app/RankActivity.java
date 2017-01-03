package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoyu.rewritebaisi.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.rank_back)
    public void onClick() {
        finish();
    }
}
