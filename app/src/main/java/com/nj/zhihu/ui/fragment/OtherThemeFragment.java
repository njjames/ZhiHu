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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.BeforeThemeStories;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.ThemesContent;
import com.nj.zhihu.mvp.presenter.OtherPresenter;
import com.nj.zhihu.mvp.view.IOtherView;
import com.nj.zhihu.ui.adapter.other.OtherHeader;
import com.nj.zhihu.ui.adapter.other.OtherListAdapter;
import com.nj.zhihu.ui.adapter.other.OtherSection;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherThemeFragment extends Fragment implements IOtherView {

    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    List<IBaseItem> mList = new ArrayList<>();
    private OtherListAdapter mAdapter;
    private static final String LIST_ID = "list_id";
    private OtherPresenter mPresenter;
    private int mId;

    public OtherThemeFragment() {
    }

    public static OtherThemeFragment newInstance(int id) {
        OtherThemeFragment fragment = new OtherThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LIST_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(LIST_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_stories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeLayout = view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.rv_other);
        mAdapter = new OtherListAdapter(getContext(), mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new OtherPresenter();
        mPresenter.attachView(this);
        mPresenter.getThemeContent(mId);
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
    public void loadOtherContent(ThemesContent themesContent) {
        mList.clear();
        mList.add(new OtherHeader(themesContent.getImage()));
        mList.add(new OtherSection(themesContent.getEditors()));
        mList.addAll(themesContent.getStories());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadBeforeOtherContent(BeforeThemeStories beforeThemeStories) {

    }
}
