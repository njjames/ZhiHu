package com.nj.zhihu.api;

import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.bean.Themes;

import io.reactivex.Observable;
import retrofit2.http.GET;

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

}
