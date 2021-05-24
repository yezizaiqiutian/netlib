package com.gh.net_lib.http;

import com.gh.net_lib.api.BaseApi;
import com.gh.net_lib.exception.RetryWhenNetworkException;
import com.gh.net_lib.listener.BaseHttpOnNextListener;
import com.gh.net_lib.subscribers.ProgressSubscriber;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-13.
 * @from:
 */
public class HttpManager {

    private volatile static HttpManager INSTANCE;
    private Map<String, String> headersMap;
    private List<Interceptor> interceptorList;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public HttpManager setHeaders(Map<String, String> headers) {
        headersMap = headers;
        return this;
    }

    public HttpManager setInterceptorList(List<Interceptor> interceptorList) {
        this.interceptorList = interceptorList;
        return this;
    }

    /**
     * 处理http请求
     *
     * @param baseApi
     */
    public void doHttpDeal(BaseApi baseApi) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(baseApi.getConnectionTime(), TimeUnit.SECONDS);

        if (headersMap != null) {
            builder.addInterceptor(chain -> {
                Set set = headersMap.entrySet();
                Iterator i = set.iterator();
                Request.Builder xmlHttpRequest = chain.request().newBuilder();

                while (i.hasNext()) {
                    Map.Entry<String, String> entry1 = (Map.Entry<String, String>) i.next();
                    xmlHttpRequest.addHeader(entry1.getKey(), entry1.getValue());
                }
                Request request = xmlHttpRequest.build();
                return chain.proceed(request);

            });
        }

        if (interceptorList != null && interceptorList.size() > 0) {
            for (Interceptor in : interceptorList) {
                builder.addInterceptor(in);
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseApi.getBaseUrl())
                .build();

        ProgressSubscriber subscriber = new ProgressSubscriber(baseApi);

        Flowable observable = baseApi.getObservable(retrofit)
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException(baseApi.getRetryCount(),
                        baseApi.getRetryDelay(), baseApi.getRetryIncreaseDelay()))
                /*生命周期管理*/
//                .compose(baseApi.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map(baseApi);

        SoftReference<BaseHttpOnNextListener> httpOnNextListener = baseApi.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }

        observable.subscribe(subscriber);

    }

}
