package com.gh.net_lib


/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */
open class BaseRepository {

    suspend fun <T> executeHttp(block: suspend () -> BaseApiResponse<T>): BaseApiResponse<T> {
        kotlin.runCatching {
            block.invoke()
        }.onSuccess { data: BaseApiResponse<T> ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return BaseApiResponse()
    }

    /**
     * 返回200，但是还要判断isSuccess
     */
    private fun <T> handleHttpOk(data: BaseApiResponse<T>): BaseApiResponse<T> {
        data.converter()
        return if (data.isSuccess) {
            getHttpSuccessResponse(data)
        } else {
            ApiFailedResponse(data.net_code, data.net_msg)
        }
    }

    /**
     * 成功和数据为空的处理
     */
    private fun <T> getHttpSuccessResponse(response: BaseApiResponse<T>): BaseApiResponse<T> {
        return ApiSuccessResponse(response)
    }

    /**
     * 非后台返回错误,捕获到的异常
     */
    private fun <T> handleHttpError(e: Throwable?): BaseApiResponse<T> {
        if (BuildConfig.DEBUG) e?.printStackTrace()
        return ApiErrorResponse(e)
    }

}