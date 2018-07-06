package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.LatestStories;
import com.nj.zhihu.bean.Themes;

/**
 * Created by Administrator on 2018-07-05.
 */

public interface IStoriesView extends IBaseView{
    /**
     * 请求数据时失败后，调用此方法
     */
    void onRequestErr(String msg);

    /**
     * 将P层获取的数据显示到UI中
     */
    void loadLatestStories(LatestStories latestStories);

    /**
     * 将P层获取的更多数据显示到UI中
     * @param beforeStories
     */
    void loadBeforeStorise(BeforeStories beforeStories);
}
