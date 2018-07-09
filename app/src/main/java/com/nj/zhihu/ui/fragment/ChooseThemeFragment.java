package com.nj.zhihu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.nj.zhihu.ui.activity.LoginActivity;
import com.nj.zhihu.ui.activity.MainActivity;
import com.nj.zhihu.ui.adapter.drawer.DrawerHeader;
import com.nj.zhihu.ui.adapter.drawer.DrawerHome;
import com.nj.zhihu.ui.adapter.drawer.ThemesListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nj on 2018/6/30.
 */

public class ChooseThemeFragment extends Fragment implements IThemeView {
    private static final String TAG = "ChooseThemeFragmentLOG";

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
                switch (view.getId()) {
                    case R.id.download:
                        Toast.makeText(getContext(), "正在进行离线下载...",Toast.LENGTH_SHORT).show();
                        break;
                    default://默认跳转到登录界面
//                        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                        ((MainActivity)getActivity()).goToLogin();
                        break;
                }
            }

            @Override
            public void onFollowClick() {
                Toast.makeText(getContext(), "关注成功，关注内容将在首页呈现", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemViewClick(int position) {//这里好像只能点击首页
                mAdapter.setSelection(position);
                //必须调用notifyDataSetChanged，否则即使select的position改变了，界面也不会改变
                mAdapter.notifyDataSetChanged();
                //点击了之后，要关闭菜单页面，并把首页重新显示出来
                MainActivity activity = (MainActivity) getActivity();
                if (position == 1) {
                    activity.switchFragment(new DailyStoriesFragment(), "首页");
                } else {
                    //获取点击的id和标题,根据id创建Fragmenrt，根据title跳转到Fragment
                    int id = ((ThemesOther) mAdapter.getDatas().get(position)).getId();
                    String title = ((ThemesOther) mAdapter.getDatas().get(position)).getName();
                    Log.d(TAG, id + ": " + title);
                    OtherThemeFragment otherThemeFragment = OtherThemeFragment.newInstance(id);
                    activity.switchFragment(otherThemeFragment, title);
                }

                activity.onBackPressed();
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
