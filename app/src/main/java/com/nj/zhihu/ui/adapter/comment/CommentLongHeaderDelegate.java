package com.nj.zhihu.ui.adapter.comment;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentLongHeaderDelegate implements ItemViewDelegate<IBaseItem> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.commemt_long_header_item;
    }

    @Override
    public boolean isForViewType(IBaseItem item, int position) {
        return item instanceof CommentLongHeader;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {
        CommentLongHeader commentLongHeader = (CommentLongHeader) iBaseItem;
        holder.setText(R.id.comment_long_title, commentLongHeader.getCount() + "个评论");
    }
}
