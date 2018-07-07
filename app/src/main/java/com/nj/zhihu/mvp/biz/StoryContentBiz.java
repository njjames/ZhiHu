package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.StoryContent;
import com.nj.zhihu.bean.StoryContentExtra;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018-07-07.
 */

public class StoryContentBiz {
    public Observable<StoryContent> getgetStoryContent(int id) {
        return ApiManage.getInstance().getCommonApi().getStoryContent(id);
    }

    public Observable<StoryContentExtra> getStoryContentExtra(int id) {
        return ApiManage.getInstance().getCommonApi().getStoryContentExtra(id);
    }
}
