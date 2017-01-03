package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.login_cancel)
    TextView loginCancel;
    @InjectView(R.id.show_image)
    ImageView showImage;
    @InjectView(R.id.wx_login)
    Button wxLogin;
    @InjectView(R.id.qq_login)
    Button qqLogin;
    @InjectView(R.id.login_other)
    TextView loginOther;
    @InjectView(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.login_cancel, R.id.wx_login, R.id.qq_login, R.id.login_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_cancel:
                finish();
                break;
            case R.id.wx_login:
                Toast.makeText(LoginActivity.this, "请转到微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.qq_login:
                Toast.makeText(LoginActivity.this, "情转到QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_other:
                Toast.makeText(LoginActivity.this, "使用其他方式登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
