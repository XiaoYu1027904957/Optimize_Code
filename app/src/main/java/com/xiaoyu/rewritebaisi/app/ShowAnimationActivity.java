package com.xiaoyu.rewritebaisi.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


import static com.xiaoyu.rewritebaisi.R.id.voice_edit;

public class ShowAnimationActivity extends AppCompatActivity {


    @InjectView(R.id.edit_titlebar)
    ImageView editTitlebar;
    @InjectView(R.id.video_edit)
    TextView videoEdit;
    @InjectView(R.id.picture_edit)
    TextView pictureEdit;
    @InjectView(R.id.text_edit)
    TextView textEdit;
    @InjectView(voice_edit)
    TextView voiceEdit;
    @InjectView(R.id.link_edit)
    TextView linkEdit;
    @InjectView(R.id.edit_show_cancel)
    Button editShowCancel;
    @InjectView(R.id.activity_show_animation)
    RelativeLayout activityShowAnimation;
    private AnimationSet set;
    private Intent intent;
    private TranslateAnimation translateAnimation;
    private TranslateAnimation translateAnimationVideo;
    private TranslateAnimation translateAnimationPic;
    private TranslateAnimation translateAnimationVoice;
    private TranslateAnimation translateAnimationLink;
    private TranslateAnimation translateAnimationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animation);
        ButterKnife.inject(this);

        initAninmation();
    }

    private void initAninmation() {

        translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.5f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation.setDuration(1800);
        translateAnimation.setInterpolator(new AnticipateOvershootInterpolator());

        translateAnimationVideo = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.6f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimationVideo.setDuration(1600);
        translateAnimationVideo.setInterpolator(new AnticipateOvershootInterpolator());

        translateAnimationPic = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.6f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimationPic.setDuration(1700);
        translateAnimationPic.setInterpolator(new AnticipateOvershootInterpolator());

        translateAnimationText = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.7f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimationText.setDuration(1650);
        translateAnimationText.setInterpolator(new AnticipateOvershootInterpolator());

        translateAnimationVoice = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.5f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimationVoice.setDuration(1500);
        translateAnimationVoice.setInterpolator(new AnticipateOvershootInterpolator());


        translateAnimationLink = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, -1.7f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimationLink.setDuration(1500);
        translateAnimationLink.setInterpolator(new AnticipateOvershootInterpolator());
        setStartAnimation();

    }

    /**
     * 设置动画
     */

    private void setStartAnimation() {
        editTitlebar.startAnimation(translateAnimation);
        videoEdit.startAnimation(translateAnimationVideo);
        pictureEdit.startAnimation(translateAnimationPic);
        textEdit.startAnimation(translateAnimationText);
        voiceEdit.startAnimation(translateAnimationVoice);
        linkEdit.startAnimation(translateAnimationLink);
    }


    @OnClick(R.id.edit_show_cancel)
    public void onClick() {

        translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 1.0f);
        translateAnimation.setDuration(1350);
        translateAnimation.setInterpolator(new AnticipateInterpolator());
        translateAnimation.setFillAfter(true);

        translateAnimationVideo = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.95f);
        translateAnimationVideo.setDuration(1200);
        translateAnimationVideo.setInterpolator(new AnticipateInterpolator());
        translateAnimationVideo.setFillAfter(true);

        translateAnimationPic = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.9f);
        translateAnimationPic.setDuration(1250);
        translateAnimationPic.setInterpolator(new AnticipateInterpolator());
        translateAnimationPic.setFillAfter(true);


        translateAnimationText = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 1.05f);
        translateAnimationText.setDuration(1200);
        translateAnimationText.setInterpolator(new AnticipateInterpolator());
        translateAnimationText.setFillAfter(true);

        translateAnimationVoice = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 0.95f);
        translateAnimationVoice.setDuration(1100);
        translateAnimationVoice.setInterpolator(new AnticipateInterpolator());
        translateAnimationVoice.setFillAfter(true);


        translateAnimationLink = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_PARENT, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_PARENT, 1.0f);
        translateAnimationLink.setDuration(1100);
        translateAnimationLink.setInterpolator(new AnticipateInterpolator());
        translateAnimationLink.setFillAfter(true);

        setEndAnimation();

        translateAnimationPic.setAnimationListener(new Animation.AnimationListener() {
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

    private void setEndAnimation() {
        editTitlebar.startAnimation(translateAnimation);
        videoEdit.startAnimation(translateAnimationVideo);
        pictureEdit.startAnimation(translateAnimationPic);
        textEdit.startAnimation(translateAnimationText);
        voiceEdit.startAnimation(translateAnimationVoice);
        linkEdit.startAnimation(translateAnimationLink);
    }


    @OnClick({R.id.video_edit, R.id.picture_edit, R.id.text_edit, R.id.voice_edit, R.id.link_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_edit:
                TextView tv = new TextView(this);
                tv.setText("请先下载不得姐视频录制插件");
                new AlertDialog.Builder(this)
                        .setTitle("上传视频")
                        .setView(tv)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.picture_edit:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
                break;
            case R.id.text_edit:
                Toast.makeText(ShowAnimationActivity.this, "编辑文本信息", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ShowEditActivity.class);
                startActivity(intent);
                break;
            case R.id.voice_edit:
                Toast.makeText(ShowAnimationActivity.this, "编辑声音信息", Toast.LENGTH_SHORT).show();
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.link_edit:
                Toast.makeText(ShowAnimationActivity.this, "编辑链接信息", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ShowEditActivity.class);
                startActivity(intent);
                break;
        }
    }
}
