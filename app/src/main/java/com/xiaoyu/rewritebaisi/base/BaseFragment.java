package com.xiaoyu.rewritebaisi.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public abstract class BaseFragment extends Fragment {
    public Context mContext;

    public BaseFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    /**
     * 让子布局加载视图
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 当Acticity被创建时回调
     *
     * @param savedInstanceState
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 让需要填充数据的孩子重写该方法
     */
    public void initData() {
    }


}
