package com.nj.zhihu.bean;

import java.util.List;

/**
 * Created by Administrator on 2018-07-10.
 */

public class StoryContentShortComment implements IBaseItem {
    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }
}
