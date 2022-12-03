package com.gh.net_lib

import java.io.Serializable

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

open class BaseApiResponse<T>(
    open var net_data: T? = null,
    open var net_code: Int? = null,
    open var net_msg: String? = null,
    open var net_error: Throwable? = null,
) : Serializable {

    open val isSuccess: Boolean
        get() = net_code == 0

    open fun converter() {}

}

data class ApiSuccessResponse<T>(override var net_data: T?) :
    BaseApiResponse<T>(net_data = net_data)

class ApiEmptyResponse<T> : BaseApiResponse<T>()

data class ApiFailedResponse<T>(override var net_code: Int?, override var net_msg: String?) :
    BaseApiResponse<T>(net_code = net_code, net_msg = net_msg)

data class ApiErrorResponse<T>(val throwable: Throwable) : BaseApiResponse<T>(net_error = throwable)
