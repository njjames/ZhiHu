package com.nj.zhihu.ui.adapter.other;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.ThemesContentItem;
import com.nj.zhihu.ui.activity.StoryContentActivity;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherItemDelegte implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.other_list_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof ThemesContentItem;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        final ThemesContentItem themesContentItem = (ThemesContentItem) iBaseItem;
        final Context context = holder.getConvertView().getContext();
        holder.setText(R.id.tv_story_title, themesContentItem.getTitle());
        ImageView imageView = holder.getView(R.id.iv_image);
        holder.getView(R.id.iv_mutiPic).setVisibility(View.GONE);
        if (themesContentItem.getImages() != null && themesContentItem.getImages().size() > 0) {
            if ((boolean) SpUtils.get(context, "NO_IMAGE_MODE", false)
                    && !NetWorkUtils.isWifiConnected(context)) {
                GlideApp.with(context)
                        .load(R.drawable.image_small_default)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imageView);
            } else {
                GlideApp.with(context)
                        .load(themesContentItem.getImages().get(0))
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .into(imageView);
            }
        } else {
            imageView.setVisibility(View.GONE);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryContentActivity.class);
                intent.putExtra("id", themesContentItem.getId());
                context.startActivity(intent);
            }
        });
    }
}
