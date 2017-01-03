package com.xiaoyu.rewritebaisi.follow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.LoginActivity;
import com.xiaoyu.rewritebaisi.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yuxiaobai on 2016/12/29.
 * 关注的fragment
 */

public class FollowFragment extends BaseFragment {
    LayoutInflater inflater;
    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.btn_fllow_1)
    Button btnFllow1;
    @InjectView(R.id.login_in)
    Button loginIn;
    private Intent intent;

    @Override
    protected View initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_follow, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick({R.id.btn_fllow_1, R.id.login_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fllow_1:
                intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.login_in:
                intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }
}
