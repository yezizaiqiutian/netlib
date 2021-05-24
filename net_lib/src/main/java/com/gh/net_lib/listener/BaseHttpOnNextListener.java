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
     * 主动调用，更加灵活
     *
     * @param e     错误
     * @param t     返回的数据(如果无法返回,则为null)
     */
    public void onError(Throwable e, T t) {
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
