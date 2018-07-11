package com.nj.zhihu.ui.adapter.comment;

import android.view.View;
import android.widget.ImageView;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentShortHeaderDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.commemt_short_header_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof CommentShortHeader;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        CommentShortHeader commentShortHeader = (CommentShortHeader) iBaseItem;
        holder.setText(R.id.comment_short_title, commentShortHeader.getCount() + "个短评论");
        ImageView emptyImg = holder.getView(R.id.comment_empty);
        if (commentShortHeader.getCount() == 0) {
            emptyImg.setVisibility(View.VISIBLE);
        }else {
            emptyImg.setVisibility(View.GONE);
        }
    }
}
