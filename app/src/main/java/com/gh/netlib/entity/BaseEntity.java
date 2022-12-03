//package com.gh.netlib.entity;
//
//import com.gh.net_lib.api.BaseResultEntity;
//
//import java.io.Serializable;
//
///**
// * @author: gh
// * @description:
// * @date: 5/24/21.
// * @from:
// */
//public class BaseEntity<T> implements BaseResultEntity<T>, Serializable {
//
//    private String ret;
//    private String msg;
//    private T data;
//
//    @Override
//    public boolean isSuccess() {
//        return "1".equals(ret);
//    }
//
//    @Override
//    public String getMsg() {
//        return msg;
//    }
//
//    @Override
//    public T getData() {
//        return data;
//    }
//
//    public String getRet() {
//        return ret == null ? "" : ret;
//    }
//
//    public void setRet(String ret) {
//        this.ret = ret;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//}
