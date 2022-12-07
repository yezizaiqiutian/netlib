package com.gh.netlib.net2

import android.util.Log
import com.gh.net_lib.BaseApiResponse
import com.gh.net_lib.BaseResultBuilder

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

class ResultBuilder<T> : BaseResultBuilder<T>() {

    //请求成功完整数据
    override var onSuccessAll: (data: BaseApiResponse<T>?) -> Unit = {
        onSuccessAll(it)
    }

    //请求成功泛型数据
    override var onSuccess: (data: T?) -> Unit = {
        onSuccess(it)
    }

    //失败
    override var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { errorCode, errorMsg ->
        onFailed(errorCode, errorMsg)
    }

    //错误
    override var onError: (e: Throwable?) -> Unit = { e ->
        onError(e)
    }

    override var onErrorAll: (errorCode: Int?, errorMsg: String?) -> Unit = { errorCode, errorMsg ->
        onErrorAll(errorCode, errorMsg)
    }

    //完成
    override var onComplete: () -> Unit = {
        onComplete()
    }


    fun onSuccessAll(data: BaseApiResponse<T>?) {
        Log.d("ggggg", "公共---请求成功${data.toString()}")
    }

    fun onSuccess(data: T?) {
        Log.d("ggggg", "公共---请求成功${data.toString()}")
    }

    fun onFailed(errorCode: Int?, errorMsg: String?) {
        Log.d("ggggg", "公共---请求失败${errorCode}${errorMsg}")
    }

    fun onError(e: Throwable?) {
        Log.d("ggggg", "公共---请求异常${e.toString()}")
    }

    fun onErrorAll(errorCode: Int?, errorMsg: String?) {
        Log.d("ggggg", "公共---请求失败${errorCode}${errorMsg}")
    }

    fun onComplete() {
        Log.d("ggggg", "公共---请求完成")
    }


}