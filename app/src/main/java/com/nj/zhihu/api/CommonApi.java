package com.nj.zhihu.api;

import com.nj.zhihu.bean.LaunchImage;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 用于描述网络请求的接口
 * Created by Administrator on 2018-06-29.
 */

public interface CommonApi {

    /**
     * 获取加载页面图片的方法
     * @return 返回的结果用于Rxjava
     */
    @GET("/api/7/prefetch-launch-images/1080*1920")
    Observable<LaunchImage> getLaunchImage();

}
