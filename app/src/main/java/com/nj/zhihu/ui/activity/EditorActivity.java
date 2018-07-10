package com.nj.zhihu.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.Editors;
import com.nj.zhihu.ui.adapter.editor.EditorAdpter;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class EditorActivity extends AppCompatActivity {
    private List<Editors> mEditorsList = new ArrayList<>();
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        initData();
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("主编");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mRecyclerView = findViewById(R.id.editor_list);
//        mListView.setAdapter(new EditorAdpter(mEditorsList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommonAdapter<Editors>(this, R.layout.editor_list_item, mEditorsList) {
            @Override
            protected void convert(ViewHolder holder, Editors editors, int position) {
                Context context = holder.getConvertView().getContext();
                holder.setText(R.id.name, editors.getName());
                holder.setText(R.id.bio, editors.getBio());
                ImageView headImage = holder.getView(R.id.head);
                if ((boolean) SpUtils.get(context, "NO_IMAGE_MODE", false)
                        && !NetWorkUtils.isWifiConnected(context)) {
                    GlideApp.with(EditorActivity.this)
                            .load(R.drawable.editor_profile_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .into(headImage);
                } else {
                    GlideApp.with(EditorActivity.this)
                            .load(editors.getAvatar())
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .into(headImage);
                }
            }
        });
    }

    private void initData() {
        ArrayList<Editors> arrayList = getIntent().getParcelableArrayListExtra("editor_list");
        mEditorsList.addAll(arrayList);
    }
}
