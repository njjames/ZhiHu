package com.nj.zhihu.ui.adapter.comment;

import com.nj.zhihu.R;
import com.nj.zhihu.bean.IBaseItem;
import com.nj.zhihu.bean.StoryContentLongComment;
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
        return item instanceof StoryContentLongComment;
    }

    @Override
    public void convert(ViewHolder holder, IBaseItem iBaseItem, int position) {

    }
}
