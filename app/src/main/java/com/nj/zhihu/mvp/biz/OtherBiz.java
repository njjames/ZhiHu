package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.BeforeThemeStories;
import com.nj.zhihu.bean.ThemesContent;
import com.nj.zhihu.bean.ThemesContentItem;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherBiz {
    public Observable<ThemesContent> getThemeContent(int id) {
        return ApiManage.getInstance().getCommonApi().getThemeContent(id);
    }

    public Observable<BeforeThemeStories> getBeforeThemesContent(int id, int story_id) {
        return ApiManage.getInstance().getCommonApi().getBeforeThemesContent(id, story_id);
    }
}
