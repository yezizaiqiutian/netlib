package com.gh.net_lib

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

fun <T> BaseApiResponse<T>.parseData(listener: BaseResultBuilder<T>) {
    when (this) {
        is ApiSuccessResponse -> {
            listener.onSuccess(this.net_data)
            listener.onSuccessAll(this.response)
        }

        is ApiFailedResponse -> {
            listener.onFailed(this.net_code, this.net_msg)
            listener.onErrorAll(this.net_code, this.net_msg)
        }

        is ApiErrorResponse -> {
            listener.onError(this.throwable)
            listener.onErrorAll(-1, this.throwable.toString())
        }
    }
    listener.onComplete()
}

open class BaseResultBuilder<T> {

    //成功
    open var onSuccess: (data: T?) -> Unit = {}

    //成功,包含code/msg
    open var onSuccessAll: (data: BaseApiResponse<T>?) -> Unit = {}

    //失败
    open var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _: Int?, _: String? -> }

    //错误
    open var onError: (e: Throwable?) -> Unit = {}

    //所有的错误,包含失败和错误
    open var onErrorAll: (errorCode: Int?, errorMsg: String?) -> Unit = { i: Int?, s: String? -> }

    //完成
    open var onComplete: () -> Unit = {}

}