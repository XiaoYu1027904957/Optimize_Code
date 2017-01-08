package com.xiaoyu.rewritebaisi.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.ListViewActivity;
import com.xiaoyu.rewritebaisi.app.PhotoShow;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.detials.DetialImageActivity;
import com.xiaoyu.rewritebaisi.detials.DetialShowActivity;
import com.xiaoyu.rewritebaisi.detials.DetialTextActivity;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;
import com.xiaoyu.rewritebaisi.utils.Utils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by yuxiaobai on 2017/1/3.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<CommonBean.ListBean> datas;
    private final CommonBean commonBean;
    LayoutInflater inflater;
    private Utils utils;
    private View view;

    private int currentType;

    public static final int VIDEO = 0;

    public static final int IMAGE = 1;

    public static final int HTML = 2;

    public static final int GIF = 3;

    public static final int TEXT = 4;

    public static final int AUDIO = 5;


    public RecyclerViewAdapter(Context mContext, List<CommonBean.ListBean> list, CommonBean commonBean) {
        this.mContext = mContext;
        this.datas = list;
        inflater = LayoutInflater.from(mContext);
        utils = new Utils();
        this.commonBean = commonBean;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIDEO) {
            view = inflater.inflate(R.layout.item_recycler_item, parent, false);
            return new VideoViewHolder(view);
        } else if (viewType == IMAGE) {
            view = inflater.inflate(R.layout.item_recycler_image, parent, false);
            return new ImageViewHolder(view);
        } else if (viewType == HTML) {
            view = inflater.inflate(R.layout.item_recycler_html, parent, false);
            return new HtmlViewHolder(view);
        } else if (viewType == GIF) {
            view = inflater.inflate(R.layout.item_recycler_gift, parent, false);
            return new GifViewHolder(view);
        } else if (viewType == TEXT) {
            view = inflater.inflate(R.layout.item_recycler_textview, parent, false);
            return new TextViewHolder(view);
        } else if (viewType == AUDIO) {
            view = inflater.inflate(R.layout.item_recycler_item, parent, false);
            return new AudioViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int currentType = getItemViewType(position);
        if (currentType == VIDEO) {
            VideoViewHolder viewHolder = (VideoViewHolder) holder;
            viewHolder.setData(datas.get(position));
        } else if (currentType == HTML) {
            HtmlViewHolder viewHolder = (HtmlViewHolder) holder;
            viewHolder.setData(datas.get(position));
        } else if (currentType == TEXT) {
            TextViewHolder viewHolder = (TextViewHolder) holder;
            viewHolder.setData(datas.get(position));
        } else if (currentType == GIF) {
            GifViewHolder viewHolder = (GifViewHolder) holder;
            viewHolder.setData(datas.get(position));
        } else if (currentType == IMAGE) {
            ImageViewHolder viewHolder = (ImageViewHolder) holder;
            viewHolder.setData(datas.get(position));
        } else if (currentType == AUDIO) {
            AudioViewHolder viewHolder = (AudioViewHolder) holder;
            viewHolder.setData(datas.get(position));
        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ("video".equals(datas.get(position).getType())) {
            Log.e("TAG", "视频---》");
            currentType = VIDEO;
        } else if ("image".equals(datas.get(position).getType())) {
            Log.e("TAG", "图片---》");
            currentType = IMAGE;
        } else if ("html".equals(datas.get(position).getType())) {
            Log.e("TAG", "html---》");
            currentType = HTML;
        } else if ("text".equals(datas.get(position).getType())) {
            Log.e("TAG", "文本---》");
            currentType = TEXT;
        } else if ("gif".equals(datas.get(position).getType())) {
            Log.e("TAG", "gif---》");
            currentType = GIF;
        } else if ("audio".equals(datas.get(position).getType())) {
            currentType = AUDIO;
        }


        return currentType;
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.videocontroller1)
        JCVideoPlayer videocontroller1;
        @InjectView(R.id.video_duration)
        TextView tvduration;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;
        @InjectView(R.id.show_info)
        LinearLayout layout_show;


        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");
            if (listBean.getVideo() != null) {
                tvduration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000));
                String data = listBean.getVideo().getVideo().get(0);
                String s = listBean.getVideo().getThumbnail().get(0);
                videocontroller1.setUp(data, s, "厉害了我的姐");
                JCVideoPlayer.releaseAllVideos();
            }

            textZhuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout_show.setVisibility(View.VISIBLE);
                }
            });

            //设置评论区

            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Toast.makeText(mContext, "被点击了好开心", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialShowActivity.class);
                                intent.putExtra("url", listBean.getVideo().getVideo().get(1));
                                intent.putExtra("image", listBean.getVideo().getThumbnail().get(0));//图片路径
                                intent.putExtra("position", listBean.getId());//网址值
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("imageurl", listBean.getU().getHeader().get(0));
                                intent.putExtra("type", listBean.getType());
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object", listBean);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }

            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });

            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });


        }


    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.recycler_time)
        TextView recyclerTime;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");

            Glide.with(mContext).load(listBean.getImage().getThumbnail_small().get(0)).into(image);

            //设置评论区

            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Toast.makeText(mContext, "点击评论内容" + listBean.getImage().getThumbnail_small().get(0), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialImageActivity.class);
                                intent.putExtra("imageurl", listBean.getImage().getThumbnail_small().get(0));
                                intent.putExtra("position", listBean.getId());
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("image", listBean.getU().getHeader().get(0));
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object", listBean);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }
            final String s = listBean.getImage().getBig().get(0);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotoShow.class);
                    intent.putExtra("imageurl", listBean.getImage().getBig().get(0));
                    mContext.startActivity(intent);
                }
            });
            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });

            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class HtmlViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.recycler_time)
        TextView recyclerTime;
        @InjectView(R.id.rl_item)
        RelativeLayout rlItem;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;


        public HtmlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");

            Log.e("TAG", "---><-----" + listBean.getHtml().getThumbnail().get(0));
            Glide.with(mContext).load(listBean.getHtml().getThumbnail().get(1)).into(image);


            //设置评论区

            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Toast.makeText(mContext, "点击评论内容", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialImageActivity.class);
                                intent.putExtra("position", listBean.getId());
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("image", listBean.getU().getHeader().get(0));
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object", listBean);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }
            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });
            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class GifViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.recycler_time)
        TextView recyclerTime;
        @InjectView(R.id.rl_item)
        RelativeLayout rlItem;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;


        public GifViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");
            //设置评论区
            Glide.with(mContext).load(listBean.getGif().getGif_thumbnail().get(0)).into(image);
            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Toast.makeText(mContext, "点击评论内容", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialImageActivity.class);
                                intent.putExtra("position", listBean.getId());
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("image", listBean.getU().getHeader().get(0));
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object", listBean);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }
            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });
            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.recycler_time)
        TextView recyclerTime;
        @InjectView(R.id.rl_item)
        RelativeLayout rlItem;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");
            //设置评论区

            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Intent intent = new Intent(mContext, DetialTextActivity.class);
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("position", listBean.getId());
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                intent.putExtra("image", listBean.getU().getHeader().get(0));
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("object", listBean);
                                intent.putExtras(bundle);
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }

            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });
            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }


    class AudioViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.icon_imageView)
        ImageView iconImageView;
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.text_intriduce)
        TextView textIntriduce;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.recycler_time)
        TextView texttime;
        @InjectView(R.id.videocontroller1)
        JCVideoPlayer videocontroller1;
        @InjectView(R.id.video_duration)
        TextView tvduration;
        @InjectView(R.id.layout_commont)
        LinearLayout layout;
        @InjectView(R.id.show_info)
        LinearLayout layout_show;
