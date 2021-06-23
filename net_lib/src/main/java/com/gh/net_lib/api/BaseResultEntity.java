package com.gh.net_lib.api;

/**
 * @author: gh
 * @description:请求接口返回基类
 * @date: 2019-07-13.
 * @from:
 */
public interface BaseResultEntity<T> {

    public  boolean isSuccess();

    public String getCode();

    public String getMsg();

    public T getData();

}
