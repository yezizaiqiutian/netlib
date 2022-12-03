package com.gh.netlib.net2

import android.util.Log
import com.gh.net_lib.BaseRetrofitClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object RetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        builder.addInterceptor(object :Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d("gggg","哈哈哈啊哈哈")
                val request = chain.request()
                return chain.proceed(request)
            }

        })
    }


}
