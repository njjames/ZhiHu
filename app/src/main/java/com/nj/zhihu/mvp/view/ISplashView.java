package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.LaunchImage;

/**
 * Splash界面的view接口
 * 用来更新Splash界面的UI
 * Created by Administrator on 2018-06-28.
 */

public interface ISplashView extends IBaseView {
    /**
     * 根据获取到的数据，设置图片
     * @param launchImage 从P层返回的数据
     */
    void getLanuchImage(LaunchImage launchImage);

    /**
     * 请求结束之后，调用此方法
     */
    void onRequestEnd();

    /**
     * 请求图片数据时失败后，调用此方法
     */
    void onRequestErr();
}
