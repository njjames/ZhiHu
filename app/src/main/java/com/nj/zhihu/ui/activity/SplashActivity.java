package com.nj.zhihu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.mvp.presenter.SplashPresenter;
import com.nj.zhihu.mvp.view.ISplashView;
import com.nj.zhihu.utils.SpUtils;

public class SplashActivity extends AppCompatActivity implements ISplashView {

    private ImageView mLaunchImage;
    private String mLauchImageUrl;
    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //去除ActionBar，上面都糊上了
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        mLaunchImage = findViewById(R.id.iv_launch_image);

        //获取P层的引用，设置view，并更新UI
        mPresenter = new SplashPresenter();
        mPresenter.attachView(this);
        mPresenter.getLaunchImageFromBiY();
    }

    @Override
    protected void onDestroy() {
        //销毁时，解除view和订阅
        mPresenter.detachView();
        mPresenter.unSubscription();
        super.onDestroy();
    }

    /**
     * 从返回的数据中获取图片的url，并显示图片，最后保存到sp中
     * @param launchImage 从P层返回的数据
     */
    @Override
    public void getLanuchImage(LaunchImage launchImage) {
        if (launchImage.getCreatives().size() > 0) {
            mLauchImageUrl = launchImage.getCreatives().get(0).getUrl();
            //4.0之后使用GlideApp，才能使用diskCacheStrategy方法
            GlideApp.with(this)
                    .load(mLauchImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(mLaunchImage);
            //        //4.0之前使用Glide就可以使用diskCacheStrategy方法
            //        Glide.with(this)
            //                .load(mLauchImageUrl)
            //                .into(mLaunchImage);
            SpUtils.put(this, "launch_image", mLauchImageUrl);
        }
    }

    @Override
    public void getLanuchImageFromBiY(final String imageurl) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GlideApp.with(SplashActivity.this)
                        .load(imageurl)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(mLaunchImage);
                SpUtils.put(SplashActivity.this, "launch_image", imageurl);
            }
        });
    }

    @Override
    public void onRequestEnd() {

    }

    /**
     * 请求失败时，就从sp中读取上次保存的url，显示出来
     */
    @Override
    public void onRequestErr() {
        mLauchImageUrl = (String) SpUtils.get(this, "launch_iamge", "");
        GlideApp.with(this)
                .load(mLauchImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mLaunchImage);
    }
}
