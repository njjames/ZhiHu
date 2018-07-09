package com.nj.zhihu.mvp.presenter;

import com.nj.zhihu.bean.ThemesContent;
import com.nj.zhihu.mvp.biz.OtherBiz;
import com.nj.zhihu.mvp.view.IOtherView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018-07-09.
 */

public class OtherPresenter extends BasePresenter<IOtherView> {
    private OtherBiz mBiz;

    public OtherPresenter() {
        this.mBiz = new OtherBiz();
    }

    public void getThemeContent(int id) {
        if (!isViewAttaced()) {
            return;
        }
        mBiz.getThemeContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThemesContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(ThemesContent themesContent) {
                        if (isViewAttaced()) {
                            getView().loadOtherContent(themesContent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestErr("数据加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
