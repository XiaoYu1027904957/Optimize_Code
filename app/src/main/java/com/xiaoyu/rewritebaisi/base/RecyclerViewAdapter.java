package com.xiaoyu.rewritebaisi.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.PhotoShow;
import com.xiaoyu.rewritebaisi.detials.DetialActivity;
import com.xiaoyu.rewritebaisi.common.CommonBean;
import com.xiaoyu.rewritebaisi.detials.DetialGifActivity;
import com.xiaoyu.rewritebaisi.detials.DetialHtmlActivity;
import com.xiaoyu.rewritebaisi.detials.DetialImageActivity;
import com.xiaoyu.rewritebaisi.detials.DetialTextActivity;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;
import com.xiaoyu.rewritebaisi.utils.Utils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by yuxiaobai on 2017/1/3.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<CommonBean.ListBean> datas;
    LayoutInflater inflater;
    private Utils utils;
    private View view;

    private int currentType;

    public static final int VIDEO = 0;

    public static final int IMAGE = 1;

    public static final int HTML = 2;

    public static final int GIF = 3;

    public static final int TEXT = 4;

//    public static final int EMPTY = 5;


    public RecyclerViewAdapter(Context mContext, List<CommonBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
        inflater = LayoutInflater.from(mContext);
        utils = new Utils();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIDEO) {
            view = inflater.inflate(R.layout.item_recycler_item, null);
            return new VideoViewHolder(view);
        } else if (viewType == IMAGE) {
            view = inflater.inflate(R.layout.item_recycler_image, null);
            return new ImageViewHolder(view);
        } else if (viewType == HTML) {
            view = inflater.inflate(R.layout.item_recycler_html, null);
            return new HtmlViewHolder(view);
        } else if (viewType == GIF) {
            view = inflater.inflate(R.layout.item_recycler_gift, null);
            return new GifViewHolder(view);
        } else if (viewType == TEXT) {
            view = inflater.inflate(R.layout.item_recycler_textview, null);
            return new TextViewHolder(view);
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
        @InjectView(R.id.button_share)
        Button button_share;


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

            button_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layout_show.setVisibility(View.GONE);
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
                                Intent intent = new Intent(mContext, DetialActivity.class);
                                intent.putExtra("position", listBean.getId());
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
                                Toast.makeText(mContext, "点击评论内容", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialImageActivity.class);
                                intent.putExtra("position", listBean.getId());
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
            final String s = listBean.getImage().getThumbnail_small().get(0);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PhotoShow.class);
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
//            Glide.with(mContext).load(listBean.getHtml().getThumbnail_small().get(0)).into(image);

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
                                Intent intent = new Intent(mContext, DetialHtmlActivity.class);
                                intent.putExtra("position", listBean.getId());
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
                                Intent intent = new Intent(mContext, DetialGifActivity.class);
                                intent.putExtra("position", listBean.getId());
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
        @InjectView(R.id.text_show)
        TextView textShow;
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
                                Toast.makeText(mContext, "点击评论内容", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mContext, DetialTextActivity.class);
                                intent.putExtra("position", listBean.getId());
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


        }
    }
}
