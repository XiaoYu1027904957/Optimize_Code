package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.utils.DensityUtil;

import org.xutils.image.ImageOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoShow extends AppCompatActivity {

    @InjectView(R.id.iv_photo)
    PhotoView ivPhoto;
    private String url;
    private ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_show);
        ButterKnife.inject(this);

        url = getIntent().getStringExtra("imageurl");
        final PhotoViewAttacher attacher = new PhotoViewAttacher(ivPhoto);

        //设置xUtils3的配置
        imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(80))
                //设置圆角
                .setRadius(DensityUtil.dip2px(this, 5))
                .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.news_pic_default)
                .setFailureDrawableId(R.drawable.news_pic_default)
                .build();


        Picasso.with(this).load(url).into(ivPhoto, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
//                 进行更新
                attacher.update();
            }

            @Override
            public void onError() {

            }
        });
    }
}