//        @InjectView(R.id.button_share)
//        Button button_share;

        public AudioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(final CommonBean.ListBean listBean) {
            titleBar.setText(listBean.getU().getName());
            texttime.setText(listBean.getPasstime());
            Glide.with(mContext).load(listBean.getU().getHeader().get(0)).transform(new GlideCircleTransform(mContext)).into(iconImageView);
            textIntriduce.setText(listBean.getText());
            textZan.setText(listBean.getUp() + "");
            textSun.setText(listBean.getDown() + "");
            textNews.setText(listBean.getForward() + "");
            textZhuan.setText(listBean.getStatus() + "");
            tvduration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000));
            String data = listBean.getVideo().getVideo().get(1);
            String s = listBean.getVideo().getThumbnail().get(0);
            videocontroller1.setUp(data, s, "厉害了我的姐");
            JCVideoPlayer.releaseAllVideos();

            textZhuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout_show.setVisibility(View.VISIBLE);
                }
            });

            //设置评论区

            if (listBean.getTop_comments() != null && listBean.getTop_comments() != null && listBean.getTop_comments().size() > 0) {
                //设置评论区可见
                layout.setVisibility(View.VISIBLE);
                layout.removeAllViews();
                List<CommonBean.ListBean.TopCommentsBean> top_comments = listBean.getTop_comments();
                for (int i = 0; i < top_comments.size(); i++) {
                    TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                    final CommonBean.ListBean.TopCommentsBean topCommentsBean = top_comments.get(i);
                    if (!TextUtils.isEmpty(topCommentsBean.getContent()) && !TextUtils.isEmpty(topCommentsBean.getU().getName())) {
                        //获取评论的用户名和评论内容
                        String comment = topCommentsBean.getU().getName() + " :  " + topCommentsBean.getContent();
                        //构造SpannableStringBuilder
                        SpannableStringBuilder builder = new SpannableStringBuilder(comment);
                        //构造改变字体颜色的span
//                        ForegroundColorSpan span = new ForegroundColorSpan(UIUtils.getContext().getResources().getColor(R.color.blue));
                        //将span用于 评论区用户名的颜色
//                        builder.setSpan(span,0,topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        //设置点击用户名，部分text文本内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLUE);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //进项业务逻辑处理
                                Toast.makeText(mContext, "--->" + topCommentsBean.getU().getName() + "", Toast.LENGTH_SHORT).show();

                            }
                        }, 0, topCommentsBean.getU().getName().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //评论内容可点击
                        builder.setSpan(new ClickableSpan() {

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                //设置指定文字的颜色
                                ds.setColor(Color.BLACK);
                                //设置指定文字是否需要下划线
                                ds.setUnderlineText(false);
                            }

                            @Override
                            public void onClick(View view) {
                                //业务逻辑处理
                                Toast.makeText(mContext, "点击评论内容", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialShowActivity.class);
                                intent.putExtra("position", listBean.getId());
                                intent.putExtra("name", listBean.getU().getName());
                                intent.putExtra("time", listBean.getPasstime());
                                intent.putExtra("text", listBean.getText());
                                mContext.startActivity(intent);

                            }
                        }, topCommentsBean.getU().getName().length() + 1, comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                        //设置textview 显示出来
                        textview.setText(builder);
                        //设置textview 的setMovementMethod 不设置 不可点击
                        textview.setMovementMethod(LinkMovementMethod.getInstance());

                        //添加得到commentArea
                        layout.addView(textview);
                    }
                }


            } else {
                //设置评论区不可见
                layout.setVisibility(View.GONE);
            }
            textNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    showShare();
                }
            });
            titleBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ListViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", listBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }
            });
        }
    }


    private void showShare() {
        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(mContext);
    }

}
