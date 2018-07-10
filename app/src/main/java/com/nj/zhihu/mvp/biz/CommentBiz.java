package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentBiz {
    public Observable<StoryContentLongComment> getStoryContentLongComments(int id) {
        return ApiManage.getInstance().getCommonApi().getStoryContentLongComments(id);
    }

    public Observable<StoryContentShortComment> getStoryContentShortComments(int id) {
        return ApiManage.getInstance().getCommonApi().getStoryContentShortComments(id);
    }
}
