package com.xiaoyu.rewritebaisi.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.mine.bean.PagerOneBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2016/12/30.
 */

public class PagerOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<PagerOneBean.SquareListBean> datas;
    LayoutInflater inflater;


    public PagerOneAdapter(Context mContext, List<PagerOneBean.SquareListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycle_onpager, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.pagerone_image)
        ImageView pageroneImage;
        @InjectView(R.id.pagerone_name)
        TextView pageroneName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.getPosition(getAdapterPosition());
                    }
                }
            });
        }

        public void setData(int position) {
            Glide.with(mContext).load(datas.get(position).getIcon()).into(pageroneImage);
            pageroneName.setText(datas.get(position).getName());

        }
    }


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void getPosition(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
