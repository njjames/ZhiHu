package com.nj.zhihu.mvp.presenter;

import android.util.Log;

import com.nj.zhihu.bean.BeforeStories;
import com.nj.zhihu.bean.LatestStories;
import com.nj.zhihu.mvp.biz.StoriesBiz;
import com.nj.zhihu.mvp.view.IStoriesView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018-07-05.
 */

public class StoriesPresenter extends BasePresenter<IStoriesView> {

    private final StoriesBiz mStoriesBiz;

    public StoriesPresenter() {
        mStoriesBiz = new StoriesBiz();
    }

    public void getLatestStories() {
        if (!isViewAttaced()) {
            return;
        }
        mStoriesBiz.getLatestStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestStories>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(LatestStories latestStories) {
                        if (isViewAttaced()) {
                            getView().loadLatestStories(latestStories);
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

    public void getBeforeStories(String date) {
        if (!isViewAttaced()) {
            return;
        }
        mStoriesBiz.getBeforeStories(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeforeStories>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(BeforeStories beforeStories) {
                        if (isViewAttaced()) {
                            getView().loadBeforeStorise(beforeStories);
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
