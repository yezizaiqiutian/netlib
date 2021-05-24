package com.gh.net_lib.api;


import android.app.Dialog;
import android.content.Context;

import com.gh.net_lib.LoadingDialog;
import com.gh.net_lib.exception.HttpTimeException;
import com.gh.net_lib.listener.BaseHttpOnNextListener;

import java.lang.ref.SoftReference;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-13.
 * @from:
 */
public abstract class BaseApi<T> implements Function<BaseResultEntity<T>, BaseResultEntity<T>> {
    //rx生命周期管理
    private SoftReference<Context> contextSoftReference;
    //回调
    private SoftReference<BaseHttpOnNextListener> listener;
    //加载框
    private Dialog progressDialog;
    //是否能取消加载框
    private boolean cancel;
    //是否显示加载框
    private boolean showProgress;
    //基础url
    private String baseUrl = "https://www.izaodao.com/Api/";
    //超时时间-默认6秒
    private int connectionTime = 6;
    //失败后retry次数
    private int retryCount = 0;
    //失败后retry延迟
    private long retryDelay = 100;
    //失败后retry叠加延迟
    private long retryIncreaseDelay = 10;

    public BaseApi(Context context, BaseHttpOnNextListener listener) {
        setListener(listener);
        setContext(context);
        setProgressDialog(new LoadingDialog(context));
        setShowProgress(true);
    }

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Flowable getObservable(Retrofit retrofit);

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setContext(Context context) {
        this.contextSoftReference = new SoftReference(context);
    }

    public Dialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(Dialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public SoftReference<BaseHttpOnNextListener> getListener() {
        return listener;
    }

    public void setListener(BaseHttpOnNextListener listener) {
        this.listener = new SoftReference(listener);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public long getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(long retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    /*
     * 获取当前rx生命周期
     * @return
     */
    public Context getContext() {
        return contextSoftReference.get();
    }

    @Override
    public BaseResultEntity<T> apply(BaseResultEntity<T> httpResult) {
        if (!httpResult.isSuccess()) {
            throw new HttpTimeException(httpResult.getMsg());
        }
        return httpResult;
    }

}
