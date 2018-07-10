package com.nj.zhihu.mvp.presenter;

import com.nj.zhihu.bean.StoryContentLongComment;
import com.nj.zhihu.bean.StoryContentShortComment;
import com.nj.zhihu.mvp.biz.CommentBiz;
import com.nj.zhihu.mvp.view.ICommectView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018-07-10.
 */

public class CommentPresenter extends BasePresenter<ICommectView> {

    private final CommentBiz mBiz;

    public CommentPresenter() {
        mBiz = new CommentBiz();
    }

    public void getLongComments(int id) {
        if (!isViewAttaced()) {
            return;
        }
        mBiz.getStoryContentLongComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContentLongComment>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(StoryContentLongComment storyContentLongComment) {
                        if (isViewAttaced()) {
                            getView().loadLongComments(storyContentLongComment);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestErr("长评论加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getShortComments(int id) {
        if (!isViewAttaced()) {
            return;
        }
        mBiz.getStoryContentShortComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryContentShortComment>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(StoryContentShortComment storyContentShortComment) {
                        if (isViewAttaced()) {
                            getView().loadSgortComments(storyContentShortComment);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            getView().onRequestErr("短评论加载失败o(╥﹏╥)o");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
