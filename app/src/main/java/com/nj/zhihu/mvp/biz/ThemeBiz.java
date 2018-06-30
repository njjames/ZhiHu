package com.nj.zhihu.mvp.biz;

import com.nj.zhihu.api.ApiManage;
import com.nj.zhihu.bean.Themes;

import io.reactivex.Observable;

/**
 * Created by nj on 2018/6/30.
 */

public class ThemeBiz {
    public Observable<Themes> getThemesList() {
        return ApiManage.getInstance().getCommonApi().getThemesList();
    }
}
