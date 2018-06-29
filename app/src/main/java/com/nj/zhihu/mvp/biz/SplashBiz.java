package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.LaunchImage;

import io.reactivex.Observable;

/**
 * 获取接口实例，并根据不同的业务需要，调用接口中不同的方法
 * 也就是根据不同的业务逻辑，来配置请求参数
 * 返回结果用于在P层中真正的发送请求获取数据（用Rxjava的方式）
 * Created by Administrator on 2018-06-29.
 */

public class SplashBiz {
    public Observable<LaunchImage> getLaunchImage() {
        return ApiManage.getInstance().getCommonApi().getLaunchImage();
    }
}
