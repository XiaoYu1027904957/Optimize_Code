package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ShowAnimationActivity extends AppCompatActivity {


    @InjectView(R.id.edit_titlebar)
    ImageView editTitlebar;
    @InjectView(R.id.video_edit)
    TextView videoEdit;
    @InjectView(R.id.picture_edit)
    TextView pictureEdit;
    @InjectView(R.id.text_edit)
    TextView textEdit;
    @InjectView(R.id.linearLayout_Animation)
    LinearLayout linearLayoutAnimation;
    @InjectView(R.id.link_edit)
    TextView linkEdit;
    @InjectView(R.id.voice_edit)
    TextView voiceEdit;
    @InjectView(R.id.bottom_animation_edit)
    LinearLayout bottomAnimationEdit;
    @InjectView(R.id.edit_show_cancel)
    Button editShowCancel;
    @InjectView(R.id.activity_show_animation)
    RelativeLayout activityShowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animation);
        ButterKnife.inject(this);

        initAninmation();
    }

    private void initAninmation() {
        AnimationSet set = new AnimationSet(false);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.5f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);

        translateAnimation.setDuration(2000);
        set.addAnimation(translateAnimation);
        set.setFillAfter(true);
        translateAnimation.setInterpolator(new AnticipateOvershootInterpolator());
        set.setDuration(2000);
        set.setRepeatMode(Animation.REVERSE);
        editTitlebar.setAnimation(translateAnimation);
        editTitlebar.startAnimation(translateAnimation);
        ShowAnimationActivity.this.linearLayoutAnimation.startAnimation(set);
        ShowAnimationActivity.this.bottomAnimationEdit.startAnimation(set);
    }

    @OnClick(R.id.edit_show_cancel)
    public void onClick() {
        AnimationSet set = new AnimationSet(false);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 1.0f);

        translateAnimation.setDuration(1000);
        set.addAnimation(translateAnimation);
        set.setFillAfter(true);
        translateAnimation.setInterpolator(new AnticipateInterpolator());
        set.setDuration(1000);
        set.setRepeatMode(Animation.REVERSE);
        editTitlebar.setAnimation(translateAnimation);
        editTitlebar.startAnimation(translateAnimation);
        ShowAnimationActivity.this.linearLayoutAnimation.startAnimation(set);
        ShowAnimationActivity.this.bottomAnimationEdit.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                ShowAnimationActivity.this.overridePendingTransition(0, R.anim.activity_close);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


}
