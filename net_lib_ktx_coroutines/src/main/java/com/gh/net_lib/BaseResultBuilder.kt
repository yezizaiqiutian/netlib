package com.gh.net_lib

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

fun <T> BaseApiResponse<T>.parseData(listener: BaseResultBuilder<T>) {
    when (this) {
        is ApiSuccessResponse -> listener.onSuccess(this.net_data)
        is ApiEmptyResponse -> listener.onDataEmpty
        is ApiFailedResponse -> listener.onFailed(this.net_code, this.net_msg)
        is ApiErrorResponse -> listener.onError(this.throwable)
    }
    listener.onComplete()
}

open class BaseResultBuilder<T> {

    //成功
    open var onSuccess: (data: T?) -> Unit = {}

    //数据为空
    open var onDataEmpty: () -> Unit = {}

    //失败
    open var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _: Int?, _: String? -> }

    //错误
    open var onError: (e: Throwable) -> Unit = {}

    //完成
    open var onComplete: () -> Unit = {}

}