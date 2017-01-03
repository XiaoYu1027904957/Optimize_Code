package com.xiaoyu.rewritebaisi.base;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by yuxiaobai on 2016/12/29.
 */

public class GetNet {
    public static void get(String s, Map<String, String> map, final OnGetListener onGetListener) {
        OkHttpUtils.get().url(s).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (onGetListener != null) {
                    onGetListener.onError(e.getMessage());
                }
            }

            @Override
            public void onResponse(String response, int id) {
                if (onGetListener != null) {
                    onGetListener.onSuccess(response);
                }
            }
        });
    }
}
