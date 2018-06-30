package com.nj.zhihu.mvp.presenter;

import com.nj.zhihu.mvp.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Presenter的父类
 * 管理View的附加与清除
 * 统计管理订阅者，包括添加和取消
 * Created by Administrator on 2018-06-28.
 */

public class BasePresenter<V extends IBaseView> {
    private V mvpView;

    /**
     * 附加上view
     * @param mvpView
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 清除view
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 判断view是否可用
     * @return
     */
    public boolean isViewAttaced() {
        return this.mvpView != null;
    }

    /**
     * 获取view
     * @return
     */
    public V getView() {
        return this.mvpView;
    }

    //RxJava1中使用的是CompositeSubscription，RxJava2中使用CompositeDisposable
    private CompositeDisposable mCompositeDisposable;

    //CompositeSubscription中的参数是Subscription
    //CompositeDisposable中的参数是Disposable
    /**
     * 每当我们得到一个Disposable时就将它添加到容器中
     * @param disposable
     */
    public void addSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消订阅
     * 对于一个Activity是有独立的Presenter，例如当Activity退出时，停止所有的订阅
     */
    public void unSubscription() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
