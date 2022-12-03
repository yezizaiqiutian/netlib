//package com.gh.netlib.net;
//
//import android.content.Context;
//
//import com.gh.net_lib.api.BaseApi;
//import com.gh.net_lib.listener.BaseHttpOnNextListener;
//
//import io.reactivex.Flowable;
//import retrofit2.Retrofit;
//
///**
// * @author: gh
// * @description:
// * @date: 2019-07-19.
// * @from:
// */
//public class NetApi<T> extends BaseApi<T> {
//
//    public NetApi(Context context, BaseHttpOnNextListener listener) {
//        super(context, listener);
//        setBaseUrl(getUrl());
//        setShowProgress(false);
//    }
//
//    @Override
//    public Flowable getObservable(Retrofit retrofit) {
//        return null;
//    }
//
//    public String getUrl() {
//        return "https://www.izaodao.com/Api/";
//    }
//
//}
