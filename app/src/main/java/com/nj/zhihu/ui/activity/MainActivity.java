package com.nj.zhihu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nj.zhihu.R;
import com.nj.zhihu.ui.fragment.DailyStoriesFragment;
import com.nj.zhihu.utils.SpUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityLOG";
    private Toolbar mToolbar;
    private DailyStoriesFragment mFragment;
    private String mCurrentTitle = "";
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        initFragment();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFragment() {
        mFragment = new DailyStoriesFragment();
        switchFragment(mFragment, "首页");
    }

    private void switchFragment(Fragment fragment, String title) {
        if (!mCurrentTitle.equals(title)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            mCurrentTitle = title;
            setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //就这一句代码，就可以设置上菜单
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                break;
            case R.id.action_night:
                //如果当前是夜间模式,则设置为非夜间模式
                if ((boolean) SpUtils.get(this, "night_mode", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SpUtils.put(this, "night_mode", false);
                } else { //否则设置为夜间模式
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SpUtils.put(this, "night_mode", true);
                }
                recreate();
                break;
            case R.id.action_setting:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
