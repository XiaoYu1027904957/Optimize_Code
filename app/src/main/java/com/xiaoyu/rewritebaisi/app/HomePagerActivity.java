package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.view.ImgRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomePagerActivity extends AppCompatActivity {


    @InjectView(R.id.xListView)
    ImgRecyclerView personalListView;
    @InjectView(R.id.activity_list_view)
    LinearLayout activityListView;
    private MyListViewAdapter adapter;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.inject(this);
        initlist();
        initView();

    }

    private void initlist() {
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("我是item" + i);
        }
    }

    /**
     * 添加头
     */
    private void initView() {
//        View view = View.inflate(this, R.layout.listview_head_image, null);
//        ImageView image = (ImageView) view.findViewById(R.id.image);
//        personalListView.addHeaderView(view);
        adapter = new MyListViewAdapter();
        personalListView.setAdapter(adapter);
        personalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomePagerActivity.this, "position-------------------->" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    class MyListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size()-1;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(HomePagerActivity.this, R.layout.text_listview_item, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(list.get(position) + "");
            Log.e("TAG", "--------->" + list.get(position));

            return convertView;
        }
    }

    class ViewHolder {
        TextView textView;
    }

}
