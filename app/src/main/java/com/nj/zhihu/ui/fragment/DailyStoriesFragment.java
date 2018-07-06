package com.nj.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.LatestStories;
import com.nj.zhihu.mvp.presenter.StoriesPresenter;
import com.nj.zhihu.mvp.view.IStoriesView;
import com.nj.zhihu.ui.adapter.home.StoriesHeader;
import com.nj.zhihu.ui.adapter.home.StoriesListAdapter;
import com.nj.zhihu.ui.adapter.home.StoriesSection;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-07-05.
 */

public class DailyStoriesFragment extends Fragment implements IStoriesView{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<IBaseItem> mItemList = new ArrayList<>();
    private StoriesListAdapter mAdapter;
    private StoriesPresenter mPresenter;
    private boolean mIsRefresh;
    private LoadMoreWrapper mLoadMoreWrapper;
    private String mDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_stories, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.rv_daily);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mAdapter = new StoriesListAdapter(getContext(), mItemList);
        mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter = new StoriesPresenter();
        mPresenter.attachView(this);
        mPresenter.getLatestStories();
    }

    private void initView() {
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mDate != null) {
                    mPresenter.getBeforeStories(mDate);
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mPresenter.getLatestStories();
            }
        });
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        mPresenter.unSubscription();
        super.onDestroy();
    }

    @Override
    public void onRequestErr(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadLatestStories(LatestStories latestStories) {
        if (mIsRefresh) {
            mItemList.clear();
            mLoadMoreWrapper.notifyDataSetChanged();
        }
        //获取最新新闻的日期，用来作为获取上次新闻的参数
        mDate = latestStories.getDate();
        mItemList.add(new StoriesHeader(latestStories.getTop_stories()));
        mItemList.add(new StoriesSection(mDate));
        mItemList.addAll(latestStories.getStories());
        mLoadMoreWrapper.notifyDataSetChanged();
        mIsRefresh = false;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadBeforeStorise(BeforeStories beforeStories) {
        mDate = beforeStories.getDate();
        mItemList.add(new StoriesSection(mDate));
        mItemList.addAll(beforeStories.getStories());
        mLoadMoreWrapper.notifyDataSetChanged();
    }
}
