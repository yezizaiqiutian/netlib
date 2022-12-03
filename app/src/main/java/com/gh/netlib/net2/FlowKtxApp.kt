package com.gh.netlib.net2

import androidx.lifecycle.lifecycleScope
import com.gh.net_lib.BaseApiResponse
import com.gh.net_lib.parseData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

fun <T> launchFlow(
    requestBlock: suspend () -> BaseApiResponse<T>,
    startCallback: (() -> Unit)? = null,
    completeCallback: (() -> Unit)? = null,
): Flow<BaseApiResponse<T>> {
    return flow {
        emit(requestBlock())
    }.onStart {
        startCallback?.invoke()
    }.onCompletion {
        completeCallback?.invoke()
    }
}

/**
 * 这个方法只是简单的一个封装Loading的普通方法，不返回任何实体类
 */
fun IUIView.launchWithLoading(requestBlock: suspend () -> Unit) {
    lifecycleScope.launch {
        flow {
            emit(requestBlock())
        }.onStart {
            showLoading()
        }.onCompletion {
            dismissLoading()
        }.collect()
    }
}

/**
 * 推荐
 * 请求不带Loading&&不需要声明LiveData
 */
fun <T> IUIView.launchAndCollect(
    requestBlock: suspend () -> BaseApiResponse<T>,
    listenerBuilder: ResultBuilderApp<T>.() -> Unit,
) {
    lifecycleScope.launch {
        launchFlow(requestBlock).collect { response ->
            val listener = ResultBuilderApp<T>().also(listenerBuilder)
            response.parseData(listener)
        }
    }
}

/**
 * 推荐
 * 请求带Loading&&不需要声明LiveData
 */
fun <T> IUIView.launchWithLoadingAndCollect(
    requestBlock: suspend () -> BaseApiResponse<T>,
    listenerBuilder: ResultBuilderApp<T>.() -> Unit,
) {
    lifecycleScope.launch {
        launchFlow(requestBlock, { showLoading() }, { dismissLoading() }).collect { responce ->
            val listener = ResultBuilderApp<T>().also(listenerBuilder)
            responce.parseData(listener)
        }
    }
}