package com.xiaoyu.rewritebaisi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShowEditActivity extends AppCompatActivity {

    @InjectView(R.id.cancel_edit)
    TextView cancelEdit;
    @InjectView(R.id.share_mine)
    TextView shareMine;
    @InjectView(R.id.edit_text)
    EditText editText;
    @InjectView(R.id.activity_show_edit)
    RelativeLayout activityShowEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edit);
        ButterKnife.inject(this);
    }
}
