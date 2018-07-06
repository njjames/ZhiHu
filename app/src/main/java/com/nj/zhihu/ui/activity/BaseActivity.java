package com.nj.zhihu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nj.zhihu.R;

/**
 * Created by Administrator on 2018-07-06.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    private void initView() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_story_content);
    }

    protected abstract int getLayoutId();

}
