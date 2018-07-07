package com.nj.zhihu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.StoryContent;

public class StoryContentActivity extends BaseActivity {

    private ImageView mImage;
    private TextView mTitle;
    private TextView mImageSource;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_story_content;
    }

    @Override
    protected void initHeadView() {
        mImage = findViewById(R.id.image);
        mTitle = findViewById(R.id.title);
        mImageSource = findViewById(R.id.image_source);
    }

    @Override
    protected void loadHeaderImg(StoryContent storyContent) {
        mTitle.setText(storyContent.getTitle());
        mImageSource.setText(storyContent.getImage_source());
        GlideApp.with(this)
                .load(storyContent.getImage())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mImage);
    }
}
