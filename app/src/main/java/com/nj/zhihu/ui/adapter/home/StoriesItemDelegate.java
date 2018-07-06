package com.nj.zhihu.ui.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.Stories;
import com.nj.zhihu.ui.activity.StoryContentActivity;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesItemDelegate implements ItemViewDelegate<IBaseItem> {

    private Context mContext;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.story_list_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof Stories;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        //绑定UI
        final Stories stories = (Stories) iBaseItem;
        holder.setText(R.id.tv_story_title, stories.getTitle());
        mContext = holder.getConvertView().getContext();
        GlideApp.with(mContext)
                .load(stories.getImages().get(0))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into((ImageView) holder.getView(R.id.iv_image));
        if (!stories.isMultiPic()) {
            holder.getView(R.id.iv_mutiPic).setVisibility(View.INVISIBLE);
        }
        //绑定事件
        holder.setOnClickListener(R.id.cardview, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoryContentActivity.class);
                intent.putExtra("id", stories.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
