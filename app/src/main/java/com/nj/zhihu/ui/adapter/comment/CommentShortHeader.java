package com.nj.zhihu.ui.adapter.comment;

import com.nj.zhihu.bean.IBaseItem;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentShortHeader implements IBaseItem {
    private int count;

    public CommentShortHeader(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
