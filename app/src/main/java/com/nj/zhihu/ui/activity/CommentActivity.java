package com.nj.zhihu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;
import com.nj.zhihu.mvp.presenter.CommentPresenter;
import com.nj.zhihu.mvp.view.ICommectView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommectView {
    private List<IBaseItem> mItemList = new ArrayList<>();

    private CommentPresenter mPresenter;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mPresenter = new CommentPresenter();
        mPresenter.attachView(this);
        mPresenter.getLongComments(mId);
        mPresenter.getShortComments(mId);
    }

    @Override
    public void onRequestErr(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadLongComments(StoryContentLongComment storyContentLongComment) {

    }

    @Override
    public void loadSgortComments(StoryContentShortComment storyContentShortComment) {

    }
}
