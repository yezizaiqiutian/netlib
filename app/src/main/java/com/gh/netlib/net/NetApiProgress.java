//package com.gh.netlib.net;
//
//import android.content.Context;
//
//import com.gh.net_lib.LoadingDialog;
//import com.gh.net_lib.api.BaseApi;
//import com.gh.net_lib.listener.BaseHttpOnNextListener;
//
//import io.reactivex.Flowable;
//import retrofit2.Retrofit;
//
///**
// * @author: gh
// * @description: 有加载框, 下拉刷新页面用
// * @date: 2019-07-19.
// * @from:
// */
//public class NetApiProgress<T> extends BaseApi<T> {
//
//    public NetApiProgress(Context context, BaseHttpOnNextListener listener) {
//        super(context, listener);
//        setBaseUrl(getUrl());
//        setCancel(true);
//        setShowProgress(true);
//        setProgressDialog(new LoadingDialog(context));
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
