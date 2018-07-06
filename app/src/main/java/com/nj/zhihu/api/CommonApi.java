package com.nj.zhihu.api;

import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.LatestStories;
import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.bean.Themes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 用于描述网络请求的接口
 * Created by Administrator on 2018-06-29.
 */

public interface CommonApi {

    /**
     * 获取加载页面图片
     * @return 返回的结果用于Rxjava
     */
    @GET("/api/7/prefetch-launch-images/1080*1920")
    Observable<LaunchImage> getLaunchImage();

    /**
     * 获取主题的数据
     * @return
     */
    @GET("/api/4/themes")
    Observable<Themes> getThemesList();

    /**
     * 获取最新新闻的数据
     * @return
     */
    @GET("/api/4/news/latest")
    Observable<LatestStories> getLatestStories();

    @GET("/api/4/news/before/{date}")
    Observable<BeforeStories> getBeforeStorise(@Path("date") String date);

}
