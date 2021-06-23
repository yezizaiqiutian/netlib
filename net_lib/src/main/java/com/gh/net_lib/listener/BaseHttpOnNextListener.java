package com.gh.net_lib.listener;


import io.reactivex.Flowable;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-13.
 * @from:
 */
public abstract class BaseHttpOnNextListener<T> {

    /**
     * 成功后回调方法
     *
     * @param t
     */
    public abstract void onNext(T t);


    /**
     * 失败或者错误方法
     *
     * @param e
     */
    public void onError(Throwable e) {
    }

    /**
     * 失败或者错误方法
     *
     * @param e
     * @param errorCode
     * @param errorMsg
     * @param t
     */
    public void onError(Throwable e, String errorCode, String errorMsg, T t) {
    }

    /**
     * 取消回調
     */
    public void onCancel() {
    }

    public void onLoading() {
    }

    public void onLoadFinish() {
    }

    /**
     * 不常用
     * 成功后的ober返回，扩展链接式调用
     *
     * @param observable
     */
    public void onNext(Flowable observable) {
    }

}
