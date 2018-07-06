package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.LatestStories;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesBiz {
    public Observable<LatestStories> getLatestStories() {
        return ApiManage.getInstance().getCommonApi().getLatestStories();
    }

    public Observable<BeforeStories> getBeforeStories(String date) {
        return ApiManage.getInstance().getCommonApi().getBeforeStorise(date);
    }
}
