package com.nj.zhihu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.mvp.view.ISplashView;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    private ImageView mLaunchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mLaunchImage = findViewById(R.id.iv_launch_image);

    }

    @Override
    public void getLanuchImage(LaunchImage launchImage) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestErr() {

    }
}
