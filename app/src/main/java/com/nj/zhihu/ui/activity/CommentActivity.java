package com.nj.zhihu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;
import com.nj.zhihu.mvp.presenter.CommentPresenter;
import com.nj.zhihu.mvp.view.ICommectView;
import com.nj.zhihu.ui.adapter.comment.CommentListAdapter;
import com.nj.zhihu.ui.adapter.comment.CommentLongHeader;
import com.nj.zhihu.ui.adapter.comment.CommentShortHeader;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommectView {
    private List<IBaseItem> mItemList = new ArrayList<>();

    private CommentPresenter mPresenter;
    private int mId;
    private CommentListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        initData();

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.comment_list);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("评论");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        mId = getIntent().getIntExtra("id", 0);
        if (mId > 0) {
            mAdapter = new CommentListAdapter(this, mItemList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
            mPresenter = new CommentPresenter();
            mPresenter.attachView(this);
            mPresenter.getLongComments(mId);
            mPresenter.getShortComments(mId);
        }
    }

    @Override
    public void onRequestErr(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadLongComments(StoryContentLongComment storyContentLongComment) {
        mItemList.add(new CommentLongHeader(storyContentLongComment.getComments().size()));
        mItemList.addAll(storyContentLongComment.getComments());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadShortComments(StoryContentShortComment storyContentShortComment) {
        mItemList.add(new CommentShortHeader(storyContentShortComment.getComments().size()));
        mItemList.addAll(storyContentShortComment.getComments());
        mAdapter.notifyDataSetChanged();
    }
}
