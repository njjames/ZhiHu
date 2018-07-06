package com.nj.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.Themes;
import com.nj.zhihu.bean.ThemesOther;
import com.nj.zhihu.mvp.presenter.ThemePresenter;
import com.nj.zhihu.mvp.view.IThemeView;
import com.nj.zhihu.ui.adapter.drawer.DrawerHeader;
import com.nj.zhihu.ui.adapter.drawer.DrawerHome;
import com.nj.zhihu.ui.adapter.drawer.ThemesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nj on 2018/6/30.
 */

public class ChooseThemeFragment extends Fragment implements IThemeView {

    private List<IBaseItem> mList = new ArrayList<>();
    private RecyclerView mNavigation;
    private ThemePresenter mPresenter;
    private ThemesListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_theme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavigation = view.findViewById(R.id.rv_navigation);
        mPresenter = new ThemePresenter();
        mPresenter.attachView(this);
        mAdapter = new ThemesListAdapter(getContext(),mList);

        mAdapter.setOnItemClickListener(new ThemesListAdapter.OnItemClickListener() {
            @Override
            public void onDrawerHeaderClick(View view) {

            }

            @Override
            public void onFollowClick() {

            }

            @Override
            public void onItemViewClick(int position) {
                mAdapter.setSelection(position);
                //必须调用notifyDataSetChanged，否则即使select的position改变了，界面也不会改变
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mNavigation.setLayoutManager(new LinearLayoutManager(getContext()));
        mNavigation.setAdapter(mAdapter);
        mPresenter.getThemesList();
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        mPresenter.unSubscription();
        super.onDestroy();
    }

    @Override
    public void onRequestErr(String msg) {
        Toast.makeText(getContext(), msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadThemeList(Themes themes) {
        mList.add(new DrawerHeader());
        mList.add(new DrawerHome());
        mList.addAll(themes.getOthers());
        mAdapter.notifyDataSetChanged();
    }
}
