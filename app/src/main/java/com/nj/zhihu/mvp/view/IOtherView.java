package com.nj.zhihu.mvp.view;

import com.nj.zhihu.bean.BeforeThemeStories;
import com.nj.zhihu.bean.ThemesContent;

/**
 * Created by Administrator on 2018-07-09.
 */

public interface IOtherView extends IBaseView {
    void onRequestErr(String msg);

    void loadOtherContent(ThemesContent themesContent);

    void loadBeforeOtherContent(BeforeThemeStories beforeThemeStories);
}
