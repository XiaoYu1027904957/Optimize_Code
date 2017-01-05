package com.xiaoyu.rewritebaisi.essence.adapter;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.essence.bean.DetialsBean;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static com.xiaoyu.rewritebaisi.essence.adapter.RecyclerDetialTextAdapter.HOT;

/**
 * Created by yuxiaobai on 2017/1/4.
 */

public class RecyclerDetialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 类型
     */
    public static final int HEAD = 0;
    public static final int OTHER = 1;
    private final Context mContext;
    private final List<DetialsBean.NormalBean.ListBeanX> datas;
    private final String url;
    private final String image;
    private final String name;
    private final String time;
    private final String text;
    private final String imageurl;
    private final String type;
    private final List<DetialsBean.HotBean.ListBean> hotlist;


    private LayoutInflater inflater;
    private int currentType = 0;

    public RecyclerDetialAdapter(Context mContext, List<DetialsBean.NormalBean.ListBeanX> datas, String url, String image, String name, String time, String text, String imageurl, String type,List<DetialsBean.HotBean.ListBean> hotlist) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
        this.url = url;
        this.image = image;
        this.name = name;
        this.time = time;
        this.text = text;
        this.imageurl =imageurl;
        this.type = type;
        this.hotlist = hotlist;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = inflater.inflate(R.layout.detial_head, parent, false);
            return new HeadViewHolder(view);
        } else if (viewType == OTHER) {
            View view = inflater.inflate(R.layout.list_coment_text, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == HOT) {
            View view = inflater.inflate(R.layout.list_coment_hot, parent, false);
            return new MyHotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == HEAD) {
            HeadViewHolder viewHolder = (HeadViewHolder) holder;
            viewHolder.setData();
        } else if (getItemViewType(position) == OTHER) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.setData();
        } else if (getItemViewType(position) == HOT) {
           MyHotViewHolder viewHolder = (MyHotViewHolder) holder;
            viewHolder.setData();
        }
    }

    @Override
    public int getItemCount() {

        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEAD) {
            currentType = HEAD;
        } else if(position==OTHER) {
            currentType = OTHER;
        }else if(position==HOT) {
            currentType = HOT;
        }
        return currentType;
    }


    class HeadViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title_bar)
        TextView titleBar;
        @InjectView(R.id.recycler_time)
        TextView recyclerTime;
        @InjectView(R.id.icon_mine)
        ImageView iconMine;
        @InjectView(R.id.rl_item)
        RelativeLayout rlItem;
        @InjectView(R.id.videocontroller1)
        JCVideoPlayer videocontroller1;
        @InjectView(R.id.video_duration)
        TextView videoDuration;
        @InjectView(R.id.rl_1)
        RelativeLayout rl1;
        @InjectView(R.id.text_zan)
        TextView textZan;
        @InjectView(R.id.text_sun)
        TextView textSun;
        @InjectView(R.id.text_zhuan)
        TextView textZhuan;
        @InjectView(R.id.text_news)
        TextView textNews;
        @InjectView(R.id.text_pinglun)
        LinearLayout textPinglun;
        @InjectView(R.id.text)
        TextView textView;

        public HeadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData() {

            videocontroller1.setUp(url, image, "厉害了我的姐");
            JCVideoPlayer.releaseAllVideos();
            Glide.with(mContext).load(imageurl).transform(new GlideCircleTransform(mContext)).into(iconMine);
            titleBar.setText(name);
            recyclerTime.setText(time);
            textView.setText(text);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.title_show)
        TextView titleShow;
        @InjectView(R.id.comment_content_show)
        LinearLayout commentContentShow;

        private TextView userLevel;
        private TextView sex;
        private TextView commentName;
        private TextView commentContent;
        private LinearLayout Reply;
        private ImageView commentIcon;
        private RelativeLayout item_anyone;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

        public void setData() {

            commentContentShow.removeAllViews();
            for (int j = 0; j < datas.size(); j++) {

                View view = View.inflate(mContext, R.layout.list_comment_text_item, null);
                userLevel = (TextView) view.findViewById(R.id.user_level);
                sex = (TextView) view.findViewById(R.id.sex);
                commentName = (TextView) view.findViewById(R.id.comment_name);
                commentContent = (TextView) view.findViewById(R.id.comment_content);
                Reply = (LinearLayout) view.findViewById(R.id.Reply);
                commentIcon = (ImageView) view.findViewById(R.id.comment_icon);
                item_anyone = (RelativeLayout) view.findViewById(R.id.item_anyone);

                DetialsBean.NormalBean.ListBeanX listBeanX = datas.get(j);


                Glide.with(mContext).load(listBeanX.getUser().getProfile_image()).transform(new GlideCircleTransform(mContext)).into(commentIcon);
                userLevel.setText(listBeanX.getUser().getTotal_cmt_like_count());
                sex.setText(listBeanX.getUser().getSex());
                commentName.setText(listBeanX.getUser().getUsername());
                commentContent.setText(listBeanX.getContent());

                if (listBeanX.getPrecmts().size() > 0) {
                    //设置评论区


                    //设置评论区可见
                    Reply.setVisibility(View.VISIBLE);
                    Reply.removeAllViews();
                    List<?> precmts = listBeanX.getPrecmts();
                    for (int i = 0; i < precmts.size(); i++) {
                        TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                        final DetialsBean.NormalBean.ListBeanX listBeanX1 = (DetialsBean.NormalBean.ListBeanX) precmts.get(i);
                        if (!TextUtils.isEmpty(listBeanX1.getContent()) && !TextUtils.isEmpty(listBeanX1.getUser().getUsername())) {
                            //获取评论的用户名和评论内容
                            String comment = listBeanX1.getUser().getUsername() + " :  " + listBeanX1.getContent();
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
                                    Toast.makeText(mContext, "--->" + listBeanX1.getUser().getUsername() + "", Toast.LENGTH_SHORT).show();

                                }
                            }, 0, listBeanX1.getUser().getUsername().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


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


                                }
                            }, listBeanX1.getUser().getUsername().length(), comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                            //设置textview 显示出来
                            textview.setText(builder);
                            //设置textview 的setMovementMethod 不设置 不可点击
                            textview.setMovementMethod(LinkMovementMethod.getInstance());
                            //添加得到commentArea
                            Reply.addView(textview);
                        }
                    }


                } else {
                    //设置评论区不可见
                    Reply.setVisibility(View.GONE);
                }


                commentContentShow.addView(item_anyone);
            }

        }
    }



    public class MyHotViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.title_show)
        TextView titleShow;
        @InjectView(R.id.comment_content_show)
        LinearLayout commentContentShow;

        private TextView userLevel;
        private TextView sex;
        private TextView commentName;
        private TextView commentContent;
        private LinearLayout Reply;
        private ImageView commentIcon;
        private RelativeLayout item_anyone;

        public MyHotViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, itemView);
        }

        public void setData() {
            commentContentShow.removeAllViews();
            for (int j = 0; j < hotlist.size(); j++) {
                Log.e("TAG", "------------------------------------->"+j);
                View view = View.inflate(mContext, R.layout.list_comment_text_item, null);
                userLevel = (TextView) view.findViewById(R.id.user_level);
                sex = (TextView) view.findViewById(R.id.sex);
                commentName = (TextView) view.findViewById(R.id.comment_name);
                commentContent = (TextView) view.findViewById(R.id.comment_content);
                Reply = (LinearLayout) view.findViewById(R.id.Reply);
                commentIcon = (ImageView) view.findViewById(R.id.comment_icon);
                item_anyone = (RelativeLayout) view.findViewById(R.id.item_anyone);

                DetialsBean.HotBean.ListBean listBean = hotlist.get(j);


                Glide.with(mContext).load(listBean.getUser().getProfile_image()).transform(new GlideCircleTransform(mContext)).into(commentIcon);
                userLevel.setText(listBean.getUser().getTotal_cmt_like_count());
                sex.setText(listBean.getUser().getSex());
                commentName.setText(listBean.getUser().getUsername());
                commentContent.setText(listBean.getContent());

                if (listBean.getPrecmts().size() > 0) {
                    //设置评论区


                    //设置评论区可见
                    Reply.setVisibility(View.VISIBLE);
                    Reply.removeAllViews();
                    List<?> precmts = listBean.getPrecmts();
                    for (int i = 0; i < precmts.size(); i++) {
                        TextView textview = new TextView(mContext);
//                    TextView tv_comment_list = (TextView) UIUtils.getView(R.layout.tv_comment_list);
                        final DetialsBean.NormalBean.ListBeanX listBeanX1 = (DetialsBean.NormalBean.ListBeanX) precmts.get(i);
                        if (!TextUtils.isEmpty(listBeanX1.getContent()) && !TextUtils.isEmpty(listBeanX1.getUser().getUsername())) {
                            //获取评论的用户名和评论内容
                            String comment = listBeanX1.getUser().getUsername() + " :  " + listBeanX1.getContent();
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
                                    Toast.makeText(mContext, "--->" + listBeanX1.getUser().getUsername() + "", Toast.LENGTH_SHORT).show();

                                }
                            }, 0, listBeanX1.getUser().getUsername().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


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


                                }
                            }, listBeanX1.getUser().getUsername().length(), comment.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);


                            //设置textview 显示出来
                            textview.setText(builder);
                            //设置textview 的setMovementMethod 不设置 不可点击
                            textview.setMovementMethod(LinkMovementMethod.getInstance());
                            //添加得到commentArea
                            Reply.addView(textview);
                        }
                    }


                } else {
                    //设置评论区不可见
                    Reply.setVisibility(View.GONE);
                }


                commentContentShow.addView(item_anyone);
            }
        }
    }
}
