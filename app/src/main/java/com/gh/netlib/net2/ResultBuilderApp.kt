package com.gh.netlib.net2

import com.gh.net_lib.BaseResultBuilder

/**
 * @author: gh
 * @description:
 * @date: 2022/12/1.
 * @from:
 */

class ResultBuilderApp<T> : BaseResultBuilder<T>() {

    //成功
    override var onSuccess: (data: T?) -> Unit = {}
    //数据为空
    override var onDataEmpty: () -> Unit = {}
    //失败
    override var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg.let {
            // TODO:
        }
    }
    //错误
    override var onError: (e: Throwable) -> Unit = { e ->
        e.message?.let {
            // TODO: toast
        }
    }
    //完成
    override var onComplete: () -> Unit = {}

}