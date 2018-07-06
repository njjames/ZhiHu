package com.nj.zhihu.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nj.zhihu.R;
import com.nj.zhihu.ui.fragment.DailyStoriesFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityLOG";
    private Toolbar mToolbar;
    private DailyStoriesFragment mFragment;
    private String mCurrentTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        initFragment();
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
}
