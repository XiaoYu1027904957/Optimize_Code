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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuxiaobai on 2016/12/30.
 */

public class PagerTwnPager extends RecyclerView.Adapter {
    private final Context mContext;

    private List<PagerOneBean.SquareListBean> datas;
    private List<PagerOneBean.SquareListBean> listone = null;
    private List<PagerOneBean.SquareListBean> listtwn = null;
    private LayoutInflater inflater;

    public PagerTwnPager(Context mContext, List<PagerOneBean.SquareListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
        SeparateDatas();
    }

    private void SeparateDatas() {

        listone = new ArrayList<>();
        listtwn = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (i <= 9) {
                PagerOneBean.SquareListBean squareListBean = datas.get(i);
                listone.add(squareListBean);
            } else {
                PagerOneBean.SquareListBean squareListBean = datas.get(i);
                listtwn.add(squareListBean);
            }
        }
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
        return listtwn.size();
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
            Glide.with(mContext).load(listtwn.get(position).getIcon()).into(pageroneImage);
            pageroneName.setText(listtwn.get(position).getName());
        }
    }

    private PagerOneAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void getPosition(int position);
    }

    public void setOnItemClickListener(PagerOneAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
