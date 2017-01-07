package com.xiaoyu.rewritebaisi.follow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.follow.bean.RecyclerViewBean;
import com.xiaoyu.rewritebaisi.utils.GlideCircleTransform;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2017/1/6.
 */

public class NewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<RecyclerViewBean.ListBean> datas;



    public NewRecyclerAdapter(Context mContext, List<RecyclerViewBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.follow_recycler_show, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.follow_icon)
        ImageView followIcon;
        @InjectView(R.id.follow_name)
        TextView followName;
        @InjectView(R.id.follow)
        Button follow;
        @InjectView(R.id.follow_content)
        TextView followContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(int position) {
            Glide.with(mContext).load(datas.get(position).getHeader()).transform(new GlideCircleTransform(mContext)).into(followIcon);
            followName.setText(datas.get(position).getScreen_name());
            followContent.setText(datas.get(position).getFans_count() + "人关注");
        }
    }
}
