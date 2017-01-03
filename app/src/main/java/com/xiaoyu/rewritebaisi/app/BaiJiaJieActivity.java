package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BaiJiaJieActivity extends AppCompatActivity {

    @InjectView(R.id.back_mine)
    TextView backMine;
    @InjectView(R.id.share_mine)
    TextView shareMine;
    @InjectView(R.id.webView_mine)
    WebView webViewMine;
    @InjectView(R.id.activity_bai_jia_jie)
    LinearLayout activityBaiJiaJie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_jia_jie);
        ButterKnife.inject(this);
        initWebView();
    }

    private void initWebView() {
        webViewMine.getSettings().setJavaScriptEnabled(true);
//        webViewMine.loadUrl();

    }

    @OnClick({R.id.back_mine, R.id.share_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_mine:
                finish();
                break;
            case R.id.share_mine:
                Toast.makeText(BaiJiaJieActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
