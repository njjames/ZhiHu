package com.nj.zhihu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.StoryContent;
import com.nj.zhihu.bean.StoryContentExtra;
import com.nj.zhihu.mvp.presenter.StoryContentPresenter;
import com.nj.zhihu.mvp.view.IStoryContentView;
import com.nj.zhihu.utils.CalculateUtil;
import com.nj.zhihu.utils.SpUtils;
import com.nj.zhihu.utils.WebUtil;

import java.util.List;

/**
 * Created by Administrator on 2018-07-06.
 */

public abstract class BaseActivity extends AppCompatActivity implements IStoryContentView,View.OnClickListener {

    private Toolbar mToolbar;
    private WebView mWebView;
    private WebSettings mWebSettings;
    private int mId;
    private StoryContentPresenter mPresenter;
    private ImageView mCommentImg;
    private TextView mCommentText;
    private ImageView mLikeImg;
    private TextView mLikeText;
    private int mLikeNum;
    private int mCommentMum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    private void initView() {
        initHeadView();
        initToolbar();
        initWebView();
        initData();
    }

    /**
     * 初始化Header的view，有子类去实现
     */
    protected abstract void initHeadView();


    //初始化数据
    private void initData() {
        mId = getIntent().getIntExtra("id", 0);
        mPresenter = new StoryContentPresenter();
        mPresenter.attachView(this);
        if (mId != 0) {
            mPresenter.getStoryContent(mId);
            mPresenter.getStoryContentExtra(mId);
        }
    }

    //初始化WebView
    private void initWebView() {
        mWebView = findViewById(R.id.webview);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebChromeClient(new WebChromeClient());
    }

    //初始化Toolbar，处理Toolbar的UI和事件
    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        //设置导航键
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.inflateMenu(R.menu.menu_story_content);
        Menu menu = mToolbar.getMenu();
        //给菜单项设置新的view
        menu.findItem(R.id.menu_comment).setActionView(R.layout.action_item);
        menu.findItem(R.id.menu_like).setActionView(R.layout.action_item);
        //获取到设置的view，并获取view中的每个view
        View actionCommentView = menu.findItem(R.id.menu_comment).getActionView();
        mCommentImg = actionCommentView.findViewById(R.id.action_item_image);
        mCommentText = actionCommentView.findViewById(R.id.actio_item_text);

        View actionLikeView = menu.findItem(R.id.menu_like).getActionView();
        mLikeImg = actionLikeView.findViewById(R.id.action_item_image);
        mLikeText = actionLikeView.findViewById(R.id.actio_item_text);
        //给评论和点赞添加点击事件
        actionCommentView.setOnClickListener(this);
        actionLikeView.setOnClickListener(this);
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        mPresenter.unSubscription();
        super.onDestroy();
    }

    @Override
    public void onRequestError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadStoryContent(StoryContent storyContent) {
        loadHeaderImg(storyContent);
        String body = storyContent.getBody();
        List<String> css = storyContent.getCss();
        boolean nightMode = (boolean) SpUtils.get(this, "night_mode", false);
        String data = WebUtil.buildHtmlWithCss(body, css, nightMode);
        mWebView.loadData(data, WebUtil.MIME_TYPE, WebUtil.ENCODING);
    }

    /**
     * 具体的头部信息，让子类去实现
     * @param storyContent
     */
    protected abstract void loadHeaderImg(StoryContent storyContent);

    @Override
    public void loadStoryContentExtra(StoryContentExtra storyContentExtra) {
        mCommentMum = storyContentExtra.getComments();
        mLikeNum = storyContentExtra.getPopularity();
        int longCommentNum = storyContentExtra.getLong_comments();
        int shortCommentNum = storyContentExtra.getShort_comments();
        //设置评论的图标和数字
        mCommentImg.setImageResource(R.drawable.ic_comment);
        mCommentText.setText(CalculateUtil.CalculatePraise(mCommentMum));
        //判断这个问题是否点赞过，如果点赞过则显示点赞过的图标
        if (SpUtils.contains(this, Integer.toString(mId))
                && (boolean) SpUtils.get(this, Integer.toString(mId), false)) {
            mLikeImg.setImageResource(R.drawable.ic_thumb_up_orange);
            mLikeText.setText(CalculateUtil.CalculatePraise(++mLikeNum));
        }else {
            mLikeImg.setImageResource(R.drawable.ic_thumb_up);
            mLikeText.setText(CalculateUtil.CalculatePraise(mLikeNum));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_comment:
                Intent intent = new Intent(BaseActivity.this, CommentActivity.class);
                intent.putExtra("id", mId);
                startActivity(intent);
                break;
            case R.id.menu_like:
                //这里并没有同步回去，这是在获取到的基础上，点赞=1,去掉点赞就-1
                //如果点过赞就取消点赞
                if ((boolean) SpUtils.get(this, Integer.toString(mId), false)) {
                    mLikeImg.setImageResource(R.drawable.ic_thumb_up);
                    mLikeText.setText(CalculateUtil.CalculatePraise(--mLikeNum));
                    SpUtils.put(this, Integer.toString(mId), false);
                } else {
                    mLikeImg.setImageResource(R.drawable.ic_thumb_up_orange);
                    mLikeText.setText(CalculateUtil.CalculatePraise(++mLikeNum));
                    SpUtils.put(this, Integer.toString(mId), true);
                }
                break;
        }
    }
}
