package com.nj.zhihu.ui.adapter.comment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nj.zhihu.GlideApp;
import com.nj.zhihu.R;
import com.nj.zhihu.bean.CommentsBean;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.utils.DateUtils;
import com.nj.zhihu.utils.NetWorkUtils;
import com.nj.zhihu.utils.SpUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentItemDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.comment_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof CommentsBean;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        Context context = holder.getConvertView().getContext();
        CommentsBean commentsBean = (CommentsBean) iBaseItem;
        holder.setText(R.id.content, commentsBean.content);
        holder.setText(R.id.time, DateUtils.timeToStr(commentsBean.time));
        holder.setText(R.id.like_count, String.valueOf(commentsBean.likes));
        ImageView imageView = holder.getView(R.id.head);
        if ((boolean) SpUtils.get(context, "NO_IMAGE_MODE", false)
                && !NetWorkUtils.isWifiConnected(context)) {
            GlideApp.with(context)
                    .load(R.drawable.editor_profile_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        } else {
            GlideApp.with(context)
                    .load(((CommentsBean) iBaseItem).avatar)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        }
    }
}
