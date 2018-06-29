package com.nj.zhihu.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.nj.zhihu.bean.LaunchImage;
import com.nj.zhihu.mvp.biz.SplashBiz;
import com.nj.zhihu.mvp.view.ISplashView;
import com.nj.zhihu.ui.activity.SplashActivity;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Splash的P层
 * 用来获取数据，并把数据传递给view
 * Created by Administrator on 2018-06-28.
 */

public class SplashPresenter extends IBasePresenter<ISplashView> {
    private static final String TAG = "SplashPresenterLog";

    private final SplashBiz mBiz;
    private ISplashView mView;

    public SplashPresenter() {
        mBiz = new SplashBiz();
    }

    /**
     * 获取加载界面的图片，这里是利用Retrofit结合Rxjava获取的
     */
    public void getLaunchImage() {
        if (!isViewAttaced()) {
            return;
        }
        //获取view
        mView = getView();
        //用Rxjava处理事件
        mBiz.getLaunchImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LaunchImage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(LaunchImage launchImage) {
                        if (isViewAttaced()) {
                            Log.d(TAG, "接收到数据了: " + launchImage.toString());
                            mView.getLanuchImage(launchImage);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttaced()) {
                            Log.d(TAG, "请求失败了: " + e);
                            mView.onRequestErr();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttaced()) {
                            Log.d(TAG, "请求完毕了: ");
                            mView.onRequestEnd();
                        }
                    }
                });
    }

    /**
     * 从必应获取加载界面的图片，这里是利用Retrofit结合Rxjava获取的
     */
    public void getLaunchImageFromBiY() {
        if (!isViewAttaced()) {
            return;
        }
        //获取view
        mView = getView();
        String bingUrl = "http://guolin.tech/api/bing_pic";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(bingUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String imageUrl = response.body().string();
                Log.d(TAG, "数据是:" + imageUrl + "\n当前线程是: " + Thread.currentThread().getName());
                mView.getLanuchImageFromBiY(imageUrl);
            }
        });
    }
}
