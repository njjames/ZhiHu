package com.nj.zhihu.ui.adapter.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.ThemesOther;

import java.util.List;

/**
 * Created by nj on 2018/6/30.
 */

public class ThemesListAdapter extends RecyclerView.Adapter<ThemesListAdapter.ViewHolder> {
    private List<ThemesOther> mList;

    public ThemesListAdapter(List<ThemesOther> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ThemesOther themesOther = mList.get(position);
        holder.mTextView.setText(themesOther.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_theme_title);
            mImageView = itemView.findViewById(R.id.iv_add_follow);
        }
    }
}
