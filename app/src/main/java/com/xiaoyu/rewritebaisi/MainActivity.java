package com.xiaoyu.rewritebaisi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.xiaoyu.rewritebaisi.app.ShowAnimationActivity;
import com.xiaoyu.rewritebaisi.essence.EssenceFragment;
import com.xiaoyu.rewritebaisi.follow.FollowFragment;
import com.xiaoyu.rewritebaisi.mine.MineFragment;
import com.xiaoyu.rewritebaisi.newpost.NewPostFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @InjectView(R.id.main_fragment)
    FrameLayout mainFragment;

    @InjectView(R.id.main_rg)
    RadioGroup mainRg;
    //     创建一个集合装载fragment
    private List<Fragment> list;
    private int position;
    //    记录之前选择的item
    private Fragment temfragment;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext =MainActivity.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initData();
        setOnCheckedListener();
    }


    /**
     * 加载fragment
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new EssenceFragment());
        list.add(new NewPostFragment());
        list.add(new FollowFragment());
        list.add(new MineFragment());

//         设置默认显示
        defultFragent(list.get(position));

    }

    /**
     * 设置默认选中
     *
     * @param fragment
     */

    private void defultFragent(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment, fragment);
        transaction.commit();
        temfragment = fragment;
    }

    /**
     * 设置RadioGroup的点击事件
     */

    private void setOnCheckedListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_1:
                        position = 0;
                        break;
                    case R.id.main_rb_2:
                        position = 1;
                        break;
                    case R.id.main_rb_3:
                        Intent intent = new Intent(MainActivity.this,ShowAnimationActivity.class);
                        intent.setClassName(mContext,"com.xiaoyu.rewritebaisi.app.ShowAnimationActivity");
                         mContext.startActivity(intent);
                        MainActivity.this.overridePendingTransition(R.anim.activity_open,0);
                        break;
                    case R.id.main_rb_4:
                        position = 2;
                        break;
                    case R.id.main_rb_5:
                        position = 3;
                        break;
                }
                Fragment currentFtagment = getFragment(position);
//                 切换fragment
                switchFragment(currentFtagment);

            }
        });
//         默认选中第一项
        mainRg.check(R.id.main_rb_1);

    }

    private void switchFragment(Fragment currentFtagment) {
//         当前显示的fragment
//         currentfragmrnt 将要显示的item
        if (temfragment != currentFtagment) {
//             判断当前的fragment有没有
            if (currentFtagment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!currentFtagment.isAdded()) {
                    if (temfragment != null) {
                        transaction.hide(temfragment);
                    }
                    transaction.add(R.id.main_fragment, currentFtagment);

                } else {
                    if (temfragment != null) {
                        transaction.hide(temfragment);
                    }
                    transaction.show(currentFtagment);
                }
                transaction.commit();
//                然后重新给fragment赋值
                temfragment = currentFtagment;
            }


        }
    }

    /**
     * 选择的是哪个position的fragmrnt
     *
     * @param position
     */

    private Fragment getFragment(int position) {
        if (list.size() > 0 && list != null) {
            return list.get(position);
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getDataFromNet() {


    }
}
