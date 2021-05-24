package com.gh.netlib.net

import android.content.Context
import android.util.Log
import com.gh.net_lib.http.HttpManager
import io.reactivex.Flowable
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * @author: gh
 * @description:
 * @date: 5/24/21.
 * @from:
 */
object NetUtils {

    /**
     * 请求网咯,无加载框
     *
     * @param context
     * @param simpleOnNextListener
     */
    fun <T> getNet(context: Context, simpleOnNextListener: HttpOnNextListener<T>) {
        val manager: HttpManager = HttpManager.getInstance()

        manager
            .setInterceptorList(getInterceptorList())
            .doHttpDeal(object : NetApi<T>(context, simpleOnNextListener) {
                override fun getObservable(retrofit: Retrofit): Flowable<*> {
                    val service = retrofit.create(HttpService::class.java)
                    return simpleOnNextListener.onConnect(service)!!
                }
            })
    }

    /**
     * 请求网咯,有加载框
     *
     * @param context
     * @param simpleOnNextListener
     */
    fun <T> getNetProgress(context: Context, simpleOnNextListener: HttpOnNextListener<T>) {
        val manager: HttpManager = HttpManager.getInstance()

        manager
            .setInterceptorList(getInterceptorList())
            .doHttpDeal(object : NetApiProgress<T>(context, simpleOnNextListener) {
                override fun getObservable(retrofit: Retrofit): Flowable<*> {
                    val service = retrofit.create(HttpService::class.java)
                    return simpleOnNextListener.onConnect(service)!!
                }
            })
    }

    private fun getInterceptorList() :List<Interceptor> {
        val interceptorList = ArrayList<Interceptor>()
        interceptorList.add(getHttpLoggingInterceptor())
        return interceptorList
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        //日志显示级别
        val level = HttpLoggingInterceptor.Level.BODY
        //新建log拦截器
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("RxRetrofit", "Retrofit====Message:$message")

            }
        })
        loggingInterceptor.level = level
        return loggingInterceptor
    }

}