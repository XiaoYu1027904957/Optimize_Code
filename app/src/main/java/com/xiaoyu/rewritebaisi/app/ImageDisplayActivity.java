package com.xiaoyu.rewritebaisi.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.xiaoyu.rewritebaisi.R;
import com.xiaoyu.rewritebaisi.app.fragment.ImageDisplayLargeFragment;
import com.xiaoyu.rewritebaisi.app.fragment.ImageDisplayRegionFragment;
import com.xiaoyu.rewritebaisi.app.fragment.ImageDisplayRotateFragment;

import java.util.Arrays;
import java.util.List;

public class ImageDisplayActivity extends AppCompatActivity {
    private static final String BUNDLE_POSITION = "position";

    private int position;

    private List<Page> pages;
    private ImageDisplayLargeFragment imageDisplayLargeFragment;
    private ImageDisplayRotateFragment imageDisplayRotateFragment;
    private ImageDisplayRegionFragment imageDisplayRegionFragment;
    private String imageurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Intent intent = getIntent();
        imageurl = intent.getStringExtra("imageurl");

        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_POSITION)) {
            position = savedInstanceState.getInt(BUNDLE_POSITION);
        }
        pages = Arrays.asList(
                new Page(imageurl, ImageDisplayLargeFragment.class),
                new Page(imageurl, ImageDisplayRotateFragment.class),
                new Page(imageurl, ImageDisplayRegionFragment.class)
        );

        updatePage();
        initFragment();
    }

    private void initFragment() {

        imageDisplayLargeFragment = new ImageDisplayLargeFragment(imageurl);
        imageDisplayRegionFragment = new ImageDisplayRegionFragment(imageurl);
        imageDisplayRotateFragment = new ImageDisplayRotateFragment(imageurl);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_POSITION, position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void next() {
        position++;
        updatePage();
    }

    public void previous() {
        position--;
        updatePage();
    }

    private void updatePage() {
        if (position > pages.size() - 1) {
            return;
        }

        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, (Fragment) pages.get(position).clazz.newInstance())
                    .commit();
        } catch (Exception e) {
            Log.e("something", "Failed to load fragment", e);
            Toast.makeText(this, "Whoops, couldn't load the fragment!", Toast.LENGTH_SHORT).show();
        }
    }

    private static final class Page {
        private final String subtitle;
        private final Class<?> clazz;

        private Page(String subtitle, Class<?> clazz) {
            this.subtitle = subtitle;
            this.clazz = clazz;
        }
    }

}
