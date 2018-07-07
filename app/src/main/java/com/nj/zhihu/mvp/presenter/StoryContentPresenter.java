package com.nj.zhihu.mvp.presenter;

import com.nj.zhihu.bean.StoryContent;
import com.nj.zhihu.bean.StoryContentExtra;
import com.nj.zhihu.mvp.biz.StoryContentBiz;
import com.nj.zhihu.mvp.view.IStoryContentView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018-07-07.
 */

public class StoryContentPresenter extends BasePresenter<IStoryContentView> {
    private final StoryContentBiz mStoryContentBiz;

    public StoryContentPresenter() {
        mStoryContentBiz = new StoryContentBiz();
    }

    public void getStoryContent(int id) {
        if (!isViewAttaced()) {
            return;
        }
        mStoryContentBiz.getgetStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(StoryContent storyContent) {
                        if (isViewAttaced()) {
                            getView().loadStoryContent(storyContent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestError("数据加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStoryContentExtra(int id) {
        if (!isViewAttaced()) {
            return;
        }
        mStoryContentBiz.getStoryContentExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContentExtra>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(StoryContentExtra storyContentExtra) {
                        if (isViewAttaced()) {
                            getView().loadStoryContentExtra(storyContentExtra);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestError("数据加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
