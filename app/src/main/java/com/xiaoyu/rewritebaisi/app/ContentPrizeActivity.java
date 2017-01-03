package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.constantUtils.ContantUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ContentPrizeActivity extends AppCompatActivity {


    @InjectView(R.id.back_mine)
    TextView backMine;
    @InjectView(R.id.share_mine)
    TextView shareMine;
    @InjectView(R.id.webView_mine)
    WebView webViewMine;
    @InjectView(R.id.activity_content_prize)
    LinearLayout activityContentPrize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_prize);
        ButterKnife.inject(this);
        initWebView();
    }

    private void initWebView() {
        webViewMine.getSettings().setJavaScriptEnabled(true);
        webViewMine.loadUrl(ContantUtils.MINE_CONTRIBUTE);


    }

    @OnClick({R.id.back_mine, R.id.share_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_mine:
                finish();
                break;
            case R.id.share_mine:
                Toast.makeText(ContentPrizeActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
