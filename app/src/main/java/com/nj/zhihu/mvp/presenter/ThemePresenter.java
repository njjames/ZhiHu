package com.nj.zhihu.mvp.presenter;

import com.nj.zhihu.bean.Themes;
import com.nj.zhihu.mvp.biz.ThemeBiz;
import com.nj.zhihu.mvp.view.IThemeView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nj on 2018/6/30.
 */

public class ThemePresenter extends BasePresenter<IThemeView> {
    private ThemeBiz mThemeBiz;

    public ThemePresenter() {
        mThemeBiz = new ThemeBiz();
    }

    /**
     * 获取主题信息
     */
    public void getThemesList() {
        if (!isViewAttaced()) {
            return;
        }
        mThemeBiz.getThemesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Themes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(Themes themes) {
                        if (isViewAttaced()) {
                            getView().loadThemeList(themes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestErr("主题数据加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
