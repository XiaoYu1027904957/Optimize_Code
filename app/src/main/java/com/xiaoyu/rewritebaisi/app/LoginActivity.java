package com.xiaoyu.rewritebaisi.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiaoyu.rewritebaisi.R;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity {

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
    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mShareAPI = UMShareAPI.get(this);


    }

    @OnClick({R.id.login_cancel, R.id.wx_login, R.id.qq_login, R.id.login_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_cancel:
                finish();
                break;
            case R.id.wx_login:
                Toast.makeText(LoginActivity.this, "请转到微信登录", Toast.LENGTH_SHORT).show();
                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE, qumAuthListener);
                break;
            case R.id.qq_login:
                Log.e("TAG", "------------------------------------->" + mShareAPI.toString());
                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.login_other:
                Toast.makeText(LoginActivity.this, "使用其他方式登录", Toast.LENGTH_SHORT).show();

                showPopupWindow();

                break;
        }
    }

    private Button login_cancel;
    private Button login_phone;
    private Button login_sina;
    private Button login_tencen;

    private void showPopupWindow() {
//        1.使用layoutInflate獲得view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_show_login, null);
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
        login_cancel = (Button) view.findViewById(R.id.login_canceller);
        login_phone = (Button) view.findViewById(R.id.login_phone);
        login_sina = (Button) view.findViewById(R.id.login_sina);
        login_tencen = (Button) view.findViewById(R.id.login_tencen);


//         实例化布局文件

//        显示设置
        window.showAtLocation(LoginActivity.this.findViewById(R.id.login_cancel), Gravity.BOTTOM, 0, 0);

//         设置点击取消
        login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "手机登录", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        login_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "新浪微博", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        login_tencen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "腾讯微博", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });


    }


    //为qq设置监听--第三方登录


    /**
     * auth callback interface
     **/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, this);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * auth callback interface
     **/
    private UMAuthListener qumAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN_CIRCLE, this);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Log.e("TAG", "11111" + t.getMessage());
            Toast.makeText(getApplicationContext(), "Authorize fail" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


}
