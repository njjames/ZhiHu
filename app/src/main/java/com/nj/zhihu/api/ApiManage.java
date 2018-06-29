package com.nj.zhihu.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.internal.schedulers.RxThreadFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于统一的管理Retrofit的请求接口，生成Retrofit实例，并生成网络接口实例
 * 此类设置为单例模式
 * Created by Administrator on 2018-06-29.
 */

public class ApiManage {
    private static ApiManage mApiManage = null;

    private CommonApi mCommonApi;

    private static final int DEFAULT_TIMEOUT = 5;
    //这个对象用来作为Commonapi的锁
    private final Object Monitor = new Object();
    //使用自定义的OKHttpClient
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build();

    private ApiManage() {}

    public static ApiManage getInstance() {
        if (mApiManage ==null) {
            synchronized (ApiManage.class) {
                if (mApiManage == null) {
                    mApiManage = new ApiManage();
                }
            }
        }
        return mApiManage;
    }

    /**
     * 获取网络请求接口的实例
     * 这个对象也是单例的
     * @return 网络请求接口的实例
     */
    public CommonApi getCommonApi() {
        if (mCommonApi == null) {
            synchronized (Monitor) {
                if (mCommonApi == null) {
                    mCommonApi = configRetrofit(CommonApi.class);
                }
            }
        }
        return mCommonApi;
    }

    /**
     * 先获取Retrofit的实例，然后获取网络请求接口实例
     * @param service
     * @param <T>
     * @return 接口实例
     */
    private <T> T configRetrofit(Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }
}
