package com.gh.netlib.net

import com.gh.net_lib.listener.BaseHttpOnNextListener
import io.reactivex.Flowable

/**
 * @author: gh
 * @description:
 * @date: 5/24/21.
 * @from:
 */
abstract class HttpOnNextListener<T> : BaseHttpOnNextListener<T?>() {

    abstract fun onConnect(service: HttpService): Flowable<*>?

}