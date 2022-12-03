package com.gh.netlib.net2

import com.gh.net_lib.BaseApiResponse

data class ApiResponseApp<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMsg: String? = null,
) : BaseApiResponse<T>() {

    override val isSuccess: Boolean
        get() = net_code == 0

    override fun converter() {
        net_data = data
        net_code = errorCode
        net_msg = errorMsg
    }

}