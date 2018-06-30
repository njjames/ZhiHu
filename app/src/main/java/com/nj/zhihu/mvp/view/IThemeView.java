package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.Themes;

/**
 * Created by nj on 2018/6/30.
 */

public interface IThemeView extends IBaseView {
    /**
     * 请求主题类别数据时失败后，调用此方法
     */
    void onRequestErr(String msg);

    /**
     * 将P层获取的主题数据显示到UI中
     */
    void loadThemeList(Themes themes);
}
