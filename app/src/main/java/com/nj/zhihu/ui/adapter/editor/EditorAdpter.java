package com.nj.zhihu.ui.adapter.editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.App;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.Editors;
import com.nj.zhihu.ui.activity.EditorActivity;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;

import java.util.List;

/**
 * Created by Administrator on 2018-07-10.
 */

public class EditorAdpter extends BaseAdapter {
    private List<Editors> mEditorsList;

    public EditorAdpter(List<Editors> editorsList) {
        mEditorsList = editorsList;
    }

    @Override
    public int getCount() {
        return mEditorsList.size();
    }

    @Override
    public Editors getItem(int position) {
        return mEditorsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = View.inflate(App.getContext(), R.layout.editor_list_item, parent);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_list_item, null);
        Editors editors = mEditorsList.get(position);
        ImageView headImage = view.findViewById(R.id.head);
        TextView name = view.findViewById(R.id.name);
        TextView bio = view.findViewById(R.id.bio);
        name.setText(mEditorsList.get(position).getName());
        bio.setText(mEditorsList.get(position).getBio());
        if ((boolean) SpUtils.get(App.getContext(), "NO_IMAGE_MODE", false)
                && !NetWorkUtils.isWifiConnected(App.getContext())) {
            GlideApp.with(App.getContext())
                    .load(R.drawable.editor_profile_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(headImage);
        } else {
            GlideApp.with(App.getContext())
                    .load(mEditorsList.get(position).getAvatar())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(headImage);
        }
        return view;
    }
}
