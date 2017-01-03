package com.xiaoyu.rewritebaisi.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.xiaoyu.rewritebaisi.MainActivity;
import com.xiaoyu.rewritebaisi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.timr_change)
    TextView timrChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        initData();
    }

    /**
     * 改变text显示的时间
     */
    private void initData() {
        handler.sendEmptyMessageDelayed(0, 1000);
        handler.sendEmptyMessageDelayed(1, 2000);
        handler.sendEmptyMessageDelayed(2, 3000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    timrChange.setText("2");
                    break;
                case 1:
                    timrChange.setText("1");
                    break;
                case 2:
                    timrChange.setText("0");
                    break;
            }

            if (timrChange.getText().equals("0")) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.removeCallbacksAndMessages(null);
                break;
            case MotionEvent.ACTION_UP:
                handler.removeCallbacksAndMessages(null);
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                break;
        }
        return true;
    }
}
